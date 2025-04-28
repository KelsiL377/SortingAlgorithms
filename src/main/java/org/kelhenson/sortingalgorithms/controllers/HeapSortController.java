package org.kelhenson.sortingalgorithms.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HeapSortController extends AppController {

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
        System.out.println("init before -> " + sortList);
        sortList = heapSort(new ArrayList<>(sortList), sortList.size()-1);
        System.out.println("init after -> " + sortList);
    }

    //********************************//
    //         FXML Listeners         //
    //********************************//

    @FXML
    protected void onInfoButtonClick() {
        displayInfoMsg("Heap Sort Algorithm", MSG_TXT);
    }

    @FXML
    protected void onStartButtonClick() { startOrPauseBtnClicked(); }

    @FXML
    protected void onHomeButtonClick() throws IOException { navigateToHome(); }

    //********************************//
    //           Heap Sort            //
    //********************************//

    public List<Integer> heapSort(List<Integer> list, int sortedIdx) {
        if (sortedIdx < 1) return list;
        heapify(list, 0, sortedIdx);
        swap(list, sortedIdx, 0, list.get(sortedIdx), list.getFirst());
        heapSort(list, --sortedIdx);
        return list;
    }

    private void heapify(List<Integer> list, int currIdx, int sortedIdx) {
        int leftChildIdx = (currIdx*2) + 1;
        int rightChildIdx = (currIdx*2) + 2;
        if (leftChildIdx > sortedIdx && rightChildIdx > sortedIdx) return;

        //Recursive calls to mergeSort and then to merge the two lists together
        if (leftChildIdx <= sortedIdx) {
            heapify(list, leftChildIdx, sortedIdx);
            checkToSwap(list, currIdx, leftChildIdx);
        }
        if (rightChildIdx <= sortedIdx) {
            heapify(list, rightChildIdx, sortedIdx);
            checkToSwap(list, currIdx, rightChildIdx);
        }
    }

    private void checkToSwap(List<Integer> list, int currIdx, int childIdx) {
        int currValue =  list.get(currIdx);
        int childValue = list.get(childIdx);
        if (currValue < childValue) swap(list, currIdx, childIdx, currValue, childValue);
    }

    private void swap(List<Integer> list, int sortedIdx, int swappedIdx, int swappedValue, int sortedValue) {
        list.set(sortedIdx, sortedValue);
        list.set(swappedIdx, swappedValue);
        swapList.add(new HashMap<>() {{ put(sortedValue, swappedValue); }}); //color both and swap
    }
}