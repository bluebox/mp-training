package library.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class TestController {

    @FXML
    private Label welcomeLabel;
    @FXML
    private Label messageLabel; 

    @FXML
    public void initialize() {
        welcomeLabel.setText("Welcome to JavaFX!");
    }

    @FXML
    private void handleClick() {
        messageLabel.setText("Button was clicked!");
    }
}
