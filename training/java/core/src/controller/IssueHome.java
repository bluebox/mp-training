package controller;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import application.Main;
import Domain.Member;
import Service.ServiceLayer;
import Domain.Gender;
public class IssueHome {
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	
	ServiceLayer memberService=new ServiceLayer();

	
	public void gotoHome(ActionEvent event) throws IOException {
		root= FXMLLoader.load(getClass().getResource("/applicationview/HomeView.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();		
	}
	
	public void addIssue(ActionEvent event) throws IOException {
		root= FXMLLoader.load(getClass().getResource("/applicationview/IssueBook.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	
	
	public void issueUpdate(ActionEvent event) throws IOException {
		root= FXMLLoader.load(getClass().getResource("/applicationview/Returnbook.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	
	public void getIssues(ActionEvent event) throws IOException {
		root= FXMLLoader.load(getClass().getResource("/applicationview/Issueprintallrecords.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
}

	
	
