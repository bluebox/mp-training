package com.lms.controller;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.lms.model.Book;
import com.lms.serviceImpl.BookServiceImpl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewBooksController implements Initializable {

    @FXML
    private TableView<Book> bookTable;
    
    @FXML
    private TableColumn<Book, Integer> bookIdColumn;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> categoryColumn;

    @FXML
    private TableColumn<Book, Character> statusColumn;

    @FXML
    private TableColumn<Book, Character> availabilityColumn;
    public BookServiceImpl bookService=new BookServiceImpl();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    			bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
        
        refreshTable();
    }
        public void refreshTable() {
        	bookTable.getItems().clear();
        	try {
				bookTable.getItems().addAll(bookService.getAllBooks());
			} catch (SQLException e) {
				showAlert(Alert.AlertType.ERROR, "Server Issue", "Our server is currently down. Please try again later.");
			}
        }
        private void showAlert(Alert.AlertType type, String title, String content) {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }
       
}