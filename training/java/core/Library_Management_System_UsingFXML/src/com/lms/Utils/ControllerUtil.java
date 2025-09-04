package com.lms.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControllerUtil {
	
	public static void createAlert(AlertType alertType,String title,String header,String content) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
