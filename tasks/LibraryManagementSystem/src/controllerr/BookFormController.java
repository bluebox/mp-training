package controllerr;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Book;
import service.BookService;

public class BookFormController {

    @FXML 
    private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField categoryField;
    @FXML private ChoiceBox<String> statusChoiceBox;
    @FXML private ChoiceBox<String> availabilityChoiceBox;
    @FXML private Label messageLabel;

    private final BookService bookService = new BookService();

    @FXML
    public void initialize() {
        statusChoiceBox.getItems().addAll("A", "I"); // Active/Inactive
        availabilityChoiceBox.getItems().addAll("A", "I"); // Available/Issued
    }

    @FXML
    private void handleAddBook() {
        try {
            Book book = new Book(
                0, titleField.getText(),
                authorField.getText(),
                categoryField.getText(),
                statusChoiceBox.getValue().charAt(0),
                availabilityChoiceBox.getValue().charAt(0)
            );

            bookService.addBook(book);
            messageLabel.setText("✅ Book added successfully!");
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("❌ " + e.getMessage());
        }
    }

    private void clearForm() {
        titleField.clear();
        authorField.clear();
        categoryField.clear();
        statusChoiceBox.setValue(null);
        availabilityChoiceBox.setValue(null);
    }
}
