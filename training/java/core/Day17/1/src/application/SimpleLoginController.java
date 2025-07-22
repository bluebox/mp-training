package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SimpleLoginController {
	 @FXML
     private Button myButton;
	 
	 @FXML 
	 private Text target;
	 
	 @FXML
	 private TextField username;
	 
	 @FXML
	 private TextField password;
	 
	 int count=0;
     @FXML
     public void initialize() {
         myButton.setOnAction(event -> {
        	 target.setText(("babu@gmail.com".equals(username.getText())&&"babu@123".equals(password.getText()))?"Success":"Failed");
        	 if(target.getText().equals("Failed"))count++;
        	 if(count>3)
        	 {
        		 target.setText("Failed more than 3 times.");
        		 myButton.setDisable(true);
        	 }
        	 System.out.println("button clicked");
         });
     }
	
}
