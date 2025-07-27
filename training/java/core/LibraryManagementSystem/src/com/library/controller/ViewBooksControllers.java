package com.library.controller;


import com.library.dao.impl.BookDaoImplementation;
import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ViewBooksControllers {

    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, Integer> colId;
    @FXML private TableColumn<Book, String> colTitle;
    @FXML private TableColumn<Book, String> colAuthor;
    @FXML private TableColumn<Book, String> colCategory;
    @FXML private TableColumn<Book, Character> colStatus;
    @FXML private TableColumn<Book, Character> colAvailability;
    @FXML private TableColumn<Book, Void> colActions;

    private final BookServiceImplementation bookService = new BookServiceImplementation();
    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        colActions.setCellFactory(param -> new TableCell<Book, Void>() {
            private final Button updateBookBtn = new Button("Update");
            private final Button updateAvailBtn = new Button("Availability");

            {
                updateBookBtn.setOnAction(event -> {
                    Book selectedBook = getTableView().getItems().get(getIndex());
                    openEditBookPopup(selectedBook);
                });

                updateAvailBtn.setOnAction(event -> {
                    Book selectedBook = getTableView().getItems().get(getIndex());
                    updateAvailability(selectedBook);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(10, updateBookBtn, updateAvailBtn);
                    setGraphic(box);
                }
            }
        });

        loadBooks();
    }

    @FXML
    private void handleBack() throws IOException {
        MainController.switchScene("MainDashboard.fxml");
    }

    private void loadBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            ObservableList<Book> data = FXCollections.observableArrayList(books);
            bookTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openEditBookPopup(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateBookForm.fxml"));
            Parent root = loader.load();
            
            UpdateBookController controller = loader.getController();
            controller.setBook(book);

            Stage popup = new Stage();
            popup.setTitle("Edit Book");
            popup.setScene(new Scene(root));
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setOnHiding(event -> loadBooks());
            popup.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateAvailability(Book book) {
        
    	System.out.println("toggle Availability for "+book.getTitle());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateBookAvailabilityForm.fxml"));
            Parent root = loader.load();
            

            UpdateAvailabilityController controller = loader.getController();
            controller.setBook(book);
            
            Stage popup = new Stage();
            popup.setTitle("Edit Book");
            popup.setScene(new Scene(root));
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setOnHiding(event -> loadBooks());
            popup.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    

}


