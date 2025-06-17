package PustakaLokam.library.controller.book;

import javafx.event.ActionEvent;
import java.io.IOException;

import PustakaLokam.library.model.Book;
import PustakaLokam.library.service.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class BookListController {

    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, Integer> idColumn;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, String> categoryColumn;

    private final BookService bookService = new BookService();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookID()).asObject());
        titleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        authorColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));
        categoryColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));

        ObservableList<Book> books = FXCollections.observableArrayList(bookService.fetchAllBooks());
        bookTable.setItems(books);
    }

    @FXML
    private void onUpdateClick(ActionEvent event) {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            // Optionally show an alert here
            return;
        }

        try {
            // Load the update book FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PustakaLokam/library/ui/book/update_book.fxml"));
            Parent root = loader.load();

            // Pass selectedBook to the update controller
            UpdateBookController controller = loader.getController();
            controller.setBook(selectedBook);  // Make sure setBook(Book) exists in UpdateBookController

            // Show new window
            Stage stage = new Stage();
            stage.setTitle("Update Book");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
