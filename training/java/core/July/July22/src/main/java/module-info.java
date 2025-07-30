module TodoList.July22 {
    requires javafx.controls;
    requires javafx.fxml;

    opens TodoList.July22 to javafx.fxml;
    exports TodoList.July22;
}
