package org.kelhenson.sortingalgorithms.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSortController extends AppController {
    @FXML
    private Slider numOfComputationsSlider;

    @FXML
    private BarChart<String, Number> numbersChart;

    public void initialize() {
        //Slider listener
        numOfComputationsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Slider changed to " + newValue);
        });

        //Initialize BarChart with values 1-100, in shuffled order
        List<Integer> numList = new ArrayList<>();
        for (int i=1; i<=100; i++) numList.add(i);
        Collections.shuffle(numList);
        XYChart.Series<String, Number> numbers = new XYChart.Series<>();
        for (Integer i : numList) numbers.getData().add(new XYChart.Data<>(String.valueOf(i), i));
        numbersChart.getData().add(numbers);
    }

    @FXML
    protected void onInfoButtonClick() {
        System.out.println("Info btn clicked");
    }

    @FXML
    protected void onStartButtonClick() {
        System.out.println("Start btn clicked");
    }

    @FXML
    protected void onHomeButtonClick() throws IOException {
        closeCurrAndOpenNewWindow(numOfComputationsSlider.getScene().getWindow(),
                "home.fxml", 400, 400, "Sorting Application", false);
    }
}