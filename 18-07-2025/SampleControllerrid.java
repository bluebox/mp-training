package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SampleControllerrid {
	@FXML private TextField nameField;
	@FXML private void handleButtonClick(ActionEvent e) {
	    System.out.println("Name: " + nameField.getText());
	}

}
