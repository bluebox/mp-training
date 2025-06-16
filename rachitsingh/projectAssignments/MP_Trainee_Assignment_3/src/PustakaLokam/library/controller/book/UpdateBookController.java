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

    private AvailabilityStatus mapToAvailabilityStatus(String value) {
        if (value.equalsIgnoreCase("Available")) return AvailabilityStatus.AVAILABLE;
        else if (value.equalsIgnoreCase("Issued")) return AvailabilityStatus.ISSUED;
        else throw new IllegalArgumentException("Unknown availability status: " + value);
    }
}
