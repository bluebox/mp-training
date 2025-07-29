
	package Controller;

	import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;

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
	    @FXML
	    public void goToPreviousScene(ActionEvent event) {
	        try {
	            Parent root = FXMLLoader.load(getClass().getResource("/applicationView/ViewAllMembers.fxml"));
	            Scene scene = new Scene(root);
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

