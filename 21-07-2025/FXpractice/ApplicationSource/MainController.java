package application;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
	@FXML
	private Label mymsg;
	
	public void generateRandomNumber(@SuppressWarnings("exports") ActionEvent event) {
		
		Random random=new Random();
		int myvalue=random.nextInt(100)+1;
		mymsg.setText(Integer.toString(myvalue));
		System.out.println(Integer.toString(myvalue));
	}

}
