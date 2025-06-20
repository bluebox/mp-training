package librarySystem.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import librarySystem.Service.libraryServices;
import model.BookPojo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;

public class BookController {

    @FXML private TextField titleField, authorField, categoryField, statusField;
    @FXML private TableView<BookPojo> bookTable;
    @FXML private TableColumn<BookPojo, Integer> Id;
    @FXML private TableColumn<BookPojo, String> Title, Author, Category;
    @FXML private TableColumn<BookPojo, Character> Status, Availability;

    private final libraryServices service = new libraryServices();
    private BookPojo selectedBook = null;

    @FXML
    public void initialize() {
        Id.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getBookId()).asObject());
        Title.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        Author.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthor()));
        Category.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory()));
        Status.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getStatus()));
        Availability.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getAvailability()));

        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refreshTable();

        bookTable.setOnMouseClicked(event -> {
            selectedBook = bookTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                titleField.setText(selectedBook.getTitle());
                authorField.setText(selectedBook.getAuthor());
                categoryField.setText(selectedBook.getCategory());
                statusField.setText(String.valueOf(selectedBook.getStatus()));
            }
        });
    }

    public void handleAddBook() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String category = categoryField.getText().trim();
        String statusInput = statusField.getText().trim();

        if (title.isEmpty()) {
            showAlert("Warning", "Please specify title.", Alert.AlertType.WARNING);
            return;
        }
        if (author.isEmpty()) {
            showAlert("Warning", "Please specify author.", Alert.AlertType.WARNING);
            return;
        }
        if (category.isEmpty()) {
            showAlert("Warning", "Please specify category.", Alert.AlertType.WARNING);
            return;
        }
        if (statusInput.length() != 1 || !(statusInput.equals("A") || statusInput.equals("I"))) {
            showAlert("Warning", "Please specify correct status (A = Available, I = Inavailable).", Alert.AlertType.WARNING);
            return;
        }

        BookPojo book = new BookPojo();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setStatus(statusInput.charAt(0));
        book.setAvailability('A');

        if (service.addBook(book)) {
            showAlert("Success", "Book added successfully.", Alert.AlertType.INFORMATION);
            refreshTable();
            clearFields();
        } else {
            showAlert("Error", "Could not add book.", Alert.AlertType.ERROR);
        }
    }

    public void handleUpdateBook() {
        if (selectedBook == null) {
            showAlert("Warning", "Please select a book to update.", Alert.AlertType.WARNING);
            return;
        }

        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String category = categoryField.getText().trim();
        String statusInput = statusField.getText().trim();

        if (title.isEmpty()) {
            showAlert("Warning", "Please specify title.", Alert.AlertType.WARNING);
            return;
        }
        if (author.isEmpty()) {
            showAlert("Warning", "Please specify author.", Alert.AlertType.WARNING);
            return;
        }
        if (category.isEmpty()) {
            showAlert("Warning", "Please specify category.", Alert.AlertType.WARNING);
            return;
        }
        if (statusInput.length() != 1 || !(statusInput.equals("A") || statusInput.equals("I"))) {
            showAlert("Warning", "Please specify correct status (A = Available, I = Inavailable).", Alert.AlertType.WARNING);
            return;
        }

        BookPojo update = new BookPojo();
        update.setBookId(selectedBook.getBookId());
        update.setTitle(title);
        update.setAuthor(author);
        update.setCategory(category);
        update.setStatus(statusInput.charAt(0));
        update.setAvailability(selectedBook.getAvailability());

        if (service.updateBookDetails(selectedBook, update)) {
            showAlert("Success", "Book updated successfully.", Alert.AlertType.INFORMATION);
            refreshTable();
            clearFields();
            selectedBook = null;
        } else {
            showAlert("Error", "Book update failed.", Alert.AlertType.ERROR);
        }
    }

    public void handleDeleteBook() {
        BookPojo selected = bookTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Warning", "Please select a book to delete.", Alert.AlertType.WARNING);
            return;
        }

        if (service.deleteBook(selected.getBookId())) {
            refreshTable();
            clearFields();
            showAlert("Success", "Book deleted successfully.", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Failed to delete book.", Alert.AlertType.ERROR);
        }
    }

    private void refreshTable() {
        ObservableList<BookPojo> books = FXCollections.observableArrayList(service.viewAllBooks());
        bookTable.setItems(books);
    }

    private void clearFields() {
        titleField.clear();
        authorField.clear();
        categoryField.clear();
        statusField.clear();
    }

    private void showAlert(String title, String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void handleBooks(ActionEvent event) throws IOException {
        switchScene(event, "/librarySystem/View/BookView.fxml");
    }

    public void handleMembers(ActionEvent event) throws IOException {
        switchScene(event, "/librarySystem/View/MemberView.fxml");
    }

    public void handleIssueReturn(ActionEvent event) throws IOException {
        switchScene(event, "/librarySystem/View/IssueReturn.fxml");
    }

    public void handleReports(ActionEvent event) throws IOException {
        switchScene(event, "/librarySystem/View/ReportsView.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) bookTable.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Library System");
        stage.show();
    }
}
