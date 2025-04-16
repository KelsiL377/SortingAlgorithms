package org.kelhenson.sortingalgorithms.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.kelhenson.sortingalgorithms.SortingApplication;

import java.io.IOException;

public class AppController {

    protected void closeCurrAndOpenNewWindow(Window window, String fxmlResource, int width, int height, String title, boolean isStageUndecorated) throws IOException {
        window.hide();
        FXMLLoader fxmlLoader = new FXMLLoader(SortingApplication.class.getResource(fxmlResource));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        if (isStageUndecorated) stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }



}
