package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Availability;
import enums.BookStatus;
import model.Book;
import service.BookService;
import javafx.scene.control.Alert.AlertType;

public class AddBookController implements Initializable {

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtAuthor;
    @FXML
    private TextField txtCategory;
    @FXML
    private ComboBox<BookStatus> cbStatus;
    @FXML
    private ComboBox<Availability> cbAvailability;

    private BookService bookService = new BookService();

    @FXML
    private void handleSave() {
        try {
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String category = txtCategory.getText();
            BookStatus status = cbStatus.getValue();
            Availability availability = cbAvailability.getValue();

            if (title.isEmpty() || author.isEmpty() || category.isEmpty() || status == null || availability == null) {
                showAlert("All are required.");
                return;
            }

            Book book = new Book(0, title, author, category, status.getCode(),
                    availability.getCode());
            boolean success = bookService.addBook(book);
            if (success)
                showAlert("Book added successflly.");
            else
                showAlert("Failed to add book.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbStatus.getItems().addAll(BookStatus.values());
        cbAvailability.getItems().addAll(Availability.values());
    }

}