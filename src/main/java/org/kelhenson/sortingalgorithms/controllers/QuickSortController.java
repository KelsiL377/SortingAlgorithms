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
        System.out.println("initialize before -> " + sortList);
        sortList = quickSort(new ArrayList<>(sortList));
        System.out.println("initialize after -> " + sortList);
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

    public List<Integer> quickSort(List<Integer> list) {
//        int k = 0, i = 0, j = 0;
        System.out.println("QuickSort -> " + list);
        if (list.size() < 2) return list;
        int pivotVal = list.getFirst();

        //Initialize left and right lists
        List<Integer> lList = new ArrayList<>();
        List<Integer> rList = new ArrayList<>();
        for (int i=1; i<list.size(); i++) {
            if (list.get(i) < pivotVal) lList.add(list.get(i));
            else rList.add(list.get(i));
        }

        //Recursive calls to mergeSort and then to merge the two lists together
        System.out.println("QuickSort lList -> " + lList);
        System.out.println("QuickSort rList -> " + rList);
        lList = quickSort(lList);
        rList = quickSort(rList);
        return merge(pivotVal, lList, rList);
    }

    public List<Integer> merge(int pivotValue, List<Integer> lList, List<Integer> rList)
    {
        lList.add(pivotValue);
        lList.addAll(rList);
//        updateMergeSortList(list.get(k), idx+k++);
        System.out.println("merge -> " + lList);
        return lList;
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