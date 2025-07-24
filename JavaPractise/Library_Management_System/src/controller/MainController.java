package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private void handleBookManagement(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/BookManagement.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    @FXML
    private void handleIssueReturnkManagement(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("/resources/IssueReturn.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    	
    }

    
}
