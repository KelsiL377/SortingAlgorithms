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

    private final static String MSG_TXT = "The Quick sort algorithm is a \"divide and conquer\" method of sorting." +
            " By this, we mean that we take a list of items and repeatedly divide it into two sublists, based on a pivot" +
            " value. First, we pick an item as the \"pivot\". Then we sort all the smaller items to the left of the pivot, " +
            "and all the larger items to the right. For each sublist, we repeat the process, picking a pivot and sorting " +
            "into smaller and larger items compared to the pivot. In this way the whole list is sorted. \n\nThe Big O time " +
            "complexity of this sort algorithm (or the worst case performance) is quasilinear, or O(n log n), meaning as " +
            "the number of items to be sorted increases, the time to sort them will increase almost linearly, but " +
            "slightly more. In comparison to other sorts, it has same time performance as the merge sort and the heap " +
            "sort but is faster than the bubble sort.";

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
    //           Quick Sort           //
    //********************************//

    private List<Integer> quickSort(List<Integer> list) {
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

    private void swap(List<Integer> list, int swappedIdx, int sortedIdx) {
        int swappedValue = list.get(sortedIdx);
        int sortedValue = list.get(swappedIdx);
        if (sortedValue != swappedValue) {
            list.set(sortedIdx, sortedValue);
            list.set(swappedIdx, swappedValue);
            swapList.add(new HashMap<>() {{ put(sortedValue, swappedValue); }});
        }
    }
}