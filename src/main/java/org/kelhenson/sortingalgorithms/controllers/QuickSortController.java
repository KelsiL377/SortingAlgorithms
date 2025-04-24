package org.kelhenson.sortingalgorithms.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuickSortController extends AppController {

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
        sortList = quickSort(new ArrayList<>(sortList));
    }

    //********************************//
    //         FXML Listeners         //
    //********************************//

    @FXML
    protected void onInfoButtonClick() {
        displayInfoMsg("Quick Sort Algorithm", MSG_TXT);
    }

    @FXML
    protected void onStartButtonClick() { startOrPauseBtnClicked(); }

    @FXML
    protected void onHomeButtonClick() throws IOException { navigateToHome(); }

    //********************************//
    //           Merge Sort           //
    //********************************//

    public List<Integer> quickSort(List<Integer> list) {
        if (list.size() < 2) return list;
        int pivotVal = list.getLast();
        swapList.add(new HashMap<>() {{ put(pivotVal, -1); }});

        //sort values smaller than pivot to the left side
        int sortedIdx = 0;
        for (int i=0; i<list.size()-1; i++) if (list.get(i) < pivotVal) swap(list, i, sortedIdx++);
        swap(list, list.indexOf(pivotVal), sortedIdx);

        //Recursive calls to quickSort to sort left and right of pivotValue, and then return sorted list
        quickSort(list.subList(0, sortedIdx)); //left of pivot
        quickSort(list.subList(sortedIdx+1, list.size())); //right of pivot
        return list;
    }

    public void swap(List<Integer> list, int swappedIdx, int sortedIdx)
    {
        int swappedValue = list.get(sortedIdx);
        int sortedValue = list.get(swappedIdx);
        if (sortedValue != swappedValue) {
            list.set(sortedIdx, sortedValue);
            list.set(swappedIdx, swappedValue);
            swapList.add(new HashMap<>() {{ put(sortedValue, swappedValue); }});
        }
    }
}