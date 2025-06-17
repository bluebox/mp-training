package com.library.util;
import javafx.scene.control.Alert;
public class AlertMsg {
    public static void showError(String msg) {
    	Alert alert=new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
    	alert.setContentText(msg);
    	alert.showAndWait();
    	}
}
