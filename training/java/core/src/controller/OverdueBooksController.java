package controller;

import Domain.Book;
import Service.ServiceInterface;
import Service.ServiceLayer;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.List;

public class OverdueBooksController {

    @FXML private TableView<Book> overdueBooksTable;
    @FXML private TableColumn<Book, Integer> idColumn;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, String> categoryColumn;

    private ServiceLayer service = new ServiceLayer();

    @FXML
    public void initialize() {
        System.out.println("OverdueBooksController initialized");

        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookId()).asObject());
        titleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        authorColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));
        categoryColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));

        loadOverdueBooks();
    }

    private void loadOverdueBooks() {
        try {
            List<Book> overdueBooks = service.getOverdueBooks();
            overdueBooksTable.getItems().clear();

            if (overdueBooks != null && !overdueBooks.isEmpty()) {
                overdueBooksTable.getItems().addAll(overdueBooks);
                System.out.println("Loaded overdue books: " + overdueBooks.size());
            } else {
                System.out.println("No overdue books found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Utility to show a popup message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

