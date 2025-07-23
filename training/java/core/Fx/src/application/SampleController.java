package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SampleController {
	@FXML
	private Button SubmitButton;
	@FXML
	private Label PrintLabel;
	
	@FXML
	private void Onbuttonclick(ActionEvent event) {
		//System.out.println("Thank You");
		PrintLabel.setText("THANKS");
	}
	
}
