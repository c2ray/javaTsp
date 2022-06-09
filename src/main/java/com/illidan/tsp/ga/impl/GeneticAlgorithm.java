package com.illidan.tsp.ga.impl;

import com.illidan.tsp.ga.AbstractGeneticAlgorithm;
import com.illidan.tsp.ga.AbstractIndividual;
import com.illidan.tsp.ga.AbstractPopulation;

import java.util.Arrays;
import java.util.List;

/**
 * @author Illidan
 */
public class GeneticAlgorithm extends AbstractGeneticAlgorithm {
    
    /**
     * @param maxGeneration 最大代数
     * @param popSize       种群大小
     * @param crossoverRate 交叉概率
     * @param mutationRate  变异概率
     * @param elitismCount  精英数
     */
    public GeneticAlgorithm(int maxGeneration, int popSize,
                            double crossoverRate, double mutationRate,
                            int elitismCount, int tournamentSize) {
        this.popSize = popSize;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.elitismCount = elitismCount;
        this.tournamentSize = tournamentSize;
        this.maxGeneration = maxGeneration;
    }
    
    /**
     * @param popSize       种群大小
     * @param crossoverRate 交叉概率
     * @param mutationRate  变异概率
     * @param elitismCount  精英数
     */
    public GeneticAlgorithm(int popSize,
                            double crossoverRate, double mutationRate,
                            int elitismCount, int tournamentSize) {
        this.popSize = popSize;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.elitismCount = elitismCount;
        this.tournamentSize = tournamentSize;
    }
    
    
    @Override
    public void initPopulation(int chromosomeLength) {
        this.population = new Population(this.popSize, chromosomeLength);
    }
    
    @Override
    public void evalPopulation(List<City> cities) {
        population.evaluate(cities);
    }
    
    
    @Override
    public boolean isTerminationMet() {
        return generation.equals(this.maxGeneration);
    }
    
    /**
     * 代数加1
     */
    public void generationAddup() {
        generation += 1;
    }
    
    @Override
    public void crossoverPopulation() {
        Population newPopulation = new Population(popSize);
        for (int populationIndex = 0; populationIndex < popSize; populationIndex++) {
            AbstractIndividual parent1 = population.getFittest(populationIndex);
            if (populationIndex >= elitismCount && this.crossoverRate > Math.random()) {
                AbstractIndividual parent2 = selectParent();
                // Create blank offspring chromosome
                int[] offspringChromosome = new int[parent1.getChromosomeLength()];
                Arrays.fill(offspringChromosome, -1);
                Individual offspring = new Individual(offspringChromosome);
                
                // Get subset of parent chromosomes
                int substrPos1 = (int) (Math.random() * parent1.getChromosomeLength());
                int substrPos2 = (int) (Math.random() * parent1.getChromosomeLength());
                
                // make the smaller the start and the larger the end
                final int startSubstr = Math.min(substrPos1, substrPos2);
                final int endSubstr = Math.max(substrPos1, substrPos2);
                
                // Loop and add the sub tour from parent1 to our child
                for (int i = startSubstr; i < endSubstr; i++) {
                    offspring.setGene(i, parent1.getGene(i));
                }
                // Loop through parent2's city tour
                for (int i = 0; i < parent2.getChromosomeLength(); i++) {
                    int parent2Gene = i + endSubstr;
                    if (parent2Gene >= parent2.getChromosomeLength()) {
                        parent2Gene -= parent2.getChromosomeLength();
                    }
                    // If offspring doesn't have the city add it
                    if (!offspring.containsGene(parent2.getGene(parent2Gene))) {
                        // Loop to find a spare position in the child's tour
                        for (int ii = 0; ii < offspring.getChromosomeLength(); ii++) {
                            // Spare position found, add city
                            if (offspring.getGene(ii) == -1) {
                                offspring.setGene(ii, parent2.getGene(parent2Gene));
                                break;
                            }
                        }
                    }
                }
                newPopulation.addIndividual(offspring);
            } else {
                newPopulation.addIndividual(parent1);
            }
        }
        this.population = newPopulation;
    }
    
    @Override
    public void mutatePopulation() {
        // Initialize new population
        Population newPopulation = new Population(popSize);
        
        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < popSize; populationIndex++) {
            AbstractIndividual individual = population.getFittest(populationIndex);
            
            // Skip mutation if this is an elite individual
            if (populationIndex >= this.elitismCount) {
                // System.out.println("Mutating population member "+populationIndex);
                // Loop over individual's genes
                for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                    // System.out.println("\tGene index "+geneIndex);
                    // Does this gene need mutation?
                    if (this.mutationRate > Math.random()) {
                        // Get new gene position
                        int newGenePos = (int) (Math.random() * individual.getChromosomeLength());
                        // Get genes to swap
                        int gene1 = individual.getGene(newGenePos);
                        int gene2 = individual.getGene(geneIndex);
                        // Swap genes
                        individual.setGene(geneIndex, gene1);
                        individual.setGene(newGenePos, gene2);
                    }
                }
            }
            // Add individual to population
            newPopulation.addIndividual(individual);
        }
        
        // Return mutated population
        this.population = newPopulation;
    }
    
    @Override
    public AbstractIndividual selectParent() {
        Population tournament = new Population(tournamentSize);
        AbstractPopulation newPopulation = population;
        newPopulation.shuffle();
        for (int i = 0; i < this.tournamentSize; i++) {
            AbstractIndividual tournamentIndividual = newPopulation.getIndividual(i);
            tournament.addIndividual(tournamentIndividual);
        }
        // 返回锦标赛中最好的个体
        return tournament.getFittest(0);
    }
}

