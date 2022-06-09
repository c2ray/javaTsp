package com.illidan.tsp.controller;


import com.illidan.tsp.ga.AbstractIndividual;
import com.illidan.tsp.ga.AbstractPopulation;
import com.illidan.tsp.ga.impl.City;
import com.illidan.tsp.ga.impl.GeneticAlgorithm;
import com.illidan.tsp.painter.Painter;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Tsp应用控制器
 *
 * @author Illidan
 */
@FXMLController
public class TspController implements Initializable {
    
    
    @FXML
    public Pane tspDashboard;
    private final Painter painter;
    @FXML
    public TextField cityNumberText;
    @FXML
    public TextField popSizeText;
    @FXML
    public TextField maxGenerationText;
    @FXML
    public TextField crossoverRateText;
    @FXML
    public TextField mutationRateText;
    @FXML
    public TextField elitismCountText;
    @FXML
    public TextField tournamentSizeText;
    @FXML
    public TextField totalDistance;
    public int cityNumber;
    @FXML
    private Canvas tspCanvas;
    /**
     * 是否正在处理tsp
     */
    private boolean isSolving;
    private int maxGeneration;
    private int popSize;
    private double crossoverRate;
    private double mutationRate;
    private int elitismCount;
    private int tournamentSize;
    
    
    public TspController(Painter painter) {
        this.painter = painter;
    }
    
    /**
     * 不会被执行,(除非实现Initializable接口)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cityNumberText.setText(String.valueOf(30));
        maxGenerationText.setText(String.valueOf(1000));
        popSizeText.setText(String.valueOf(100));
        crossoverRateText.setText(String.valueOf(0.95));
        mutationRateText.setText(String.valueOf(0.001));
        elitismCountText.setText(String.valueOf(4));
        tournamentSizeText.setText(String.valueOf(10));
    }
    
    
    /**
     * 生成随机城市
     */
    private List<City> generateCitys() {
        ArrayList<City> cities = new ArrayList<>(cityNumber);
        for (int i = 0; i < cityNumber; i++) {
            double x = Math.random() * 590;
            double y = Math.random() * 590;
            City city = new City(x, y);
            cities.add(city);
        }
        return cities;
    }
    
    /**
     * 从文本框中读取配置
     */
    private void readConfig() {
        cityNumber = Integer.parseInt(cityNumberText.getText());
        popSize = Integer.parseInt(popSizeText.getText());
        maxGeneration = Integer.parseInt(maxGenerationText.getText());
        crossoverRate = Double.parseDouble(crossoverRateText.getText());
        mutationRate = Double.parseDouble(mutationRateText.getText());
        elitismCount = Integer.parseInt(elitismCountText.getText());
        tournamentSize = Integer.parseInt(tournamentSizeText.getText());
    }
    
    /**
     * 点击开始求解按钮, 开始求解tsp
     */
    @SuppressWarnings("all")
    public void resolve(MouseEvent mouseEvent) throws InterruptedException {
        readConfig();
        if (!isSolving) {
            isSolving = true;
            new Thread(() -> {
                GraphicsContext gc = tspCanvas.getGraphicsContext2D();
                totalDistance.setText("正在解析中...");
                AbstractIndividual bestIndividual = null;
                List<City> cities = generateCitys();
                GeneticAlgorithm ga = new GeneticAlgorithm(maxGeneration, popSize,
                        crossoverRate, mutationRate, elitismCount, tournamentSize);
                ga.initPopulation(cityNumber);
                ga.evalPopulation(cities);
                while (!ga.isTerminationMet()) {
                    ga.crossoverPopulation();
                    ga.mutatePopulation();
                    ga.evalPopulation(cities);
                    // 获取当前最优路径
                    AbstractPopulation population = ga.getPopulation();
                    bestIndividual = population.getFittest(0);
                    List<City> bestCityOrder = bestIndividual.getOrderedCities(cities);
                    // 画出当前最优路径
                    drawBest(gc, bestCityOrder);
                    ga.generationAddup();
                }
                // 显示当前总距离
                String totalDistanceStr = String.valueOf(
                                bestIndividual.getTotalDistance())
                        .split("\\.")[0];
                totalDistance.setText(totalDistanceStr);
                
                isSolving = false;
            }).start();
        }
    }
    
    /**
     * 画出最优解
     */
    private void drawBest(GraphicsContext gc, List<City> orderedCities) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        painter.drawCity(orderedCities, gc);
        painter.drawLine(orderedCities, gc);
    }
    
    
}
