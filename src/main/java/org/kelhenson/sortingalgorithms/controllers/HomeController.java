package org.kelhenson.sortingalgorithms.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController extends AppController {
    @FXML
    private Button mergeSortBtn;

    @FXML
    private Button quickSortBtn;

    @FXML
    protected void onMergeSortButtonClick() throws IOException {
        closeCurrAndOpenNewWindow(mergeSortBtn.getScene().getWindow(),
                "merge-sort.fxml", 1000, 500, "Merge Sort", true);
    }

    @FXML
    protected void onBubbleSortButtonClick() {
        System.out.println("Bubble btn clicked");
    }

    @FXML
    protected void onHeapSortButtonClick() {
        System.out.println("Heap btn clicked");
    }

    @FXML
    protected void onQuickSortButtonClick() throws IOException {
        closeCurrAndOpenNewWindow(quickSortBtn.getScene().getWindow(),
                "quick-sort.fxml", 1000, 500, "Quick Sort", true);
    }

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }
}