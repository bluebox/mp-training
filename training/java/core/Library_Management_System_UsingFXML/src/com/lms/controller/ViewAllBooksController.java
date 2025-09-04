package com.lms.controller;

import com.lms.Utils.ControllerUtil;
import com.lms.exceptions.DBConstrainsException;
import com.lms.main.Main;
import com.lms.model.Book;
import com.lms.service.BookService;
import com.lms.service.impl.BookServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class ViewAllBooksController {
    
    BookService bookService;
    
    @FXML
    private TableView<Book> bookTable;
    
    @FXML
    private TableColumn<Book, String> idColumn;
    
    @FXML
    private TableColumn<Book, String> titleColumn;
    
    @FXML
    private TableColumn<Book, String> authorColumn;
    
    @FXML
    private TableColumn<Book, String> categoryColumn;
    
    @FXML
    private TableColumn<Book, String> statusColumn;
    
    @FXML
    private TableColumn<Book, String> availabilityColumn;
    
    @FXML
    private TableColumn<Book, Void> actionColumn;
    
    @FXML
    private Button backButton;
    
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        bookService = new BookServiceImpl();
        List<Book> books = bookService.getAllBooks();
        
        updateBookList(books);
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("book_Id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("book_Title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("book_Author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("book_Category"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("book_Status"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("book_Availability"));
        
        //Configure action column with update button
        actionColumn.setCellFactory(param -> new UpdateButtonCell());
        bookTable.setItems(bookList);
    }
    
    private class UpdateButtonCell extends TableCell<Book, Void> {
        private final Button updateButton = new Button("Update");

        public UpdateButtonCell() {
            updateButton.setOnAction(event -> {
                Book book = getTableView().getItems().get(getIndex());
                showUpdateDialog(book);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : updateButton);
        }
    }

    private void showUpdateDialog(Book book) {
        Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Update Book");
        dialog.setHeaderText("Update book information");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lms/view/UpdateBookDialog.fxml"));
            GridPane dialogPane = loader.load();

            TextField titleField = (TextField) dialogPane.lookup("#titleField");
            TextField authorField = (TextField) dialogPane.lookup("#authorField");
            TextField availabilityField = (TextField) dialogPane.lookup("#availabilityField");
            RadioButton availableRadio = (RadioButton) dialogPane.lookup("#availableRadio");
            RadioButton issuedRadio = (RadioButton) dialogPane.lookup("#issuedRadio");
            ComboBox<String> categoryComboBox = (ComboBox<String>) dialogPane.lookup("#categoryComboBox");
            categoryComboBox.getItems().addAll(
                    "Fiction",
                    "Non-Fiction",
                    "Science",
                    "Technology",
                    "History",
                    "Biography"
                );
            
            titleField.setText(book.getBook_Title());
            authorField.setText(book.getBook_Author());
            availabilityField.setText(String.valueOf(book.getBook_Availability()));
            categoryComboBox.setValue(book.getBook_Category());
            if (book.getBook_Status() == 'A') {
                availableRadio.setSelected(true);
            } else {
                issuedRadio.setSelected(true);
            }

            dialog.getDialogPane().setContent(dialogPane);

            ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == updateButtonType) {
                	
                	String title = titleField.getText().trim();
                    String author = authorField.getText().trim();    
                    String category = categoryComboBox.getValue();
                    Book tempBook=new Book(title, author, category);
                    tempBook.setBook_Status(book.getBook_Status());
                    tempBook.setBook_Availability(book.getBook_Availability());
                    return tempBook;
                }
                return null;
            });

            dialog.showAndWait().ifPresent(updatedBook -> {
            	try {
            		bookService.updateBook(updatedBook);
            		book.setBook_Title(updatedBook.getBook_Title());
                    book.setBook_Author(updatedBook.getBook_Author());
                    book.setBook_Category(updatedBook.getBook_Category());
                    book.setBook_Status(availableRadio.isSelected() ? 'A' : 'I');
                    bookTable.refresh();
            	}
            	catch(DBConstrainsException e) {
                	ControllerUtil.createAlert(AlertType.INFORMATION, "Fail", "Update failed", e.getMessage());
                }
                
                
            });

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading update dialog FXML: " + e.getMessage());
        }
    }
    
    public void updateBookList(List<Book> books) {
        bookList.clear();
        bookList.addAll(books);
    }
    
    @FXML
    private void handleBackButton() {
        try {
            Main.changePage("LibraryHome");
        } catch (IOException e) {
            System.out.println("Had a problem with going back to main: " + e.getMessage());
        }
    }
}
