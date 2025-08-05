package library.ui;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class MainScreenController {

    @FXML
    private Label statusLabel;

    
    @FXML
    private void handleAddBook(ActionEvent event) {
//    	 statusLabel.setText("Add Books functionality Not Added.");
//         statusLabel.setTextFill(javafx.scene.paint.Color.ORANGE);
//         System.out.println("Add Books button clicked.");
        loadScene("AddBookForm.fxml", "Add New Book", event);
    }

   
    @FXML
    private void handleViewBooks(ActionEvent event) {
//        statusLabel.setText("View Books functionality Not Added.");
//        statusLabel.setTextFill(javafx.scene.paint.Color.ORANGE);
//        System.out.println("View Books button clicked.");
        loadScene("ViewBooksScreen.fxml", "View All Books", event);

    }

   
    @FXML
    private void handleAddMember(ActionEvent event) {
//        statusLabel.setText("Add Member functionality Not Added.");
//        statusLabel.setTextFill(javafx.scene.paint.Color.ORANGE);
//        System.out.println("Add Member button clicked.");
        loadScene("AddMember.fxml", "Add New Member", event);

    }

  
    @FXML
    private void handleViewMembers(ActionEvent event) {
//        statusLabel.setText("View Members functionality Not Added.");
//        statusLabel.setTextFill(javafx.scene.paint.Color.ORANGE);
//        System.out.println("View Members button clicked.");
        loadScene("ViewMembers.fxml", "View Members", event);

    }

    
    @FXML
    private void handleIssueBook(ActionEvent event) {
//        statusLabel.setText("Issue Book functionality Not Added.");
//        statusLabel.setTextFill(javafx.scene.paint.Color.ORANGE);
//        System.out.println("Issue Book button clicked.");
        loadScene("IssueBookForm.fxml", "Library Management System - Issue Book",event);
    }

   
    @FXML
    private void handleReturnBook(ActionEvent event) {
//        statusLabel.setText("Return Book functionality Not Added.");
//        statusLabel.setTextFill(javafx.scene.paint.Color.ORANGE);
//        System.out.println("Return Book button clicked.");
        loadScene("ReturnBookForm.fxml", "Library Management System - Return Book", event);

    }

    
    @FXML
    private void handleViewIssueRecords(ActionEvent event) {
//        statusLabel.setText("View Issue Records functionality Not Added.");
//        statusLabel.setTextFill(javafx.scene.paint.Color.ORANGE);
//        System.out.println("View Issue Records button clicked.");
        loadScene("IssuedRecordsScreen.fxml", "Library Management System - All Issue Records", event);

    }

    
    @FXML
    private void handleReports(ActionEvent event) {
//        statusLabel.setText("Reports functionality Not Added.");
//        statusLabel.setTextFill(javafx.scene.paint.Color.ORANGE);
//        System.out.println("Reports button clicked.");
        loadScene("ReportsScreen.fxml", "Library Management System - Reports", event);

    }

    
    private void loadScene(String fxmlFileName, String stageTitle, ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            URL fxmlLocation = getClass().getClassLoader().getResource(fxmlFileName);
            if (fxmlLocation == null) {
                System.err.println("Error: FXML file not found: " + fxmlFileName);
                statusLabel.setText("Error: Could not load " + fxmlFileName);
                statusLabel.setTextFill(javafx.scene.paint.Color.RED);
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            
           
			Scene scene = new Scene(root, 950,600);
//            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(stageTitle);
            stage.show();

        } catch (IOException e) {
            System.err.println("Failed to load FXML file: " + fxmlFileName + " - " + e.getMessage());
            statusLabel.setText("Error loading screen: " + fxmlFileName);
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            e.printStackTrace();
        }
    }
}
