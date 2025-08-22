package com.library.controller;

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
import javafx.scene.text.Text;
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

        // Wrap text if too long for Title, Author, or Category
        setWrappingCellFactory(colTitle, "title");
        setWrappingCellFactory(colAuthor, "author");
        setWrappingCellFactory(colCategory, "category");

        // Status column mapping
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setCellFactory(column -> new TableCell<Book, Character>() {
            @Override
            protected void updateItem(Character item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item == 'A' ? "Active" : "Inactive");
                }
            }
        });

        // Availability column mapping
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colAvailability.setCellFactory(column -> new TableCell<Book, Character>() {
            @Override
            protected void updateItem(Character item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item == 'A' ? "Available" : "Issued");
                }
            }
        });

        // Action buttons
        colActions.setCellFactory(param -> new TableCell<Book, Void>() {
            private final Button updateBookBtn = new Button("Update");
            private final Button updateAvailBtn = new Button("Availability");

            {
            	updateBookBtn.setOnAction(event -> {
            	    Book selectedBook = getTableView().getItems().get(getIndex());
            	    openEditBookPage(selectedBook);
            	});

            	updateAvailBtn.setOnAction(event -> {
            	    Book selectedBook = getTableView().getItems().get(getIndex());
            	    openUpdateAvailabilityPage(selectedBook);
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

    private void setWrappingCellFactory(TableColumn<Book, String> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setCellFactory(col -> {
            TableCell<Book, String> cell = new TableCell<Book, String>() {
                private final Text text = new Text();

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        text.setText(item);
                        text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(10));
                        setGraphic(text);
                    }
                }
            };
            return cell;
        });
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
    private void openEditBookPage(Book book) {
        try {
            UpdateBookController.setSelectedBook(book); // Set the book statically
            MainController.switchScene("UpdateBookForm.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openUpdateAvailabilityPage(Book book) {
        try {
            UpdateAvailabilityController.setSelectedBook(book); // Set the book statically
            MainController.switchScene("UpdateBookAvailabilityForm.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
