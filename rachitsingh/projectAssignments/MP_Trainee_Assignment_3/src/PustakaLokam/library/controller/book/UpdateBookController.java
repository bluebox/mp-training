package PustakaLokam.library.controller.book;

import PustakaLokam.library.service.BookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import PustakaLokam.library.model.Book;

public class UpdateBookController {
    @FXML
    private TextField bookIdField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField categoryField;

    @FXML
    private Label statusLabel;

    private final BookService bookService = new BookService();

    @FXML
    public void initialize() {
    }

    @FXML
    public void updateDetailsButtonHandler(ActionEvent event) {
        try {
            int bookID = Integer.parseInt(bookIdField.getText().trim());
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String category = categoryField.getText().trim();

            if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
                statusLabel.setText("Kindly fill all the required input fields.");
                return;
            }
            Book book = new Book();
            book.setBookID(bookID);
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);

            boolean updateStatus = bookService.updateBookDetails(book);
            statusLabel.setText(
                    updateStatus ? "Book details have been updated successfully!" : "Book details udpation failed.");
        } catch (NumberFormatException NE) {
            statusLabel.setText("Book ID provided is invalid.");
        }
    }
}
