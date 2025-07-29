package controller;

import Domain.Book;
import Service.ServiceInterface;
import Service.ServiceLayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class OverdueBooksController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
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

    
    public void gotoHome(ActionEvent event) throws IOException {
  	   root= FXMLLoader.load(getClass().getResource("/applicationview/HomeView.fxml"));
  	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
  	    stage.setTitle("Library Management System");
  	    scene=new Scene(root);
  	    stage.setScene(scene);
  	    stage.show();
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

