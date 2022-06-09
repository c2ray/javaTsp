package com.illidan.tsp.painter;

import com.illidan.tsp.ga.impl.City;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * @author Illidan
 */
public class Painter {
    
    /**
     * 城市大小
     */
    private static final int CITY_SIZE = 10;
    
    public void drawCity(List<City> cities, GraphicsContext gc) {
        gc.clearRect(0, 0, 600, 600);
        gc.setFill(Color.BLACK);
        cities.forEach(city -> {
            double xLocation = city.getX();
            double yLocation = city.getY();
            gc.fillOval(xLocation, yLocation, CITY_SIZE, CITY_SIZE);
        });
    }
    
    
    public void drawLine(List<City> cities, GraphicsContext gc) {
        // 设置笔画路径
        gc.beginPath();
        City firstCity = cities.get(0);
        double x1 = firstCity.getX() + (double) (CITY_SIZE / 2);
        double y1 = firstCity.getY() + (double) (CITY_SIZE / 2);
        gc.setStroke(Color.BLACK);
        gc.moveTo(x1, y1);
        gc.setLineWidth(1);
        cities.forEach(city -> {
            double x = city.getX() + (double) (CITY_SIZE / 2);
            double y = city.getY() + (double) (CITY_SIZE / 2);
            gc.lineTo(x, y);
        });
        gc.closePath();
        // 将笔画路径画出
        gc.stroke();
    }
    
    /**
     * 清除填充
     */
    public void clearFill(GraphicsContext gc) {
        gc.clearRect(0, 0, 600, 600);
    }
    
    /**
     * 清除笔触
     */
    public void clearStroke(GraphicsContext gc) {
        // 清除笔触
        gc.beginPath();
    }
}
