package controller;

import dao.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Book;

public class UpdateBookController {

    @FXML
    private TextField bookIdField, titleField, authorField, categoryField, statusField;

    @FXML
    private TextField availabilityField;

    @FXML
    private void handleUpdate() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            String title = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            String status = statusField.getText();

            if (!(status.equals("A") || status.equals("I"))) {
                showAlert("Status must be A(Active) or I(Inactive)");
                return;
            }

            Book book = new Book(bookId, title, author, category, status, null); // Availability = null (not updating)

            BookDAO dao = new BookDAO();
            boolean updated = dao.updateBookDetails(book);

            if (updated) {
                showAlert("Book details updated successfully");
            } else {
                showAlert("Update failed,Please check Book ID");
            }

        } catch (NumberFormatException e) {
            showAlert("Book ID must be a number");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

    public void handleUpdateAvailability(ActionEvent event) throws Exception {
        // We will implement this later
        System.out.println("In UpdataBookController Update Availability button clicked");
        try {

            int bookId = Integer.parseInt(bookIdField.getText());
            String availability = availabilityField.getText();
            if (!(availability.equals("A") || availability.equals("I"))) {
                showAlert("Availability must be A (Available) or I (Unavailable)");
                return;
            }
            BookDAO dao = new BookDAO();
            boolean updated = dao.updateAvailability(bookId, availability);
            if (updated) {
                showAlert("Book availability updated");
            } else {
                showAlert("Update of book aailability failed.");
            }
        } catch (NumberFormatException e) {
            showAlert("Book ID must be a number");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error: " + e.getMessage());
        }

    }

    public void handleDeleteBook(ActionEvent event)throws Exception{
        try {

            int bookId = Integer.parseInt(bookIdField.getText());
           
            
            BookDAO dao = new BookDAO();
            boolean updated = dao.deleteBook(bookId);
            if (updated) {
                showAlert("Book deleted successfully.");
            } else {
                showAlert("deletion failed check Book ID.");
            }
        } catch (NumberFormatException e) {
            showAlert("Book ID must be a number");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error: " + e.getMessage());
        }

    }

}
