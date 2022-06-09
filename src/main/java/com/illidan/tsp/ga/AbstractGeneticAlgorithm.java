package com.illidan.tsp.ga;


import com.illidan.tsp.ga.impl.City;

import java.util.List;

/**
 * @author Illidan
 */
public abstract class AbstractGeneticAlgorithm {
    
    /**
     * 默认最大代数
     */
    protected static Integer DEFUALT_DefualtMAX_GENERATION = 500;
    /**
     * 种群数
     */
    protected Integer popSize;
    protected AbstractPopulation population;
    /**
     * 竞标赛容量
     */
    protected Integer tournamentSize;
    /**
     * 交叉率
     */
    protected Double crossoverRate;
    /**
     * 变异率
     */
    protected Double mutationRate;
    /**
     * 精英数
     */
    protected Integer elitismCount;
    /**
     * 当前代数
     */
    protected Integer generation = 0;
    /**
     * 最大代数
     */
    protected Integer maxGeneration = DEFUALT_DefualtMAX_GENERATION;
    
    
    /**
     * 种群
     */
    public AbstractPopulation getPopulation() {
        return population;
    }
    
    public void setPopulation(AbstractPopulation population) {
        this.population = population;
    }
    
    /**
     * 初始化种群的染色体长度(和城市数量相等)
     */
    public abstract void initPopulation(int chromosomeLength);
    
    
    /**
     * 评估种群
     */
    public abstract void evalPopulation(List<City> cities);
    
    
    /**
     * 是否达到结束条件
     */
    public abstract boolean isTerminationMet();
    
    /**
     * 交叉种群
     */
    public abstract void crossoverPopulation();
    
    /**
     * 变异种群
     */
    public abstract void mutatePopulation();
    
    /**
     * 选择父代
     */
    public abstract AbstractIndividual selectParent();
    
}

