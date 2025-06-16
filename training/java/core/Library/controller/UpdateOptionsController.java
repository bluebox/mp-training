package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UpdateOptionsController {

    public void handleUpdateDetails(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateBook.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Update Book Details");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleUpdateAvailability(ActionEvent event) {

        System.out.println("Update Options Controller Update Availability Clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/updateAvailable.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Update Availability Details");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleDelete(ActionEvent event){
        System.out.println("DElete function");
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/deleteBook.fxml"));
            Stage stage=new Stage();
            stage.setTitle("Delete Book");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}

