package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class CircleMoveController {
	
	@FXML
	private Circle mycircle;
	private double x;
	private double y;
	
	public void up(ActionEvent e) {
		mycircle.setCenterY(y-=10);
	}
	
	public void down(ActionEvent e) {
		mycircle.setCenterY(y+=10);
	}

	public void left(ActionEvent e) {
		mycircle.setCenterX(x-=10);
	}

	public void right(ActionEvent e) {
		mycircle.setCenterX(x+=10);
	}
}
