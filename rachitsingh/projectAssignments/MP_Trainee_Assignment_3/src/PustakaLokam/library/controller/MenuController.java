package PustakaLokam.library.controller;

import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class MenuController {
    private void openWindow(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("Error loading: " + fxmlPath);
            e.printStackTrace();
        }
    }

    @FXML
    private void onInsertBookClick(ActionEvent event) {
        openWindow("/PustakaLokam/library/ui/book/insert_book.fxml", "Add a New Book to the Library");
    }

    @FXML
    private void onUpdateBookClick(ActionEvent event) {
        openWindow("/PustakaLokam/library/ui/book/update_book.fxml", "Update Book Details");
    }

    @FXML
    private void onDisplayBooksClick(ActionEvent event) {
        openWindow("/PustakaLokam/library/ui/book/display_books.fxml", "All Registered Books of the Library");
    }
}
