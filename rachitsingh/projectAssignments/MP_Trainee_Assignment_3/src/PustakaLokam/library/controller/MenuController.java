package PustakaLokam.library.controller;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

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
    private void openDeleteBookWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PustakaLokam/library/ui/book/delete_book.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Delete Book");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onInsertBookClick(ActionEvent event) {
        openWindow("/PustakaLokam/library/ui/book/insert_book.fxml", "Add a New Book to the Library");
    }

    @FXML
    private void onUpdateBookClick(ActionEvent event) {
        openWindow("/PustakaLokam/library/ui/book/book_list.fxml", "Select a Book to Update");
    }

    @FXML
    private void onDisplayBooksClick(ActionEvent event) {
        openWindow("/PustakaLokam/library/ui/book/display_books.fxml", "All Registered Books of the Library");
    }
}
