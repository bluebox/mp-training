package com.library_management.utilities;

import java.awt.event.PaintEvent;

import javafx.animation.PauseTransition;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ResponseHandler {
	public static void showResponse(Text block, String message, Paint color) {
		block.setText(message);
		block.setFill(color);
		PauseTransition pauseTransition=new PauseTransition(Duration.seconds(4));
		pauseTransition.setOnFinished(event -> block.setText(""));
		pauseTransition.play();
	}
}
