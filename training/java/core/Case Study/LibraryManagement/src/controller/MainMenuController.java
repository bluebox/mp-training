package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML private Button addBookBtn;
    @FXML private Button addMemberBtn;
    @FXML private Button searchBookBtn;
    @FXML private Button searchMemberBtn;
    @FXML private Button allBooksBtn;
    @FXML private Button allMembersBtn;
    @FXML private Button issuedBooksBtn;

    @FXML private TabPane tabPane;

    @FXML private Button issueBookTabBtn;
    @FXML private Button returnBookTabBtn;
    @FXML private TextField bookIdField;
    @FXML private TextField memberIdField;
    @FXML private TextField returnBookIdField;
    @FXML private TextField returnMemberIdField;

    private void openWindow(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    @FXML
    private void initialize() {
        addBookBtn.setOnAction(this::handleAddBook);
        addMemberBtn.setOnAction(this::handleAddMember);
        searchBookBtn.setOnAction(this::handleSearchBook);
        searchMemberBtn.setOnAction(this::handleReturnBook);
        allBooksBtn.setOnAction(this::handleViewBooks);
        allMembersBtn.setOnAction(this::handleViewMembers);
        issuedBooksBtn.setOnAction(this::handleIssuedBooks);

        issueBookTabBtn.setOnAction(this::handleIssueBookTab);
        returnBookTabBtn.setOnAction(this::handleReturnBookTab);
    }

    private void handleAddBook(ActionEvent event) {
        openWindow("/ui/views/AddBookView.fxml", "Add Book");
    }

    private void handleAddMember(ActionEvent event) {
        openWindow("/ui/views/AddMemberView.fxml", "Add Member");
    }

    private void handleSearchBook(ActionEvent event) {
        openWindow("/ui/views/SearchBook.fxml", "Search Book");
    }

    private void handleReturnBook(ActionEvent event) {
        openWindow("/ui/views/ReturnBookView.fxml", "Return Book");
    }

    private void handleViewBooks(ActionEvent event) {
        openWindow("/ui/views/ViewBooks.fxml", "All Books");
    }

    private void handleViewMembers(ActionEvent event) {
        openWindow("/ui/views/ViewMembers.fxml", "All Members");
    }

    private void handleIssuedBooks(ActionEvent event) {
        openWindow("/ui/views/IssueBookView.fxml", "Issued Books");
    }

    @FXML
    private void handleIssueBookTab(ActionEvent event) {
    	openWindow("/ui/views/IssueBookView.fxml", "Issued Books");
    }

    @FXML
    private void handleReturnBookTab(ActionEvent event) {
        openWindow("/ui/views/ReturnBookView.fxml", "Return Book");
    }
}
