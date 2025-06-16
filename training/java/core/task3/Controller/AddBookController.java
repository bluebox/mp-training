package Controller;

import Service.BookService;
import casestudy.Book;
import casestudy.LibraryException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController {
    @FXML private TextField titleField, authorField, categoryField;
    @FXML private ComboBox<String> statusCombo;
    private BookService bookService = new BookService();
    private Stage primaryStage;

    public AddBookController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleAddBook() {
        try {
            Book book = new Book(
                titleField.getText(),
                authorField.getText(),
                categoryField.getText(),
                statusCombo.getValue().charAt(statusCombo.getValue().length() - 2),
                'A'
            );
            if(book.getAuthor() == null || book.getTitle() == null || book.getCategory() == null) {
            	showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields");
            	return;
            }
            bookService.addBook(book);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book added successfully");
            clearFields();
        } catch (LibraryException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void goBack() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/MainView.fxml"));
        Scene scene = new Scene(loader.load());
        MainController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
    }

    @FXML
    private void handleClear() {
        titleField.clear();
        authorField.clear();
        categoryField.clear();
        statusCombo.setValue(null);
    }

    private void clearFields() {
        titleField.clear();
        authorField.clear();
        categoryField.clear();
        statusCombo.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
