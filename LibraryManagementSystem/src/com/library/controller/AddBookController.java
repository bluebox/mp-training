package com.library.controller;

import com.Models.Book;
import com.Service.BookService;
//import com.library.util.AlertMsg;
 
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
 
public class AddBookController {
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField categoryField;
 
    private final BookService bookService = new BookService();
 
    @FXML
    private void addBook() {
        try {
            Book book = new Book(
                titleField.getText(),
                authorField.getText(),
                categoryField.getText(),
                "A", "A");
 
            bookService.addBook(book);
            //data to BooksLog
           // bookService.addToBooksLog(book);
            
            titleField.clear();
            authorField.clear();
            categoryField.clear();
 
        } catch (Exception e) {
            AlertMsg.showError(e.getMessage());;
        }
    }
    
//    //data to log
//    @FXML
//    private void addToBooksLog() {
//        try {
//            Book book = new Book(0,
//                titleField.getText(),
//                authorField.getText(),
//                categoryField.getText(),
//                'A', 'A');
//
//            //data to BooksLog
//            bookService.addToBooksLog(book);
//            
//            titleField.clear();
//            authorField.clear();
//            categoryField.clear();
//
//        } catch(Exception e) {
//        	AlertMsg.showError(e.getMessage());
//        }
//      }
}
