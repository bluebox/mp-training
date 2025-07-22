package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class SampleController {
	@FXML
	private Circle myCircle;
	private double X;
	private double Y;
	public void up(ActionEvent e) {
		myCircle.setCenterY(Y-=5);
	}
	public void down(ActionEvent e) {
		myCircle.setCenterY(Y+=5);
	}
	public void right(ActionEvent e) {
		myCircle.setCenterX(X+=5);
	}
	public void left(ActionEvent e) {
		myCircle.setCenterX(X-=5);
	}
}
