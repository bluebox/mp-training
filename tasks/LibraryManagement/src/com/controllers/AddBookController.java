package com.controllers;
import com.models.Book;
import com.services.BookService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
public class AddBookController {

    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField categoryField;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private ComboBox<String> availabilityComboBox;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        statusComboBox.getItems().addAll("A", "I");     
        availabilityComboBox.getItems().addAll("A", "I"); 
    }

    @FXML
    public void handleAddBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String category = categoryField.getText();
        char status = statusComboBox.getValue().charAt(0);
        char availability = availabilityComboBox.getValue().charAt(0);

        if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
            messageLabel.setText("Please fill all fields.");
        } else {
//        	Book book=new Book(title,author,category,status,availability);
        	BookService bookService=new BookService();
        	bookService.addBook(title, author, category, status, availability);
            messageLabel.setText("Book added: " + title);
            
        }
    }
}
