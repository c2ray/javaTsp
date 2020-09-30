package com.illidan.tsp.controller;


import com.illidan.tsp.service.TspService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

/**
 * Tsp应用控制器
 *
 * @author Illidan
 */
@FXMLController
public class TspController {

    private final TspService tspService;
    @FXML
    public Pane tspDashboard;
    @FXML
    private Canvas tspCanvas;

    public TspController(TspService tspService) {
        this.tspService = tspService;
    }

}
