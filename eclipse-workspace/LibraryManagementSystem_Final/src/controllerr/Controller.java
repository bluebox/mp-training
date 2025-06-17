package controllerr;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Controller {

    @FXML
    public void openAddBookForm() {
        openWindow("/view/BookForm.fxml", "Add Book");
    }
    @FXML
    public void openAddMemberForm() {
        openWindow("/view/MemberForm.fxml", "Add Member");
    }

    @FXML
    public void openUpdateMemberForm() {
        openWindow("/view/UpdateMemberForm.fxml", "Update Member");
    }
    
    @FXML
    public void openIssueBookForm() {
        openWindow("/view/IssueBookForm.fxml", "Issue Book");
    }
    
    @FXML
    public void openOverdueRecords() {
        openWindow("/view/OverdueRecords.fxml", "Overdue Issued Books");
    }

    @FXML
    public void openUpdateBookAvailability() {
        openWindow("/view/UpdateBookAvailability.fxml", "Update Book Availability");
    }
    
    @FXML
    public void openViewBooks() {
        openWindow("/view/ViewBooks.fxml", "All Books");
    }

    @FXML
    public void openViewMembers() {
        openWindow("/view/ViewMembers.fxml", "All Members");
    }

    @FXML
    public void openReturnBookForm() {
        openWindow("/view/ReturnBookForm.fxml", "Return a Book");
    }

    @FXML
    public void openIssuedRecords() {
        openWindow("/view/IssuedRecords.fxml", "All Issued Records");
    }

    private void openWindow(String fxmlPath, String title) {
        try {
            URL path = getClass().getResource(fxmlPath);
            System.out.println("FXML path = " + path); // Debug
            if (path == null) throw new RuntimeException("FXML file not found at " + fxmlPath);

            FXMLLoader loader = new FXMLLoader(path);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 400, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
