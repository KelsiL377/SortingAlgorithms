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

    private final static String MSG_TXT = "The Heap sort algorithm is an efficient sorting method based on the heap data" +
            " structure. It is the most confusing of the sorts to understand. The list is first \"heapified\"," +
            " or put into a tree-like structure called a binary tree, where each item will have up to one parent node, " +
            "and up to two children nodes. By repeated comparison of parent and child nodes, the binary tree will be " +
            "sorted so the largest item in the tree will be sorted to the very top or root of the tree. Then, this top " +
            "node is removed from the tree to be placed in the list. This process is repeated until all the items have " +
            "been sorted. \n\nThe Big O time complexity of this sort algorithm (or the worst case performance) is quasilinear, " +
            "or O(n log n), meaning as the number of items to be sorted increases, the time to sort them will increase " +
            "almost linearly, but slightly more. In comparison to other sorts, it has same time performance as the merge " +
            "sort and quick sort but is faster than the bubble sort.";

    public void initialize() {
        barChart = super.initValues(barChart, numOfComputationsSlider, startBtn, homeBtn);
        sortList = heapSort(new ArrayList<>(sortList), sortList.size()-1);
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

    private List<Integer> heapSort(List<Integer> list, int sortedIdx) {
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

        //Recursive calls to heapify and then to check if parent and child node should be swapped
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