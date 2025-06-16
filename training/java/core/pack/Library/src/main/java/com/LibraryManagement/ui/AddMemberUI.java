package Library.src.main.java.com.LibraryManagement.ui;
import Library.src.main.java.com.LibraryManagement.model.Member;
import Library.src.main.java.com.LibraryManagement.service.MemberService;
import Library.src.main.java.com.LibraryManagement.service.MemberServiceImpl;
import Library.src.main.java.com.LibraryManagement.dao.MemberDAOImpl;
import Library.src.main.java.com.LibraryManagement.util.DBConnection;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class AddMemberUI {
    private final MemberService memberService;

    public AddMemberUI() {
        try {
            Connection conn = DBConnection.getConnection();
            this.memberService = new MemberServiceImpl(new MemberDAOImpl(conn));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize MemberService: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during DB initialization: " + e.getMessage());
        }
    }

    public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(8);
        pane.setHgap(10);

        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField mobileField = new TextField();
        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("M", "F");
        genderBox.setValue("M");
        TextField addressField = new TextField();

        Button addButton = new Button("Register");
        Label messageLabel = new Label();

        pane.add(new Label("Member ID:"), 0, 0);
        pane.add(idField, 1, 0);
        pane.add(new Label("Name:"), 0, 1);
        pane.add(nameField, 1, 1);
        pane.add(new Label("Email:"), 0, 2);
        pane.add(emailField, 1, 2);
        pane.add(new Label("Mobile:"), 0, 3);
        pane.add(mobileField, 1, 3);
        pane.add(new Label("Gender (M/F):"), 0, 4);
        pane.add(genderBox, 1, 4);
        pane.add(new Label("Address:"), 0, 5);
        pane.add(addressField, 1, 5);
        pane.add(addButton, 1, 6);
        pane.add(messageLabel, 1, 7);

        addButton.setOnAction(e -> {
            String idText = idField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String mobileText = mobileField.getText();
            String gender = genderBox.getValue();
            String address = addressField.getText();

            if (idText.isEmpty() || name.isEmpty() || email.isEmpty() || mobileText.isEmpty() || gender == null || address.isEmpty()) {
                messageLabel.setText("Please fill in all fields.");
                return;
            }

            try {
                int memberId = Integer.parseInt(idText);
                String mobile = mobileText;

                Member member = new Member();
                member.setMemberId(memberId);
                member.setName(name);
                member.setEmail(email);
                member.setMobile(mobile);
                member.setGender(gender.charAt(0));
                member.setAddress(address);

                memberService.addMember(member);
                
                messageLabel.setText("Member registered successfully.");
                idField.clear();
                nameField.clear();
                emailField.clear();
                mobileField.clear();
                genderBox.setValue("M");
                addressField.clear();
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid input. ID and Mobile must be numbers.");
            } catch (SQLException ex) {
                messageLabel.setText("Database error: " + ex.getMessage());
            } catch (RuntimeException ex) {
                messageLabel.setText("Unexpected error: " + ex.getMessage());
            } catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        stage.setScene(new Scene(pane, 450, 350));
        stage.setTitle("Add Member");
        stage.show();
    }
}
