package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Domain.Member;
import Domain.Gender;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Service.ServiceLayer;

public class AddMemberController implements Initializable {
	ServiceLayer service=new ServiceLayer();
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    public  TextField  nameField, emailField, mobileField, addressField;
    @FXML
    public ComboBox<String> genderBox;
    

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> items = FXCollections.observableArrayList(
            "Male","Female"
        );
        genderBox.setItems(items);
    }
    private void showAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
    
    public void handleAddMember(ActionEvent event) throws Exception{
    	service.registerMember(new Member(0,nameField.getText(),emailField.getText(),mobileField.getText(),Gender.getGender(genderBox.getValue().toUpperCase().charAt(0)),addressField.getText()));
    }
    
    
    public void gotoHome(ActionEvent event) throws IOException {
 	   root= FXMLLoader.load(getClass().getResource("/applicationview/HomeView.fxml"));
 	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
 	    stage.setTitle("Library Management System");
 	    scene=new Scene(root);
 	    stage.setScene(scene);
 	    stage.show();
    }
}
