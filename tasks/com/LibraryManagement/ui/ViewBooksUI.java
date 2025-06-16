package com.LibraryManagement.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.List;

import com.LibraryManagement.dao.BookDAO;
import com.LibraryManagement.dao.BookDAOImpl;
import com.LibraryManagement.model.*;
import com.LibraryManagement.service.BookService;
import com.LibraryManagement.service.BookServiceImpl;
import com.LibraryManagement.util.DBConnection;

public class ViewBooksUI {
    private final BookService bookService;

    public ViewBooksUI() {
        try {
        	Connection conn = DBConnection.getConnection();
        	BookDAO bookDAO = new BookDAOImpl(conn);
        	this.bookService = new BookServiceImpl(bookDAO);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize service: " + e.getMessage());
        }
    }

    public void start(Stage stage) {
        TableView<Book> table = new TableView<>();
        TableColumn<Book, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book, Character> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Book, Character> availabilityCol = new TableColumn<>("Available");
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

        table.getColumns().addAll(idCol, titleCol, authorCol, categoryCol, statusCol, availabilityCol);

        try {
            List<Book> books = bookService.getAllBooks();
            ObservableList<Book> data = FXCollections.observableArrayList(books);
            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        VBox root = new VBox(10, table);
        root.setPadding(new Insets(10));

        stage.setScene(new Scene(root, 700, 400));
        stage.setTitle("View All Books");
        stage.show();
    }
}