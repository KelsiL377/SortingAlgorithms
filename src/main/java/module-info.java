module org.kelhenson.sortingalgorithms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.kelhenson.sortingalgorithms to javafx.fxml;
    exports org.kelhenson.sortingalgorithms;
    exports org.kelhenson.sortingalgorithms.controllers;
    opens org.kelhenson.sortingalgorithms.controllers to javafx.fxml;
}