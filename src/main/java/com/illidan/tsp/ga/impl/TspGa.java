package com.illidan.tsp.ga.impl;

import com.illidan.tsp.ga.AbstractIndividual;
import com.illidan.tsp.ga.AbstractPopulation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Illidan
 */
public class TspGa {
    
    
    public static final int CITY_NUMBER = 50;
    
    private static List<City> generateCitys() {
        ArrayList<City> cities = new ArrayList<>(CITY_NUMBER);
        for (int i = 0; i < CITY_NUMBER; i++) {
            double x = Math.random() * 800;
            double y = Math.random() * 800;
            City city = new City(x, y);
            cities.add(city);
        }
        return cities;
    }
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<City> cities = generateCitys();
        
        GeneticAlgorithm ga = new GeneticAlgorithm(3000, 100,
                0.95, 0.001, 4,
                15);
        ga.initPopulation(CITY_NUMBER);
        ga.evalPopulation(cities);
        
        while (!ga.isTerminationMet()) {
            ga.crossoverPopulation();
            ga.mutatePopulation();
            ga.evalPopulation(cities);
            
            AbstractPopulation population = ga.getPopulation();
            AbstractIndividual individual = population.getFittest(0);
            List<City> orderedCities = individual.getOrderedCities(cities);
            
            System.out.println(individual);
            ga.generationAddup();
        }
        
        long end = System.currentTimeMillis();
        System.out.println("用时: " + (end - start) + " ms");
        
    }
    
}
