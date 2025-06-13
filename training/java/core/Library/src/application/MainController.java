package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void handleAddBook(ActionEvent event) {
        System.out.println("Add Book button clicked");
        System.out.println("Add book details");
    }

    @FXML
    private void handleAddMember(ActionEvent event) {
        System.out.println("Add Member button clicked");
        // Add your code here to open Add Member UI
    }

    @FXML
    private void handleIssueReturn(ActionEvent event) {
        System.out.println("Issue/Return button clicked");
        // Add your code here to open Issue/Return UI
    }

    @FXML
    private void handleReports(ActionEvent event) {
        System.out.println("Reports button clicked");
        // Add your code here to open Reports UI
        //use enums for active and inacctive ,available not availabale and gender feiilds
        //try to use concepts of core java like inner classess,generics,interfaces and abstract classess
        
    }
}

