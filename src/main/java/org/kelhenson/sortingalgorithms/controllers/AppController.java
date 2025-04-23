package org.kelhenson.sortingalgorithms.controllers;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
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

    private BarChart<String, Number> barChart;
    private Slider numOfComputationsSlider;
    private Button startBtn;
    private Button homeBtn;

    public BarChart<String, Number> initValues(BarChart<String, Number> barChart, Slider slider, Button startBtn, Button homeBtn) {
        this.barChart = barChart;
        this.numOfComputationsSlider = slider;
        this.startBtn = startBtn;
        this.homeBtn = homeBtn;

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

    public void startOrPauseBtnClicked() {
        if (!isStarted) { //Click on Start
            isStarted = true;
            startBtn.setText("Pause");
            homeBtn.setDisable(true);
            barChartVisualSort(swapList.get(swapListIdx).entrySet().iterator().next());
        } else { //Click on Pause
            isStarted = false;
            startBtn.setDisable(true);
            startBtn.setText("Start");
        }
    }

    protected void navigateToHome() throws IOException {
        closeCurrAndOpenNewWindow(numOfComputationsSlider.getScene().getWindow(),
                "home.fxml", 400, 400, "Sorting Application", false);
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

    //********************************//
    //       BarChart animation       //
    //********************************//

    protected void barChartVisualSort(Map.Entry<Integer, Integer> swapMap) {
        if (isStarted) {
            ObservableList<XYChart.Data<String, Number>> data = barChart.getData().getFirst().getData();
            XYChart.Data<String, Number> sortedBar = data.stream().filter((e) -> e.getYValue().equals(swapMap.getKey())).toList().getFirst();
            XYChart.Data<String, Number> swappedBar = data.stream().filter((e) -> e.getYValue().equals(swapMap.getValue())).toList().getFirst();

            sortedBar.getNode().setStyle("-fx-background-color: green ;");
            swappedBar.getNode().setStyle("-fx-background-color: green ;");

            Animation swap = createSwapAnimation(sortedBar, swappedBar);
            swap.play();
            swap.setOnFinished((e) -> {
                try {
                    Thread.sleep((long) (1000 - (numOfComputationsSlider.getValue() * 100)));
                    sortedBar.getNode().setStyle("");
                    swappedBar.getNode().setStyle("");
                    if (swapListIdx++ < swapList.size() - 1) barChartVisualSort(swapList.get(swapListIdx).entrySet().iterator().next());
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
        if (!isStarted || swapListIdx >= swapList.size()-1) {
            homeBtn.setDisable(false); //if Paused OR End, enable btn
            if (!isStarted) startBtn.setDisable(false); //if Paused, enable btn
            else if (swapListIdx >= swapList.size()-1) startBtn.setDisable(true); //if End, disable btn
        }
    }

    private <T> Animation createSwapAnimation(XYChart.Data<?, T> first, XYChart.Data<?, T> second) {
        double sortedBarX = first.getNode().getParent().localToScene(first.getNode().getBoundsInParent()).getMinX();
        double swappedBarX = first.getNode().getParent().localToScene(second.getNode().getBoundsInParent()).getMinX();

        TranslateTransition firstTranslate = new TranslateTransition(Duration.millis(500), first.getNode());
        firstTranslate.setByX(swappedBarX - sortedBarX);
        TranslateTransition secondTranslate = new TranslateTransition(Duration.millis(500), second.getNode());
        secondTranslate.setByX(sortedBarX - swappedBarX);
        return new ParallelTransition(firstTranslate, secondTranslate);
    }
}
