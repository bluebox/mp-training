package com.LibraryManagement.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MembersController {
	
	@FXML
    private StackPane rootPane;
	@FXML
	public void Back(ActionEvent event) {
		try {
			MainController.switchScene("MainPageForm.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

        @FXML
    public void switchToAddMemberForm(ActionEvent event) {
        	try {
    			MainController.switchScene("members/AddMemberForm.fxml");
    		} catch (Exception e) {
    			e.printStackTrace();
    		}    }

    @FXML
    public void switchToViewMembers(ActionEvent event) {
    	try {
			MainController.switchScene("members/ViewMembersForm.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
