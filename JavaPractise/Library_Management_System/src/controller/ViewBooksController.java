package controller;

import dao.BookDao;
import domain.Book;
import exceptions.ManagementException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.BookService;
import service.BookServiceInterface;


import java.util.List;

public class ViewBooksController {

    @FXML private TableView<Book> tableView;
    @FXML private TableColumn<Book, Integer> book_id;
    @FXML private TableColumn<Book, String> book_title;
    @FXML private TableColumn<Book, String> author;
    @FXML private TableColumn<Book, String> category;
    @FXML private TableColumn<Book, String> status;
    @FXML private TableColumn<Book, String> availability;
    @FXML private TableColumn<Book, Void> updateCol;
    @FXML private TableColumn<Book, Void> updateAvailCol;

    private final BookServiceInterface bookService = new BookService(new BookDao());

    @FXML
    public void initialize() {
        book_id.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("bookId"));
        book_title.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("title"));
        author.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("author"));
        category.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("category"));
        status.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("status"));
        availability.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("availability"));

        addUpdateButton();
        addUpdateAvailabilityButton();

        try {
            List<Book> books = bookService.getAllBooks();
            ObservableList<Book> bookList = FXCollections.observableArrayList(books);
            tableView.setItems(bookList);
        } catch (ManagementException e) {
            e.printStackTrace();
        }
    }

    private void addUpdateButton() {
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Update");

                    {
                        btn.setOnAction(event -> {
                            Book book = getTableView().getItems().get(getIndex());
                            openUpdateForm(book);
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : btn);
                    }
                };
            }
        };
        updateCol.setCellFactory(cellFactory);
    }

    private void addUpdateAvailabilityButton() {
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Update Availability");

                    {
                        btn.setOnAction(event -> {
                            Book book = getTableView().getItems().get(getIndex());
                            openUpdateAvailabilityForm(book);
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : btn);
                    }
                };
            }
        };
        updateAvailCol.setCellFactory(cellFactory);
    }

    private void openUpdateForm(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/UpdateBook.fxml"));
            Parent root = loader.load();
            UpdateBookController controller = loader.getController();
            controller.setBookData(book);
            Stage stage = (Stage) tableView.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openUpdateAvailabilityForm(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/UpdateAvailability.fxml"));
            Parent root = loader.load();
            UpdateBookAvailabilityController controller = loader.getController();
            controller.setBookData(book);
            Stage stage = (Stage) tableView.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/BookManagement.fxml"));
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
