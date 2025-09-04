package com.lms.controller;

import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.service.impl.IssueLogServiceImpl;
import com.lms.service.impl.MemberServiceImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MemberBooksController implements Initializable {

    @FXML private ComboBox<String> memberComboBox;
    @FXML private TextField emailField;
    @FXML private TableView<Book> booksTable;
    @FXML private TableColumn<Book, Integer> bookId;
    @FXML private TableColumn<Book, String> bookTitle;
    @FXML private TableColumn<Book, String> author;

    @FXML private Label errorLabel;

    private final MemberServiceImpl memberService = new MemberServiceImpl();
    private final IssueLogServiceImpl issueLogService = new IssueLogServiceImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumns();
        loadMembers();
    }

    private void setupTableColumns() {
    	
        bookId.setCellValueFactory(new PropertyValueFactory<>("book_Id"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<>("book_Title"));
        author.setCellValueFactory(new PropertyValueFactory<>("book_Author"));
    }

    private void loadMembers() {
        List<String> members = memberService.getAllMembers()
            .stream()
            .map(member -> member.getMember_Id() + ". " + member.getMember_Name())
            .collect(Collectors.toList());
        memberComboBox.getItems().addAll(members);
    }

    @FXML
    private void onMemberSelected() {
        String selectedMember = memberComboBox.getValue();
        if (selectedMember != null) {
            int memberId = Integer.parseInt(selectedMember.split("\\.")[0].trim());
            displayMemberBooks(memberId);
            emailField.clear();
            errorLabel.setText("");
        }
    }

    @FXML
    private void searchByEmail() {
        String email = emailField.getText().trim();
        
        if (email.isEmpty()) {
            errorLabel.setText("Please enter an email address");
            return;
        }

        Member member = memberService.getMemberByEmail(email);
        if (member == null) {
            errorLabel.setText("No member found with this email");
            booksTable.getItems().clear();
            return;
        }

        String memberString = member.getMember_Id() + ". " + member.getMember_Name();
        memberComboBox.setValue(memberString);
        displayMemberBooks(member.getMember_Id());
        errorLabel.setText("");
    }

    private void displayMemberBooks(int memberId) {
    	System.out.println("Trying to show books of " + memberId);
        List<Book> books = issueLogService.booksOfMember(memberId);
        booksTable.setItems(FXCollections.observableArrayList(books));
    }

    @FXML
    private void handleBack() {
        
    }
}
