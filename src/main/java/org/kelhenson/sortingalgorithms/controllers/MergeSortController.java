package org.kelhenson.sortingalgorithms.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MergeSortController extends AppController {

    @FXML
    private Slider numOfComputationsSlider;

    @FXML
    private Button startBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private BarChart<String, Number> barChart;

    private final static String MSG_TXT = "The Merge Sort algorithm is a popular sorting method known for its stability " +
            "and efficiency. It uses a \"divide and conquer\" method of sorting. By this, we mean that we take a list of " +
            "items and repeatedly divide it into two sublists until it can no longer be divided by 2. Then, we will " +
            "merge those lists back together by sorting all the items from both sublists into one sorted list. \n\nThe Big " +
            "O time complexity of this sort algorithm (or the worst case performance) is quasilinear, or O(n log n), " +
            "meaning as the number of items to be sorted increases, the time to sort them will increase almost linearly, " +
            "but slightly more. In comparison to other sorts, it has same time performance as the heap sort and quick " +
            "sort but is faster than the bubble sort.";

    public void initialize() {
        barChart = super.initValues(barChart, numOfComputationsSlider, startBtn, homeBtn);
        sortList = mergeSort(new ArrayList<>(sortList), 0);
    }

    //********************************//
    //         FXML Listeners         //
    //********************************//

    @FXML
    protected void onInfoButtonClick() {
        displayInfoMsg("Merge Sort Algorithm", MSG_TXT);
    }

    @FXML
    protected void onStartButtonClick() { startOrPauseBtnClicked(); }

    @FXML
    protected void onHomeButtonClick() throws IOException { navigateToHome(); }

    //********************************//
    //           Merge Sort           //
    //********************************//

    private List<Integer> mergeSort(List<Integer> list, int idx) {
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
        merge(list, lList, rList, k, i, j, idx);
        return list;
    }

    private void merge(List<Integer> list, List<Integer> lList, List<Integer> rList, int k, int i, int j, int idx) {
        while (i<lList.size() || j<rList.size()) {
            if (i>=lList.size()) list.set(k, rList.get(j++));
            else if (j>=rList.size()) list.set(k, lList.get(i++));
            else {
                if (lList.get(i) < rList.get(j)) list.set(k, lList.get(i++));
                else list.set(k, rList.get(j++));
            }
            updateMergeSortList(list.get(k), idx+k++);
        }
    }

    private void updateMergeSortList(int sortedValue, int sortedIdx) {
        int swappedIdx =  sortList.indexOf(sortedValue);
        int swappedValue = sortList.get(sortedIdx);
        if (sortedValue != swappedValue) {
            sortList.set(sortedIdx, sortedValue);
            sortList.set(swappedIdx, swappedValue);
            swapList.add(new HashMap<>() {{ put(sortedValue, swappedValue); }});
        }
    }
}