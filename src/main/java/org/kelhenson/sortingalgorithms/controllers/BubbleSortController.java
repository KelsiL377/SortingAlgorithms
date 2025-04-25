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

    private final static String MSG_TXT = "\"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece" +
            " of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin " +
            "professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, " +
            "consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical " +
            "literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 " +
            "of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. " +
            "This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line " +
            "of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\"";

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
    //           Merge Sort           //
    //********************************//

    public void bubbleSort(List<Integer> list, int numOfItr) {
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