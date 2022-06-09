package com.illidan.tsp.ga.impl;

import com.illidan.tsp.ga.AbstractIndividual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Illidan
 */
public class Individual extends AbstractIndividual {
    
    
    public Individual() {
    }
    
    
    /**
     * 按指定染色体生成个体
     *
     * @param chromosome 指定个体的染色体
     */
    public Individual(int[] chromosome) {
        // ArrayList<Integer> chromosomeList = new ArrayList<>();
        // for (int i : chromosome) {
        //     chromosomeList.add(i);
        // }
        // this.chromosome = chromosomeList;
        this.chromosome = Arrays.stream(chromosome)
                .collect(ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);
    }
    
    /**
     * 生成一个指定染色体长度的个体
     *
     * @param chromosomeLength 个体染色体长度(和城市数量相等)
     */
    public Individual(int chromosomeLength) {
        this.chromosome = new ArrayList<>(chromosomeLength);
        for (int gene = 0; gene < chromosomeLength; gene++) {
            chromosome.add(gene);
        }
    }
    
    
    /**
     * 计算个体适应度
     *
     * @param cities 随机生成的城市序列
     */
    @Override
    public void calcFitness(List<City> cities) {
        calcTotalDistance(cities);
        this.fitness = 1 / totalDistance;
    }
    
    /**
     * 计算当前个体(解)所用的总距离
     */
    private void calcTotalDistance(List<City> cities) {
        int size = chromosome.size();
        for (int chromosomeIndex = 0; chromosomeIndex + 1 <= size; chromosomeIndex++) {
            Integer cityIndex1 = chromosome.get(chromosomeIndex);
            Integer cityIndex2;
            // 实现闭环距离比较
            if (chromosomeIndex + 1 == size) {
                cityIndex2 = chromosome.get(chromosomeIndex + 1 - size);
            } else {
                cityIndex2 = chromosome.get(chromosomeIndex + 1);
            }
            City city1 = cities.get(cityIndex1);
            City city2 = cities.get(cityIndex2);
            double distance = city1.distanceFrom(city2);
            this.totalDistance += distance;
        }
    }
    
}
