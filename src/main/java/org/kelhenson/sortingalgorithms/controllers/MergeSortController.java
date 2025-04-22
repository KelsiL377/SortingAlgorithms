package org.kelhenson.sortingalgorithms.controllers;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeSortController extends AppController {

    @FXML
    private Slider numOfComputationsSlider;

    @FXML
    private Button startBtn;

    @FXML
    private BarChart<String, Number> barChart;

    private final String msgTxt = "\"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece" +
            " of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin " +
            "professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, " +
            "consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical " +
            "literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 " +
            "of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. " +
            "This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line " +
            "of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\"";

    public void initialize() {
        barChart = super.initValues(barChart);
        mergeSortList = mergeSort(new ArrayList<>(mergeSortList), 0);
    }

    //********************************//
    //         FXML Listeners         //
    //********************************//

    @FXML
    protected void onInfoButtonClick() {
        displayInfoMsg("Merge Sort Algorithm", msgTxt);
    }

    @FXML
    protected void onStartButtonClick() {
        if (!isStarted) {
            isStarted = true;
            startBtn.setText("Pause");
            barChartVisualSort(swapList.get(swapListIdx).entrySet().iterator().next());
        } else {
            isStarted = false;
            startBtn.setText("Start");
        }
    }

    @FXML
    protected void onHomeButtonClick() throws IOException {
        closeCurrAndOpenNewWindow(numOfComputationsSlider.getScene().getWindow(),
                "home.fxml", 400, 400, "Sorting Application", false);
    }

    //********************************//
    //           Merge Sort           //
    //********************************//

    public List<Integer> mergeSort(List<Integer> list, int idx) {
        int k = 0, i = 0, j = 0;
        int listSize = list.size();
        if (listSize < 2) return list;
        int midIdx = listSize / 2;

        //Initialize left and right lists
        List<Integer> lList = new ArrayList<>();
        List<Integer> rList = new ArrayList<>();
        for (int l = 0; l < midIdx; l++) lList.add(list.get(l));
        for (int r = midIdx; r < listSize; r++) rList.add(list.get(r));

        //Recursive calls to mergeSort and then to merge the two lists together
        mergeSort(lList, idx);
        mergeSort(rList, idx + midIdx);
        list = merge(list, lList, rList, k, i, j, idx);
        return list;
    }

    public List<Integer> merge(List<Integer> list, List<Integer> lList, List<Integer> rList, int k, int i, int j, int idx)
    {
        while (i<lList.size() || j<rList.size()) {
            if (i>=lList.size()) list.set(k, rList.get(j++));
            else if (j>=rList.size()) list.set(k, lList.get(i++));
            else {
                if (lList.get(i) < rList.get(j)) list.set(k, lList.get(i++));
                else list.set(k, rList.get(j++));
            }
            updateMergeSortList(list.get(k), idx+k++);
        }
        return list;
    }

    private void updateMergeSortList(int sortedValue, int sortedIdx) {
        int swappedIdx =  mergeSortList.indexOf(sortedValue);
        int swappedValue = mergeSortList.get(sortedIdx);
        if (sortedValue != swappedValue) {
            mergeSortList.set(sortedIdx, sortedValue);
            mergeSortList.set(swappedIdx, swappedValue);
            swapList.add(new HashMap<>() {{ put(sortedValue, swappedValue); }});
        }
    }

    //********************************//
    //       BarChart animation       //
    //********************************//

    private synchronized void barChartVisualSort(Map.Entry<Integer, Integer> swapMap) {
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
                    else startBtn.setDisable(true);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });
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