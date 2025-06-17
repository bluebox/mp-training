package controllerr;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Book;
import service.BookService;
import Exception.InvalidInputException;

public class UpdateBookAvailabilityController {
	@FXML private TextField BookId;
	@FXML private ChoiceBox<String> availabilityBox;
	@FXML private Label messageLabel;
	
	private final BookService bookService = new BookService();
	
	public void initialize() {
        availabilityBox.getItems().addAll("A","I");
    }
	
	@FXML
    private void handleUpdateBookAvailability() {
        try {
            String idText = BookId.getText().trim();
            if (idText.isEmpty() || !idText.matches("\\d+")) {
                throw new InvalidInputException("Book ID must be a valid number.");
            }
            int bookID = Integer.parseInt(idText);

            String availabilityVal = availabilityBox.getValue();
            if (availabilityVal == null) {
                throw new InvalidInputException("Availability is required.");
            }
            
            bookService.updateBookAvailability(bookID,availabilityVal.charAt(0));
            	messageLabel.setText("Availability updated successfully.");
        }
        catch (Exception e) {
            messageLabel.setText(e.getMessage());
        }
    }
}
