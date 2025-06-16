
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Book;
import service.BookService;

import java.util.List;

public class BookUI extends Application {

    private BookService bookService = new BookService();
    private TableView<Book> tableView = new TableView<>();
    private ObservableList<Book> bookData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library - Book Management");

        // Form fields
        TextField titleField = new TextField();
        TextField authorField = new TextField();
        TextField categoryField = new TextField();
        ComboBox<Character> statusBox = new ComboBox<>();
        ComboBox<Character> availabilityBox = new ComboBox<>();

        titleField.setPromptText("Title");
        authorField.setPromptText("Author");
        categoryField.setPromptText("Category");
        statusBox.setItems(FXCollections.observableArrayList('A', 'I'));
        availabilityBox.setItems(FXCollections.observableArrayList('A', 'I'));

        Button addButton = new Button("Add Book");

        // Form layout
        HBox form = new HBox(10, titleField, authorField, categoryField, statusBox, availabilityBox, addButton);
        form.setPadding(new Insets(10));

        // TableView columns
        TableColumn<Book, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getBookId()));

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTitle()));

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getAuthor()));

        TableColumn<Book, String> catCol = new TableColumn<>("Category");
        catCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCategory()));

        TableColumn<Book, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cell.getValue().getStatus())));

        TableColumn<Book, String> availCol = new TableColumn<>("Availability");
        availCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cell.getValue().getAvailability())));

        tableView.getColumns().addAll(idCol, titleCol, authorCol, catCol, statusCol, availCol);
        tableView.setItems(bookData);

        VBox layout = new VBox(10, form, tableView);
        layout.setPadding(new Insets(10));

        // Button action
        addButton.setOnAction(e -> {
            try {
                String title = titleField.getText();
                String author = authorField.getText();
                String category = categoryField.getText();
                char status = statusBox.getValue();
                char availability = availabilityBox.getValue();

                boolean success = bookService.addBook(title, author, category, status, availability);
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Book added successfully!");
                    loadBooks(); // refresh table
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failed", "Could not add book.");
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", ex.getMessage());
            }
        });

        loadBooks();

        Scene scene = new Scene(layout, 900, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadBooks() {
        List<Book> books = bookService.getAllBooks();
        bookData.setAll(books);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
