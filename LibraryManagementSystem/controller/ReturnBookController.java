package com.library.controller.IssueAndReturn;

import com.library.controller.MainController;
import com.library.dao.impl.IssueRecordDaoImplementation;
import com.library.dao.impl.BookDaoImplementation;
import com.library.model.IssueRecord;
import com.library.model.Book;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReturnBookController {

    @FXML
    private ComboBox<IssueRecord> issueCombo;

    @FXML
    private Label statusLabel;

    private final IssueRecordDaoImplementation issueDao = new IssueRecordDaoImplementation();
    private final BookDaoImplementation bookDao = new BookDaoImplementation();

    @FXML
    public void initialize() {
        loadIssuedBooks();

        issueCombo.setCellFactory(listView -> new ListCell<IssueRecord>() {
            @Override
            protected void updateItem(IssueRecord item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    Book book = null;
                    try {
                        book = bookDao.getBookById(item.getBookId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String bookName = (book != null) ? book.getTitle() : "Unknown";

                    setText("Book ID: " + item.getBookId() + " (" + bookName + ")" +
                            " | Member ID: " + item.getMemberId() +
                            " | Issued on: " + item.getIssueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                }
            }
        });

        issueCombo.setButtonCell(new ListCell<IssueRecord>() {
            @Override
            protected void updateItem(IssueRecord item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Select Issued Book");
                } else {
                    Book book = null;
                    try {
                        book = bookDao.getBookById(item.getBookId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String bookName = (book != null) ? book.getTitle() : "Unknown";
                    setText("Book ID: " + item.getBookId() + " (" + bookName + ")");
                }
            }
        });
    }

    private void loadIssuedBooks() {
        List<IssueRecord> issued = issueDao.getAllIssuedRecords(); // returns only those with status = 'I'
        ObservableList<IssueRecord> observableList = FXCollections.observableArrayList(issued);
        issueCombo.setItems(observableList);
    }

    @FXML
    private void handleReturn() {
        IssueRecord selected = issueCombo.getValue();
        if (selected == null) {
            showTemporaryMessage("Please select a record to return.", "red");
            return;
        }

        boolean success = issueDao.returnBook(selected.getIssueId());
        if (success) {
            showTemporaryMessage("Book returned successfully!", "green");
            issueCombo.getItems().remove(selected); // remove from dropdown
        } else {
            showTemporaryMessage("Failed to return book.", "red");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
