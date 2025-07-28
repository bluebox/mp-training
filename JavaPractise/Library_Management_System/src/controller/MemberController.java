package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class MemberController {

    @FXML
    private void handleAddMember(ActionEvent event) throws Exception {
        loadFXML(event, "/resources/AddMember.fxml");
    }

    @FXML
    private void handleUpdateMember(ActionEvent event) throws Exception {
        loadFXML(event, "/resources/UpdateMember.fxml");
    }
    
    @FXML
    private void handleViewMembers(ActionEvent event) throws Exception {
        loadFXML(event, "/resources/ViewMembers.fxml");
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