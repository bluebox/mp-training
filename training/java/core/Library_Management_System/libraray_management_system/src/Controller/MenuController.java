package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	public void getBooks(ActionEvent event) throws IOException {
		root= FXMLLoader.load(getClass().getResource("/applicationview/Books.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
public void getMembers(ActionEvent event) throws IOException {
	root= FXMLLoader.load(getClass().getResource("/applicationview/viewallmembers.fxml"));
    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    scene=new Scene(root);
    stage.setScene(scene);
    stage.show();
	}

public void getIssuesRecords(ActionEvent event) throws IOException {
	root= FXMLLoader.load(getClass().getResource("/applicationview/Issueprintallrecords.fxml"));
    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    scene=new Scene(root);
    stage.setScene(scene);
    stage.show();
}

public void getReports(ActionEvent event) throws IOException {
	root= FXMLLoader.load(getClass().getResource("/applicationview/ReportsMenu.fxml"));
    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    scene=new Scene(root);
    stage.setScene(scene);
    stage.show();
}

}
