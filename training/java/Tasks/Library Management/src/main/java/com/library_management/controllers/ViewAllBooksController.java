
package com.library_management.controllers;

import java.io.IOException;
import java.util.List;

import com.library_management.App;
import com.library_management.dao.implementation.BookDaoImplementation;
import com.library_management.domain.Book;
import com.library_management.domain.BookAvailability;
import com.library_management.domain.BookCategory;
import com.library_management.domain.BookStatus;
import com.library_management.exceptions.DatabaseException;
import com.library_management.services.implementation.BookServiceImplementation;
import com.library_management.utilities.CustomBookForTableView;
import com.library_management.utilities.ResponseHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ViewAllBooksController {

    @FXML
    private TableView<CustomBookForTableView> viewBooksTable;
    @FXML
    private TableColumn<CustomBookForTableView, Integer> bookIdColumn;
    @FXML
    private TableColumn<CustomBookForTableView, String> titleColumn1;
    @FXML
    private TableColumn<CustomBookForTableView, String> authorColumn1;
    @FXML
    private TableColumn<CustomBookForTableView, String> categoryColumn1;
    @FXML
    private TableColumn<CustomBookForTableView, String> statusColumn1;
    @FXML
    private TableColumn<CustomBookForTableView, String> availabilityColumn1;
    @FXML
    private TableColumn<CustomBookForTableView, Void> actionsColumn;
    @FXML
    private Text message;

    private ObservableList<CustomBookForTableView> bookData1 = FXCollections.observableArrayList();

    @FXML
    private void backButton() throws IOException {
        App.setRoot("bookOptions");
    }

    @FXML
    private void homeButton() throws IOException {
        App.setRoot("home");
    }

    @FXML
    public void initialize() {
        // Set column value factories
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleColumn1.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn1.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryColumn1.setCellValueFactory(new PropertyValueFactory<>("category"));
        statusColumn1.setCellValueFactory(new PropertyValueFactory<>("status"));
        availabilityColumn1.setCellValueFactory(new PropertyValueFactory<>("availability"));

        try {
            List<Book> books = new BookServiceImplementation().validateViewAllBooks();
            for (Book book : books) {
                bookData1.add(new CustomBookForTableView(
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory(),
                    book.getStatus(),
                    book.getAvailability()
                ));
            }

            viewBooksTable.setItems(bookData1);
            ResponseHandler.showResponse(message, "Books Fetched Successfully...", Color.GREEN);

        } catch (DatabaseException e) {
            ResponseHandler.showResponse(message, "Cannot Fetch Books Data...", Color.RED);
        }

        addActionsToTable();
    }

    public void addActionsToTable() {
        actionsColumn.setCellFactory(new Callback<TableColumn<CustomBookForTableView, Void>, TableCell<CustomBookForTableView, Void>>() {
            @Override
            public TableCell<CustomBookForTableView, Void> call(TableColumn<CustomBookForTableView, Void> param) {
                return new TableCell<CustomBookForTableView, Void>() {

                    private final ToggleButton toggleAvailability = new ToggleButton("Toggle Availability");
                    private final Button deleteButton = new Button("Delete");

                    {
                        // Toggle availability event
                    	
                        toggleAvailability.setOnAction(event -> {
                            CustomBookForTableView book = getTableView().getItems().get(getIndex());
                            String newAvailabilityValue = book.getAvailability().equals("Available") ? "Issued" : "Available";
                            Book currBook = new Book(
                                book.getBookId(),
                                book.getTitle(),
                                book.getAuthor(),
                                BookCategory.getEnumConstant(book.getCategory()),
                                BookStatus.getEnumConstant(book.getStatus().substring(0, 1)),
                                BookAvailability.getEnumConstant(book.getAvailability().substring(0, 1))
                            );

                            try {
                                new BookDaoImplementation().updateBookAvailability(currBook, newAvailabilityValue.substring(0, 1));
                                book.setAvailability(newAvailabilityValue);
                                viewBooksTable.refresh();
                                ResponseHandler.showResponse(message, "Successfully Updated Availability...", Color.GREEN);
                            } catch (DatabaseException e) {
                                ResponseHandler.showResponse(message, e.getMessage(), Color.RED);
                            }
                        });

                        // Delete book event
                        deleteButton.setStyle("-fx-background-color: #ef5350; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
                        deleteButton.setOnAction(event -> {
                            CustomBookForTableView book = getTableView().getItems().get(getIndex());
                            Book currentBook = new Book(
                                book.getBookId(),
                                book.getTitle(),
                                book.getAuthor(),
                                BookCategory.getEnumConstant(book.getCategory()),
                                BookStatus.getEnumConstant(book.getStatus().substring(0, 1)),
                                BookAvailability.getEnumConstant(book.getAvailability().substring(0, 1))
                            );

                            getTableView().getItems().remove(getIndex());
                            ResponseHandler.showResponse(message, "Book Deleted Successfully...", Color.GREEN);

                            try {
                                new BookDaoImplementation().deleteBook(currentBook);
                            } catch (DatabaseException e) {
                                ResponseHandler.showResponse(message, e.getMessage(), Color.RED);
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
//                            HBox buttons = new HBox(10, toggleAvailability, deleteButton);
                        	HBox buttons = new HBox(10, deleteButton);
                            buttons.setPadding(new Insets(5));
                            setGraphic(buttons);
                        }
                    }
                };
            }
        });
    }
}
