package com.casestudyfx;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.casestudy.DBUtil;
import com.casestudy.IssueRecord;
import com.casestudy.Service;

public class SearchAndIssueBookForm extends VBox {

    private ToggleGroup bookToggleGroup = new ToggleGroup();
    private VBox resultsBox = new VBox(2);
    private Button confirmButton = new Button("Confirm Book");
    private TextField memberIdField = new TextField();

    public SearchAndIssueBookForm() {
        setSpacing(2);
        setPadding(new Insets(10));

        Label title = new Label("Search Book by Name");
        title.setFont(new Font(18));

        TextField searchField = new TextField();
        searchField.setPromptText("Enter book title");

        Button searchBtn = new Button("Search");
        searchBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        searchBtn.setOnAction(e -> searchBooks(searchField.getText()));
        

        confirmButton.setVisible(false);
        memberIdField.setPromptText("Enter Member ID");
        memberIdField.setVisible(false);

        confirmButton.setOnAction(e -> {
            RadioButton selected = (RadioButton) bookToggleGroup.getSelectedToggle();
            if (selected != null) {
                int bookId = (int) selected.getUserData();
                String memberIdStr = memberIdField.getText();
                
                System.out.println(memberIdStr);

                try {
                    int memberId = Integer.parseInt(memberIdStr);
                    if (isValidMember(memberId)) {
                    	Service service = new Service();
                    	IssueRecord issueBook = new IssueRecord(bookId, memberId);
                    	service.issueBookService(issueBook);
                        
                        UtilMethods.showAlert(Alert.AlertType.INFORMATION, "Success", "Book issued successfully!");
                        searchField.clear();
                        bookToggleGroup.selectToggle(null);  
                        resultsBox.getChildren().clear();    
                        memberIdField.clear();               
                        memberIdField.setVisible(false);   
                        confirmButton.setVisible(false);     
                        
                    } else {
                    	UtilMethods.showAlert(Alert.AlertType.ERROR, "Error", "Invalid member ID.");
                    }
                } catch (NumberFormatException ex) {
                	UtilMethods.showAlert(Alert.AlertType.ERROR, "Error", "Member ID must be a number.");
                }
            }
        });

        getChildren().addAll(title, searchField, searchBtn, resultsBox, memberIdField, confirmButton);
    }

    private void searchBooks(String title) {
        resultsBox.getChildren().clear();
        bookToggleGroup.getToggles().clear();
        confirmButton.setVisible(false);
        memberIdField.setVisible(false);

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE title LIKE ?");
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

    private boolean isValidMember(int memberId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT memberId FROM member WHERE memberId = ?");
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
