package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import Domain.IssueStatus;
import Service.ServiceLayer;

public class IssueBookController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	
    @FXML
    public  TextField bookIdField, memberIdField;
     @FXML
    public ServiceLayer issueService = new ServiceLayer();

    public void handleIssueBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            int memberId = Integer.parseInt(memberIdField.getText());
             LocalDate returndate=LocalDate.now().plusDays(14);
            issueService.createBookIssue(bookId, memberId,IssueStatus.ISSUED,LocalDate.now(),returndate);
        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
        }
    }
    
    
 // for going to books
    public void gotoHome(ActionEvent event) throws IOException {
 	   root= FXMLLoader.load(getClass().getResource("/applicationview/HomeView.fxml"));
 	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
 	    stage.setTitle("Library Management System");
 	    scene=new Scene(root);
 	    stage.setScene(scene);
 	    stage.show();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}
