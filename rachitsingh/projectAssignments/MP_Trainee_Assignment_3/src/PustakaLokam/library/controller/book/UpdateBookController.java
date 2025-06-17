package PustakaLokam.library.controller.book;

import PustakaLokam.library.service.BookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import PustakaLokam.library.enums.AvailabilityStatus;
import PustakaLokam.library.enums.BookCondition;
import PustakaLokam.library.exceptionhandler.BookNotFoundException;
import PustakaLokam.library.exceptionhandler.DatabaseOperationException;
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
    @FXML
    private ChoiceBox<String> conditionChoiceBox;
    @FXML
    private ChoiceBox<String> availabilityChoiceBox;

    private final BookService bookService = new BookService();
	private Book book;
    private static int bookIdToLoad = -1;
    @FXML
    public void initialize() {
    	conditionChoiceBox.getItems().addAll("Active", "Inactive");
        availabilityChoiceBox.getItems().addAll("Available", "Issued");

        conditionChoiceBox.setValue("Active");
        availabilityChoiceBox.setValue("Available");
    }

    @FXML
    public void updateDetailsButtonHandler(ActionEvent event) {
        try {
        	if (bookIdField.getText() == null || bookIdField.getText().trim().isEmpty()) {
                statusLabel.setText("Book ID is required.");
                return;
            }
            int bookID = Integer.parseInt(bookIdField.getText().trim());
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String category = categoryField.getText().trim();
            String conditionStr = conditionChoiceBox.getValue();
            String availabilityStr = availabilityChoiceBox.getValue();

            if (title.isEmpty() || author.isEmpty() || category.isEmpty()
                    || conditionStr == null || availabilityStr == null) {
                statusLabel.setText("Kindly fill all the required input fields.");
                return;
            }

            BookCondition condition = BookCondition.fromLabel(conditionStr);
            AvailabilityStatus availability = mapToAvailabilityStatus(availabilityStr);

            Book book = new Book();
            book.setBookID(bookID);
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setCondition(condition);
            book.setAvailability(availability);

            try {
                bookService.updateBookDetails(book);
                statusLabel.setText("Book details have been updated successfully!");
            } catch (BookNotFoundException e) {
                statusLabel.setText("Book not found. Update failed.");
            } catch (DatabaseOperationException e) {
                statusLabel.setText("Database error occurred. Please try again.");
            }


        } catch (NumberFormatException NE) {
            statusLabel.setText("Book ID provided is invalid.");
        } catch (IllegalArgumentException e) {
            statusLabel.setText("Invalid selection in status or condition.");
        }
    }
    public void setBook(Book book) {
        this.book = book;

        // If you have text fields like titleField, authorField, etc.,
        // populate them here
        bookIdField.setText(String.valueOf(book.getBookID()));
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        categoryField.setText(book.getCategory());
    }
    public static void setBookIdToLoad(int id) {
        bookIdToLoad = id;
    }
    public void loadBookDetails(int bookId) {
        Book book = bookService.getBookByID(bookId);
        if (book != null) {
            bookIdField.setText(String.valueOf(book.getBookID()));
            titleField.setText(book.getTitle());
            authorField.setText(book.getAuthor());
            categoryField.setText(book.getCategory());

            // Set condition choice box
            if (book.getCondition().name().equalsIgnoreCase("ACTIVE")) {
                conditionChoiceBox.setValue("Active");
            } else {
                conditionChoiceBox.setValue("Inactive");
            }

            // Set availability choice box
            if (book.getAvailability().name().equalsIgnoreCase("AVAILABLE")) {
                availabilityChoiceBox.setValue("Available");
            } else {
                availabilityChoiceBox.setValue("Issued");
            }

            statusLabel.setText("Editing Book ID: " + bookId);  // optional
        } else {
            statusLabel.setText("Book not found.");
        }
    }

    private AvailabilityStatus mapToAvailabilityStatus(String value) {
        if (value.equalsIgnoreCase("Available")) return AvailabilityStatus.AVAILABLE;
        else if (value.equalsIgnoreCase("Issued")) return AvailabilityStatus.ISSUED;
        else throw new IllegalArgumentException("Unknown availability status: " + value);
    }
}
