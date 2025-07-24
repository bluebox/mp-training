module LibraryManagementSystem {

    requires javafx.controls; 
    requires javafx.fxml;     
    requires javafx.graphics; 
    requires java.sql;
    opens controller to javafx.fxml;
    opens resources to javafx.fxml;
    opens domain to javafx.base;

    exports main;
    exports controller;

}