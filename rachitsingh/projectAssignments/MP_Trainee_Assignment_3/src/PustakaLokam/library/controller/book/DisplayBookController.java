package PustakaLokam.library.controller.book;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

import PustakaLokam.library.model.Book;
import PustakaLokam.library.service.BookService;

public class DisplayBookController {
    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book, String> titleAttribute;

    @FXML
    private TableColumn<Book, String> authorAttribute;

    @FXML
    private TableColumn<Book, String> categoryAttribute;

    @FXML
    private TableColumn<Book, String> conditionAttribute;

    @FXML
    private TableColumn<Book, String> availableAttribute;

    private BookService bookService = new BookService();

    @FXML
    public void initialize() {
        titleAttribute.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorAttribute.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryAttribute.setCellValueFactory(new PropertyValueFactory<>("category"));

        conditionAttribute.setCellValueFactory(cell -> {
            return new SimpleStringProperty(cell.getValue().getCondition().name());
        });

        availableAttribute.setCellValueFactory(cell -> {
            return new SimpleStringProperty(cell.getValue().getAvailability().name());
        });

        List<Book> bookList;
        try {
            bookList = bookService.fetchAllBooks();
        } catch (Exception e) {
            e.printStackTrace();
            bookList = new ArrayList<>();
        }
        ObservableList<Book> books = FXCollections.observableArrayList(bookList);
        booksTable.setItems(books);
    }
}
