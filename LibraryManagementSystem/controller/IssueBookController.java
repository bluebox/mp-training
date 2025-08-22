package com.library.controller.IssueAndReturn;

import com.library.controller.MainController;
import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.Member;
import com.library.service.impl.BookServiceImplementation;
import com.library.service.impl.IssueRecordServiceImplementation;
import com.library.service.impl.MemberServiceImplementation;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.List;

public class IssueBookController {

    @FXML private ComboBox<Member> memberCombo;
    @FXML private ComboBox<Book> bookCombo;
    @FXML private Label statusLabel;

    private final MemberServiceImplementation memberService = new MemberServiceImplementation();
    private final BookServiceImplementation bookService = new BookServiceImplementation();
    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();

    @FXML
    public void initialize() {
        try {
            List<Member> members = memberService.fetchAllMembers();
            List<Book> availableBooks = bookService.getAvailableBooks();

            memberCombo.setItems(FXCollections.observableArrayList(members));
            bookCombo.setItems(FXCollections.observableArrayList(availableBooks));

            // Show name and ID in combo boxes
            memberCombo.setCellFactory(param -> new ListCell<Member>() {
                @Override
                protected void updateItem(Member item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getMemberId() + " - " + item.getName());
                }
            });
            memberCombo.setButtonCell(memberCombo.getCellFactory().call(null));

            bookCombo.setCellFactory(param -> new ListCell<Book>() {
                @Override
                protected void updateItem(Book item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item.getBookId() + " - " + item.getTitle());
                }
            });
            bookCombo.setButtonCell(bookCombo.getCellFactory().call(null));

        } catch (Exception e) {
            e.printStackTrace();
            showTemporaryMessage("Error loading data: " + e.getMessage(), "red");
        }
    }

    @FXML
    private void handleIssue() {
        Member selectedMember = memberCombo.getValue();
        Book selectedBook = bookCombo.getValue();

        if (selectedMember == null || selectedBook == null) {
            showTemporaryMessage("Select both member and book", "red");
            return;
        }

        IssueRecord record = new IssueRecord(
                selectedBook.getBookId(),
                selectedMember.getMemberId(),
                'I',
                LocalDate.now()
        );

        try {
            boolean success = issueService.issueBook(record);
            if (success) {
                showTemporaryMessage("Book issued successfully!", "green");
                initialize(); // reload updated data
            } else {
                showTemporaryMessage("Issuing failed!", "red");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showTemporaryMessage("Error: " + e.getMessage(), "red");
        }
    }

    private void showTemporaryMessage(String message, String color) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: " + color + ";");
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> statusLabel.setText(""));
        pause.play();
    }

    @FXML
    private void handleBack() {
        try {
            MainController.switchScene("MainDashboard.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
