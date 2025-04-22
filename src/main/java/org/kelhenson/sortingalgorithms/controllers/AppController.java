package org.kelhenson.sortingalgorithms.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.kelhenson.sortingalgorithms.SortingApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AppController {

    protected int swapListIdx = 0;
    protected boolean isStarted = false;
    protected List<Integer> mergeSortList = new ArrayList<>();
    protected final List<Map<Integer, Integer>> swapList = new ArrayList<>();

    public BarChart<String, Number> initValues(BarChart<String, Number> barChart) {
        //Initialize BarChart with values 1-50, in shuffled order
        for (int i=1; i<=50; i++) mergeSortList.add(i);
        Collections.shuffle(mergeSortList);

        XYChart.Series<String, Number> xyChart = new XYChart.Series<>();
        for (Integer i : mergeSortList) xyChart.getData().add(new XYChart.Data<>(String.valueOf(i), i));
        barChart.getData().add(xyChart);
        return barChart;
    }

    public void displayInfoMsg(String title, String msg) {
        Alert msgBox = new Alert(Alert.AlertType.INFORMATION);
        msgBox.setTitle(title);
        msgBox.setHeaderText(null); // No header text
        msgBox.setContentText(msg);
        msgBox.showAndWait();
    }

    protected void closeCurrAndOpenNewWindow(Window window, String fxmlResource, int width, int height, String title, boolean isStageUndecorated) throws IOException {
        window.hide();
        FXMLLoader fxmlLoader = new FXMLLoader(SortingApplication.class.getResource(fxmlResource));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        if (isStageUndecorated) stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

}
