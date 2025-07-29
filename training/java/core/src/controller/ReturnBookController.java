package controller;

import java.io.IOException;

import Service.ServiceLayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class ReturnBookController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
    @FXML
    public TextField memberIdField, bookIdField;
@FXML
    public ServiceLayer issueService = new ServiceLayer();

    public void handleReturnBook() {
        try {
            int memberid = Integer.parseInt(memberIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());
            issueService.returnBook(bookId, memberid);
            showAlert("Book returned successfully.");
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
