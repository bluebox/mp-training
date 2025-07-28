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
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class MainPageController {

    @FXML
    private StackPane rootPane;

    @FXML
    public void switchToBooksForm(ActionEvent event) {
        try {
            // Load the content to embed in the popup
            FXMLLoader contentLoader = new FXMLLoader(getClass().getResource("/com/LibraryManagement/application/books/BooksForm.fxml"));
            Region popupContent = contentLoader.load();

            popupContent.setStyle("-fx-background-color: #ffffff; -fx-padding: 30; -fx-border-radius: 10; -fx-background-radius: 10;");
            popupContent.setPrefWidth(600);  
            popupContent.setPrefHeight(400); 
            popupContent.setEffect(new DropShadow(15, Color.DARKGRAY));

            // Create Close button
            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> rootPane.getChildren().remove(rootPane.getChildren().size() - 1));
            closeButton.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;");
            closeButton.setPrefWidth(120);

            VBox popupBox = new VBox(20, popupContent, closeButton);
            popupBox.setAlignment(Pos.CENTER);
            popupBox.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 40; -fx-border-radius: 12; -fx-background-radius: 12;");
            popupBox.setEffect(new DropShadow(10, Color.GRAY));
            popupBox.setPrefWidth(700);  
            popupBox.setPrefHeight(550); 

            AnchorPane popupOverlay = new AnchorPane(popupBox);
            popupOverlay.setStyle("-fx-background-color: rgba(0,0,0,0.4);");

            AnchorPane.setTopAnchor(popupBox, 50.0);
            AnchorPane.setLeftAnchor(popupBox, 50.0);
            AnchorPane.setRightAnchor(popupBox, 50.0);
            AnchorPane.setBottomAnchor(popupBox, 50.0);

            rootPane.getChildren().add(popupOverlay);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void switchToMembersForm(ActionEvent event) {
        try {
            // Load the content to embed in the popup
            FXMLLoader contentLoader = new FXMLLoader(getClass().getResource("/com/LibraryManagement/application/members/MembersForm.fxml"));
            Region popupContent = contentLoader.load();

            popupContent.setStyle("-fx-background-color: #ffffff; -fx-padding: 30; -fx-border-radius: 10; -fx-background-radius: 10;");
            popupContent.setPrefWidth(750);  
            popupContent.setPrefHeight(400); 
            popupContent.setEffect(new DropShadow(15, Color.DARKGRAY));

            // Create Close button
            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> rootPane.getChildren().remove(rootPane.getChildren().size() - 1));
            closeButton.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;");
            closeButton.setPrefWidth(120);

            VBox popupBox = new VBox(20, popupContent, closeButton);
            popupBox.setAlignment(Pos.CENTER);
            popupBox.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 40; -fx-border-radius: 12; -fx-background-radius: 12;");
            popupBox.setEffect(new DropShadow(10, Color.GRAY));
            popupBox.setPrefWidth(800);  
            popupBox.setPrefHeight(550); 

            AnchorPane popupOverlay = new AnchorPane(popupBox);
            popupOverlay.setStyle("-fx-background-color: rgba(0,0,0,0.4);");

            AnchorPane.setTopAnchor(popupBox, 50.0);
            AnchorPane.setLeftAnchor(popupBox, 50.0);
            AnchorPane.setRightAnchor(popupBox, 50.0);
            AnchorPane.setBottomAnchor(popupBox, 50.0);

            rootPane.getChildren().add(popupOverlay);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    public void switchToIssueForm(ActionEvent event) {
        try {
            // Load the content to embed in the popup
            FXMLLoader contentLoader = new FXMLLoader(getClass().getResource("/com/LibraryManagement/application/IssueReturn/IssueReturn.fxml"));
            Region popupContent = contentLoader.load();

            popupContent.setStyle("-fx-background-color: #ffffff; -fx-padding: 30; -fx-border-radius: 10; -fx-background-radius: 10;");
            popupContent.setPrefWidth(750);  
            popupContent.setPrefHeight(400); 
            popupContent.setEffect(new DropShadow(15, Color.DARKGRAY));

            // Create Close button
            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> rootPane.getChildren().remove(rootPane.getChildren().size() - 1));
            closeButton.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;");
            closeButton.setPrefWidth(120);

            VBox popupBox = new VBox(20, popupContent, closeButton);
            popupBox.setAlignment(Pos.CENTER);
            popupBox.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 40; -fx-border-radius: 12; -fx-background-radius: 12;");
            popupBox.setEffect(new DropShadow(10, Color.GRAY));
            popupBox.setPrefWidth(800);  
            popupBox.setPrefHeight(550); 

            AnchorPane popupOverlay = new AnchorPane(popupBox);
            popupOverlay.setStyle("-fx-background-color: rgba(0,0,0,0.4);");

            AnchorPane.setTopAnchor(popupBox, 50.0);
            AnchorPane.setLeftAnchor(popupBox, 50.0);
            AnchorPane.setRightAnchor(popupBox, 50.0);
            AnchorPane.setBottomAnchor(popupBox, 50.0);

            rootPane.getChildren().add(popupOverlay);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleClose(ActionEvent event) {
        Node node = (Node) event.getSource();
        rootPane.getChildren().remove(node.getParent().getParent());
    }
}
