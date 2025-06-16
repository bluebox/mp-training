package controller;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.time.LocalDate;

import Service.LibraryService;

public class IssueBook {
    public VBox getView() {
        VBox root = new VBox(10);

        Label heading = new Label("Issue Book");

        TextField bookIdField = new TextField();
        bookIdField.setPromptText("Book ID");

        TextField memberIdField = new TextField();
        memberIdField.setPromptText("Member ID");

        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("Issued", "Returned"); // A - Active I - Inactive
        statusBox.setPromptText("Status");

        DatePicker issueDatePicker = new DatePicker();
        issueDatePicker.setPromptText("Issue Date");
        issueDatePicker.setValue(LocalDate.now()); // Default to today


        Button submitButton = new Button("Submit");

        Label message = new Label();

        submitButton.setOnAction(e -> {
            String bookId = bookIdField.getText();
            String memberId = memberIdField.getText();
            String status = statusBox.getValue();
            LocalDate issueDate = issueDatePicker.getValue();
            if (bookId.isEmpty() || memberId.isEmpty() || status == null || issueDate == null) {
                message.setText("Please fill all required fields.");
            } else {
                message.setText("Book transaction recorded (not yet saved to DB).");
            }
            try {
            	LibraryService lib = new LibraryService();
                lib.issueBook(Integer.parseInt(bookId),Integer.parseInt(memberId));
                lib.updateBookAvailability(Integer.parseInt(bookId),'I');
                message.setText("Book Issued Successfully");
            } catch (Exception ex) {
                message.setText("Error Occurred");
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(
            heading,
            bookIdField,
            memberIdField,
            statusBox,
            issueDatePicker,
            submitButton,
            message
        );

        return root;
    }
}
