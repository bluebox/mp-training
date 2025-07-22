module javaFxintro.app {
    requires javafx.controls;
    requires javafx.fxml;

    opens javaFxintro.app to javafx.fxml;
    exports javaFxintro.app;
}
