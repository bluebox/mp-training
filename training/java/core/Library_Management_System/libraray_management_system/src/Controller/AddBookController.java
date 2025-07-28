
	package Controller;

	import javafx.fxml.FXML;
	import javafx.scene.control.TextField;
	import javafx.scene.control.Alert;
	import Service.BookService;
	import domain.Book;
	import domain.checking_enum.Availability;
	import domain.checking_enum.Status;

	public class AddBookController {

	    @FXML
	    public TextField titleField;
	    @FXML
	    public TextField authorField;
	    @FXML
	    public TextField categoryField;
	    @FXML
	    public Service.BookService bookService = new Service.BookService();

	    public void handleAddBook() {
	        try {
	            Book book = new Book(
	                titleField.getText(),
	                authorField.getText(),
	                categoryField.getText(),
	                Status.ACTIVE,
	                Availability.ISSUED
	            );
	            bookService.addbooks(book);
	            showAlert("Book added successfully.");
	        } catch (Exception e) {
	            showAlert("Error: " + e.getMessage());
	        }
	    }

	    private void showAlert(String msg) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setContentText(msg);
	        alert.show();
	    }
	}

