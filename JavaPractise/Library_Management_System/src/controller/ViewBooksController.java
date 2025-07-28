package controller;

import dao.bookDao;
import domain.Book;
import exceptions.DatabaseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.BookService;

import java.util.List;

public class ViewBooksController {

    @FXML private TableView<Book> tableView;

    @FXML private TableColumn<Book, Integer> book_id;
    @FXML private TableColumn<Book, String> book_title;
    @FXML private TableColumn<Book, String> author;
    @FXML private TableColumn<Book, String> category;
    @FXML private TableColumn<Book, String> status;
    @FXML private TableColumn<Book, String> availability;

    private final BookService bookService = new BookService(new bookDao());

    @FXML
    public void initialize() {
        book_id.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        book_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        availability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        List<Book> books=null;
		try {
			books = bookService.getAllBooks();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
        ObservableList<Book> bookList = FXCollections.observableArrayList(books);
        tableView.setItems(bookList);
    }

    @FXML
    private void handleBack() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/BookManagement.fxml"));
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
