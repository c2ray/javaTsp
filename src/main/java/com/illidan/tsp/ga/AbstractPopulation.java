package com.illidan.tsp.ga;

import com.illidan.tsp.ga.impl.City;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Illidan
 */
public abstract class AbstractPopulation {
    
    
    protected final Integer popSize;
    
    protected List<AbstractIndividual> individuals;
    
    protected double populationFitness;
    
    public AbstractPopulation(Integer popSize) {
        this.popSize = popSize;
    }
    
    
    public AbstractPopulation(int popSize) {
        this.popSize = popSize;
        individuals = new ArrayList<>(popSize);
    }
    
    
    /**
     * 种群大小
     */
    public Integer getPopSize() {
        return popSize;
    }
    
    /**
     * 种群中所有的个体
     */
    public List<AbstractIndividual> getIndividuals() {
        return individuals;
    }
    
    public void setIndividuals(List<AbstractIndividual> individuals) {
        this.individuals = individuals;
    }
    
    public double getPopulationFitness() {
        return populationFitness;
    }
    
    public void setPopulationFitness(double populationFitness) {
        this.populationFitness = populationFitness;
    }
    
    
    /**
     * 按适应度获取个体
     *
     * @param position 适应度排名
     * @return 符合适应度排名的个体
     */
    public abstract AbstractIndividual getFittest(int position);
    
    
    /**
     * 打乱当前种群
     */
    public abstract void shuffle();
    
    /**
     * 评估种群适应度
     */
    public abstract void evaluate(List<City> cities);
    
    /**
     * 获取对应下标的的个体
     */
    public AbstractIndividual getIndividual(int position) {
        return individuals.get(position);
    }
    
    /**
     * 添加一个个体
     */
    public void addIndividual(AbstractIndividual individual) {
        this.individuals.add(individual);
    }
    
    
}
