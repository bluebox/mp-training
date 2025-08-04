package com.LibraryManagement.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class IssueReturnController {

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
	private void handleIssueBook() {
		try {
			MainController.switchScene("IssueReturn/IssueBookForm.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}	}

	@FXML
	private void handleReturnBook() {
		try {
			MainController.switchScene("IssueReturn/ReturnBookForm.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}	}

	@FXML
	private void handleViewIssues() {
		try {
			MainController.switchScene("IssueReturn/ViewIssuesForm.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
}}
