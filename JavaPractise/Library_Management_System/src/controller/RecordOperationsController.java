package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class RecordOperationsController {

    @FXML
    private void handleOverDueBooks(ActionEvent event) throws Exception {
        loadFXML(event, "/resources/OverdueBooks.fxml");
    }

    @FXML
    private void handleBookCountPerCategory(ActionEvent event) throws Exception {
        loadFXML(event, "/resources/BooksPerCategory.fxml");
    }

    @FXML
    private void handleActiveIssuedMembers(ActionEvent event) throws Exception {
        loadFXML(event, "/resources/ActiveIssuedMembers.fxml");
    }

    @FXML
    private void handleGoHome(ActionEvent event) throws Exception {
        loadFXML(event, "/resources/Main.fxml");
    }


    private void loadFXML(ActionEvent event, String path) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
