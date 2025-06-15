/**
 * 
 */
/**
 * 
 */
module LibraryManagementSystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
	requires org.junit.jupiter.api;

    opens view to javafx.fxml;
    opens controllerr to javafx.fxml;

    exports controllerr;
    exports model;
}




