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
        int swappedIdx =  sortList.indexOf(sortedValue);
        int swappedValue = sortList.get(sortedIdx);
        if (sortedValue != swappedValue) {
            sortList.set(sortedIdx, sortedValue);
            sortList.set(swappedIdx, swappedValue);
            swapList.add(new HashMap<>() {{ put(sortedValue, swappedValue); }});
        }
    }
}