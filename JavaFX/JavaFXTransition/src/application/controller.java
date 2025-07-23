package application;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class controller implements Initializable{
	
	@FXML
	private Button helloButton;

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		ScaleTransition scale = new ScaleTransition(Duration.seconds(1), helloButton);
        scale.setByX(0.5);
        scale.setByY(0.5);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);


        helloButton.setOnMouseEntered(e -> scale.play());
		helloButton.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.out.println("Escape key disabled");
                event.consume();
            }else {
            	System.out.println(event.getText());
            }
        });

	}

}
