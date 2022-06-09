package com.illidan.tsp.ga.impl;

import com.illidan.tsp.ga.AbstractIndividual;
import com.illidan.tsp.ga.AbstractPopulation;

import java.util.List;
import java.util.Random;

/**
 * @author Illidan
 */
public class Population extends AbstractPopulation {
    
    /**
     * 初始化individuals的容量, individuals数组中没有内容
     */
    public Population(int popSize) {
        super(popSize);
    }
    
    
    /**
     * @param popSize          种群大小
     * @param chromosomeLength 染色体长度(和城市数量相同)
     */
    public Population(int popSize, int chromosomeLength) {
        super(popSize);
        
        for (int i = 0; i < popSize; i++) {
            this.individuals.add(new Individual(chromosomeLength));
        }
    }
    
    
    @Override
    public void evaluate(List<City> cities) {
        this.populationFitness = individuals
                .stream()
                .mapToDouble(individual -> {
                    if (individual.getFitness() < 0) {
                        individual.calcFitness(cities);
                    }
                    return individual.getFitness();
                }).sum() / popSize;
    }
    
    @Override
    public void shuffle() {
        Random random = new Random();
        for (int i = popSize - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            AbstractIndividual tempIndividual = individuals.get(index);
            individuals.set(index, individuals.get(i));
            individuals.set(i, tempIndividual);
        }
    }
    
    /**
     * 按适应度获取个体
     *
     * @param position 适应度排名
     * @return 符合适应度排名的个体
     */
    @Override
    public AbstractIndividual getFittest(int position) {
        individuals.sort((individual1, individual2) -> {
            Double fintness1 = individual1.getFitness();
            Double fintness2 = individual2.getFitness();
            return fintness2.compareTo(fintness1);
        });
        
        return individuals.get(position);
    }
}
