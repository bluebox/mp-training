package com.library.controller;

import java.io.IOException;
import java.util.List;

import com.library.domain.Book;
import com.library.service.impl.LibraryServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ViewBooksController {
	
	@FXML private TableColumn<Book, Integer> idCol;
    @FXML private TableView<Book> table;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> categoryCol;
    @FXML private TableColumn<Book, String> statusCol;
    @FXML private TableColumn<Book, String> availabilityCol;

    private final LibraryServiceImpl libraryService = new LibraryServiceImpl();

    @FXML
    public void initialize() {
    	idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookId()).asObject());
        titleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        authorCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));
        categoryCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
        availabilityCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAvailability()));

        List<Book> books = libraryService.fetchAllBooks();
        ObservableList<Book> list = FXCollections.observableArrayList(books);
        table.setItems(list);
    }
    
    @FXML
    private void handleBack() {
        try {
            Parent homeView = FXMLLoader.load(getClass().getResource("/com/library/UI/Home.fxml"));
            Stage stage = (Stage) table.getScene().getWindow();
            stage.setScene(new Scene(homeView));
            stage.setTitle("Library - Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}