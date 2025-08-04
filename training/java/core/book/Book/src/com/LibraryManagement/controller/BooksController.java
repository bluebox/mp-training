package com.LibraryManagement.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class BooksController {

	@FXML
	public void Back(ActionEvent event) {
		try {
			MainController.switchScene("MainPageForm.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void switchToAddBookForm(ActionEvent event) {
		try {
			MainController.switchScene("books/AddBookForm.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void switchToViewBooks(ActionEvent event) {
		try {
			MainController.switchScene("books/ViewBooksForm.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
