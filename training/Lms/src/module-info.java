module LmsFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    

    requires java.sql; 

    requires org.junit.jupiter.api;

  
    
    opens com.lms.controller to javafx.fxml;
    opens com.lms.model to javafx.base; 

    exports com.lms.ui;
    exports com.lms.controller;
    exports com.lms.model;
}
