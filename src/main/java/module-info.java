module org.kelhenson.sortingalgorithms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.kelhenson.sortingalgorithms to javafx.fxml;
    exports org.kelhenson.sortingalgorithms;
}