package controllerr;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import service.BookService;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewBooksController {
    @FXML private TableView<Book> booksTable;
    @FXML private TableColumn<Book, Integer> bookIdCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> categoryCol;
    @FXML private TableColumn<Book, Character> statusCol;
    @FXML private TableColumn<Book, Character> availabilityCol;

    private final BookService bookService = new BookService();

    @FXML
    public void initialize() {
        bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));
        loadBooks();
    }

    private void loadBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            ObservableList<Book> observableBooks = FXCollections.observableArrayList(books);
            booksTable.setItems(observableBooks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 