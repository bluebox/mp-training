package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class BookController {

    @FXML
    private void handleAddBook(ActionEvent event) throws Exception {
        loadFXML(event, "/resources/AddBook.fxml");
    }

    @FXML
    private void handleUpdateBook(ActionEvent event) throws Exception {
        loadFXML(event, "/resources/UpdateBook.fxml");
    }

    @FXML
    private void handleUpdateAvailability(ActionEvent event) throws Exception {
        loadFXML(event, "/resources/UpdateAvailability.fxml");
    }

    @FXML
    private void handleViewBooks(ActionEvent event) throws Exception {
        loadFXML(event, "/resources/ViewBooks.fxml");
    }

    @FXML
    private void handleBack(ActionEvent event) throws Exception {
        loadFXML(event, "/resources/Main.fxml");
    }

    private void loadFXML(ActionEvent event, String path) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
