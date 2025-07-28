package Controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReportsController {
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	public void handleOverdueBooks(ActionEvent e) {
		Parent root=null;
		try {
			root = FXMLLoader.load(getClass().getResource("/applicationView/Overduebooks.fxml"));
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		stage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
	}
	public void handleBooksPerCategory(ActionEvent e) {
		Parent root=null;
		try {
			root = FXMLLoader.load(getClass().getResource("/applicationView/BooksPerCategory.fxml"));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		stage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
	}
	
	
	public void handleActiveIssuedBooks(ActionEvent e) {
		Parent root=null;
		try {
			root = FXMLLoader.load(getClass().getResource("/applicationView/ActiveIssuedBooks.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		stage=(Stage)((Node)e.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
	}
	@FXML
	private void handleBackToHome(ActionEvent event) throws IOException {
	    Parent homeView = FXMLLoader.load(getClass().getResource("/applicationView/HomeView.fxml"));
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(new Scene(homeView));
	    stage.show();
	}


}