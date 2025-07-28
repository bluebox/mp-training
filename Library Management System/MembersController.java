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

    private void showPopup(String contentFxmlPath) {
        try {
            FXMLLoader contentLoader = new FXMLLoader(getClass().getResource(contentFxmlPath));
            Region popupContent = contentLoader.load();

            double popupWidth = 600;
            double popupHeight = 400;

            

            popupContent.setStyle("-fx-background-color: #ffffff; -fx-padding: 30; -fx-border-radius: 10; -fx-background-radius: 10;");
            popupContent.setPrefWidth(popupWidth);
            popupContent.setPrefHeight(popupHeight);
            popupContent.setEffect(new DropShadow(15, Color.DARKGRAY));

            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> rootPane.getChildren().remove(rootPane.getChildren().size() - 1));
            closeButton.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;");
            closeButton.setPrefWidth(120);

            VBox popupBox = new VBox(20, popupContent, closeButton);
            popupBox.setAlignment(Pos.CENTER);
            popupBox.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 40; -fx-border-radius: 12; -fx-background-radius: 12;");
            popupBox.setEffect(new DropShadow(10, Color.GRAY));
            popupBox.setPrefWidth(popupWidth + 100);  // slightly larger box to wrap around content
            popupBox.setPrefHeight(popupHeight + 100);

            AnchorPane popupOverlay = new AnchorPane(popupBox);
            popupOverlay.setStyle("-fx-background-color: rgba(0,0,0,0.4);");

            AnchorPane.setTopAnchor(popupBox, 50.0);
            AnchorPane.setBottomAnchor(popupBox, 50.0);
            AnchorPane.setLeftAnchor(popupBox, 50.0);
            AnchorPane.setRightAnchor(popupBox, 50.0);

            rootPane.getChildren().add(popupOverlay);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToAddMemberForm(ActionEvent event) {
        showPopup("/com/LibraryManagement/application/members/AddMemberForm.fxml");
    }

    @FXML
    public void switchToViewMembers(ActionEvent event) {
        showPopup("/com/LibraryManagement/application/members/ViewMembersForm.fxml");
    }
}
