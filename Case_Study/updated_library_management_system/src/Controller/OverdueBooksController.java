package Controller;

import domain.Book;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.List;

import Service.Reports;

public class OverdueBooksController {

    @FXML private TableView<Book> overdueBooksTable;
    @FXML private TableColumn<Book, Integer> idColumn;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, String> categoryColumn;

    private Reports reports=new Reports();

    @FXML
    public void initialize() {
        System.out.println("OverdueBooksController initialized");

        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookid()).asObject());
        titleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        authorColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));
        categoryColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));

        loadOverdueBooks();
    }

    private void loadOverdueBooks() {
        try {
            List<Book> overdueBooks = reports.overduebooks();
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

    
    
    @FXML
    private void handleBack() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/applicationView/ReportsMenu.fxml")); // update the FXML path
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = (javafx.stage.Stage) overdueBooksTable.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.setTitle("Home");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not navigate to home.");
        }
    }
    
    

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}

