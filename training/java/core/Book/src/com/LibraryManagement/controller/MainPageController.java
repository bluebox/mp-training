package com.LibraryManagement.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class MainPageController {

	@FXML
	private StackPane rootPane;

	@FXML
	public void switchToBooksForm(ActionEvent event) {
		try {
			MainController.switchScene("books/BooksForm.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void switchToMembersForm(ActionEvent event) {
		try {
			MainController.switchScene("members/MembersForm.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void switchToIssueForm(ActionEvent event) {
		try {
			MainController.switchScene("IssueReturn/IssueReturn.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void switchToReportsForm(ActionEvent event) {
		try {
			MainController.switchScene("reports/reportsForm.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
