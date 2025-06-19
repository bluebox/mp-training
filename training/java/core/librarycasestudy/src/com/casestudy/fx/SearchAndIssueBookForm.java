package com.casestudy.fx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.casestudy.domain.IssueRecord;
import com.casestudy.serviceimpl.Service;
import com.casestudy.util.DBUtil;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class SearchAndIssueBookForm extends VBox {

    private final ToggleGroup bookToggleGroup = new ToggleGroup();
    private final VBox resultsBox = new VBox(5);
    private final Button confirmButton = new Button("Confirm Book");
    private final TextField memberIdField = new TextField();
    private final PauseTransition searchDelay = new PauseTransition(Duration.millis(300));

    public SearchAndIssueBookForm() {
        setSpacing(10);
        setPadding(new Insets(10));

        Label title = new Label("Search Book by Name");
        title.setFont(new Font(18));

        TextField searchField = new TextField();
        searchField.setPromptText("Enter book title");
        addLiveLimiter(searchField, 50);

        // Live search using debounce
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            searchDelay.setOnFinished(e -> searchBooks(newVal.trim()));
            searchDelay.playFromStart();
        });

        confirmButton.setText("Issue Book");
        confirmButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        confirmButton.setVisible(false);

        memberIdField.setPromptText("Enter Member ID");
        memberIdField.setVisible(false);

        confirmButton.setOnAction(e -> {
            RadioButton selected = (RadioButton) bookToggleGroup.getSelectedToggle();
            if (selected != null) {
                int bookId = (int) selected.getUserData();
                String memberIdStr = memberIdField.getText().trim();

                try {
                	Service service = new Service();
                    int memberId = Integer.parseInt(memberIdStr);
                    if (service.getMemberById(memberId)!=null) {
                        IssueRecord issueBook = new IssueRecord(bookId, memberId);
                        if(service.issueBookService(issueBook)) {
                        	UtilMethods.showAlert(Alert.AlertType.INFORMATION, "Success", "Book issued successfully!");
                            searchField.clear();
                            bookToggleGroup.selectToggle(null);
                            resultsBox.getChildren().clear();
                            memberIdField.clear();
                            memberIdField.setVisible(false);
                            confirmButton.setVisible(false);
                        }
                        else {
                        	UtilMethods.showAlert(Alert.AlertType.ERROR, "Sorry !!!", "Book Cannot be issued");
                            searchField.clear();
                            bookToggleGroup.selectToggle(null);
                            resultsBox.getChildren().clear();
                            memberIdField.clear();
                            memberIdField.setVisible(false);
                            confirmButton.setVisible(false);
                        }
                        
                    } else {
                        UtilMethods.showAlert(Alert.AlertType.ERROR, "Error", "Invalid member ID.");
                    }
                } catch (NumberFormatException ex) {
                    UtilMethods.showAlert(Alert.AlertType.ERROR, "Error", "Member ID must be a number.");
                }
            }
        });

        getChildren().addAll(title, searchField, resultsBox, memberIdField, confirmButton);
    }

    private void searchBooks(String title) {
        resultsBox.getChildren().clear();
        bookToggleGroup.getToggles().clear();
        confirmButton.setVisible(false);
        memberIdField.setVisible(false);

        if (title.isEmpty()) {
            return;
        }

        
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Books WHERE title LIKE ?");
            stmt.setString(1, "%" + title + "%");
            ResultSet rs = stmt.executeQuery();

            List<RadioButton> radioButtons = new ArrayList<>();

            while (rs.next()) {
                int bookId = rs.getInt("bookId");
                String bookTitle = rs.getString("title");

                RadioButton rb = new RadioButton(bookTitle + " (ID: " + bookId + ")");
                rb.setToggleGroup(bookToggleGroup);
                rb.setUserData(bookId);
                radioButtons.add(rb);
            }

            if (radioButtons.isEmpty()) {
                resultsBox.getChildren().add(new Label("No books found."));
            } else {
                resultsBox.getChildren().addAll(radioButtons);
                confirmButton.setVisible(true);
                memberIdField.setVisible(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    private boolean isValidMember(int memberId) {
//        try (Connection conn = DBUtil.getConnection()) {
//            PreparedStatement stmt = conn.prepareStatement("SELECT memberId FROM Member WHERE memberId = ?");
//            stmt.setInt(1, memberId);
//            ResultSet rs = stmt.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    private void addLiveLimiter(TextField field, int maxLength) {
        field.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() > maxLength) {
                field.setText(oldVal);
            }
        });
    }
}
