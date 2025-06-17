package librarySystem.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

import java.io.IOException;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;


public class BookController {
    @FXML
    private TextField titleField, authorField, categoryField, statusField;
    @FXML
    private TableView<BookPojo> bookTable;
    @FXML
    private TableColumn<BookPojo, Integer> Id;
    @FXML
    private TableColumn<BookPojo, String> Title, Author, Category;
    @FXML
    private TableColumn<BookPojo, Character> Status, Availability;

    private final libraryServices service = new libraryServices();
    private BookPojo selectedBook = null;


    @FXML
    public void initialize() {
        Id.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookId()).asObject());
        Title.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        Author.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));
        Category.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));
        Status.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStatus()));
        Availability.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getAvailability()));
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
    
    public void handleUpdateBook() {
        if (selectedBook == null) {
            showAlert("Warning", "Please select a book to update.");
            return;
        }

        BookPojo update = new BookPojo();
        update.setBookId(selectedBook.getBookId());
        update.setTitle(titleField.getText());
        update.setAuthor(authorField.getText());
        update.setCategory(categoryField.getText());
        update.setStatus(statusField.getText().charAt(0));  
        update.setAvailability(selectedBook.getAvailability()); 

        if (service.updateBookDetails(selectedBook, update)) {
            showAlert("Success", "Book updated successfully.");
            refreshTable();
            clearFields();
            selectedBook = null;
        } else {
            showAlert("Error", "Book update failed.");
        }
    }


    public void handleDelete() {
//    	BookPojo selected = bookTable.getSelectionModel().getSelectedItem();
//        if (selected == null) {
//            showAlert("Warning", "Please select a book to delete.");
//            return;
//        }
//
//        if (service.deleteBook(selected.getBookId())) {
//            refreshTable();
//            clearFields();
//            showAlert("Success", "Book deleted successfully.");
//        } else {
//            showAlert("Error", "Failed to delete book.");
//        }
    }
    public void handleAddBook() {
        BookPojo book = new BookPojo();
        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setCategory(categoryField.getText());
        book.setStatus('A');
        book.setAvailability('A');
        if (service.addBook(book)) {
            refreshTable();
            clearFields();
        } else {
            showAlert("Error", "Could not add book.");
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
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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