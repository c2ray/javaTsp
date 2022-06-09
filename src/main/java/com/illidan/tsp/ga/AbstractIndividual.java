package com.illidan.tsp.ga;

import com.illidan.tsp.ga.impl.City;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 一个个体代表一个解
 *
 * @author Illidan
 */
public abstract class AbstractIndividual {
    
    protected Double fitness = -1.0;
    
    protected List<Integer> chromosome;
    
    protected Double totalDistance = 0.0;
    
    /**
     * 个体适应度
     */
    public Double getFitness() {
        return fitness;
    }
    
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    
    /**
     * 染色体
     */
    public List<Integer> getChromosome() {
        return chromosome;
    }
    
    public void setChromosome(List<Integer> chromosome) {
        this.chromosome = chromosome;
    }
    
    public Double getTotalDistance() {
        return totalDistance;
    }
    
    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }
    
    
    /**
     * 计算自身适应度(需要自行编写计算适应度的算法)
     */
    public abstract void calcFitness(List<City> cities);
    
    
    public void setGene(int position, int gene) {
        this.chromosome.set(position, gene);
    }
    
    public int getGene(int position) {
        return chromosome.get(position);
    }
    
    public int getChromosomeLength() {
        return this.chromosome.size();
    }
    
    public boolean containsGene(int gene) {
        return chromosome.contains(gene);
    }
    
    
    @Override
    public String toString() {
        String route = chromosome.stream()
                .map(integer -> integer + "")
                .collect(Collectors.joining(" -> "));
        return String.format("当前路径: %s \n当前总距离: %f\n", route, totalDistance);
    }
    
    /**
     * 获取按染色体排序好的城市
     */
    public List<City> getOrderedCities(List<City> cities) {
        ArrayList<City> newCities = new ArrayList<>();
        for (Integer cityIndex : chromosome) {
            City city = cities.get(cityIndex);
            newCities.add(city);
        }
        return newCities;
    }
}
