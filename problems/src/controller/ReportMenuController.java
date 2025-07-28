package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReportMenuController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
    @FXML
    private void handleOverdueBooks(ActionEvent event) throws IOException {
    	System.out.println("reachedme");
    	root= FXMLLoader.load(getClass().getResource("/applicationview/OverdueBooks.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
    }

    @FXML
    private void handleBooksPerCategory(ActionEvent event) throws IOException {
    	System.out.println("reachedme");
    	root= FXMLLoader.load(getClass().getResource("/applicationview/BooksPerCategory.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
    }

    @FXML
    private void handleActiveIssuedBooks(ActionEvent event) throws IOException {
    	System.out.println("reachedme");
    	root= FXMLLoader.load(getClass().getResource("/applicationview/ActiveIssuedMembers.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show(); 
    }

  
}
