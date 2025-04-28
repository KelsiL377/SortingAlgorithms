package org.kelhenson.sortingalgorithms.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BubbleSortController extends AppController {

    @FXML
    private Slider numOfComputationsSlider;

    @FXML
    private Button startBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private BarChart<String, Number> barChart;

    private final static String MSG_TXT = "The Bubble Sort algorithm is a simple sorting algorithm. It works by repeatedly " +
            "comparing adjacent items and swapping them if they are in the wrong order. In this way, bigger items " +
            "\"bubble up\" and are eventually sorted to the top. Repeated \"bubbles\" will cause the whole list to be " +
            "order from smallest to largest. \n\nThe Big O time complexity (or the worst case perfomance) is O(n^2), meaning" +
            " as the items to be sorted grows, the time to sort them will grow proportionally, or the number of items " +
            "squared. The more items, the longer the time needed, so if you have a huge list, another sort algorithm such " +
            "as the merge sort, heap sort, or the quick sort would be more time efficient on average.";

    public void initialize() {
        barChart = super.initValues(barChart, numOfComputationsSlider, startBtn, homeBtn);
        bubbleSort(new ArrayList<>(sortList), 1);
    }

    //********************************//
    //         FXML Listeners         //
    //********************************//

    @FXML
    protected void onInfoButtonClick() {
        displayInfoMsg("Bubble Sort Algorithm", MSG_TXT);
    }

    @FXML
    protected void onStartButtonClick() { startOrPauseBtnClicked(); }

    @FXML
    protected void onHomeButtonClick() throws IOException { navigateToHome(); }

    //********************************//
    //           Bubble Sort          //
    //********************************//

    private void bubbleSort(List<Integer> list, int numOfItr) {
        if(numOfItr >= list.size()) return;

        //loop through list, swap smaller indexes with larger adjacent ones
        for (int i=0; i<list.size()-numOfItr; i++) {
            if (list.get(i) > list.get(i+1)) {
                swap(list, i, list.get(i), list.get(i+1));
            }
        }
        //Recursive calls to bubbleSort
        bubbleSort(list, ++numOfItr);
    }

    private void swap(List<Integer> list, int idx, int swappedValue, int sortedValue) {
        list.set(idx, sortedValue);
        list.set(idx+1, swappedValue);
        swapList.add(new HashMap<>() {{ put(swappedValue, sortedValue); }});
    }
}