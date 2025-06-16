package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
public class HelloController {
	 @FXML // Annotation to inject the Label from FXML
	    private Label messageLabel;

	    private int clickCount = 0;

	    // This method is called when the "Click Me!" button is pressed
	    @FXML
	    private void handleButtonClick(ActionEvent event) {
	        clickCount++;
	        messageLabel.setText("You clicked the button " + clickCount + " time(s)!");
	    }

	    // This method is called when the "Reset" button is pressed
	    @FXML
	    private void handleResetClick(ActionEvent event) {
	        clickCount = 0;
	        messageLabel.setText("Welcome to JavaFX!");
	    }
}
