package com.illidan.tsp.ga.impl;

/**
 * @author Illidan
 */
public class City {
    
    private double x;
    private double y;
    
    public City() {
    }
    
    public City(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    /**
     * @param city 待比较的城市
     * @return 城市之间的距离
     */
    public double distanceFrom(City city) {
        double deltaxSq = Math.pow(this.x - city.getX(), 2);
        double deltaySq = Math.pow(this.y - city.getY(), 2);
        return Math.sqrt(deltaxSq + deltaySq);
    }
    
}
