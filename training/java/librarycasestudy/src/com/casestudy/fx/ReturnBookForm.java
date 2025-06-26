package com.casestudy.fx;
import com.casestudy.domain.IssueRecord;
import com.casestudy.serviceimpl.Service;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ReturnBookForm extends VBox {

    public ReturnBookForm() {
        this.setPadding(new Insets(10));

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        TextField bookIdField = new TextField();
        TextField memberIdField = new TextField();
        
        addDigitLimiter(bookIdField, 10);
        addDigitLimiter(memberIdField, 10);

        Button submit = new Button("Return Book");
        submit.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");
        
        submit.setOnAction(e -> {
            // Insert logic for returning the book
        	String bookIdText = bookIdField.getText().trim();
        	String memberIdText = memberIdField.getText().trim();

        	if (bookIdText.isEmpty() || memberIdText.isEmpty()) {
        	    UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter both Book ID and Member ID.");
        	    return;
        	}

        	try {
        	    int bookId = Integer.parseInt(bookIdText);
        	    int memberId = Integer.parseInt(memberIdText);

        	    //Create IssueRecord POJO
        	    IssueRecord issueRecord = new IssueRecord(bookId, memberId);
        	    
        	    Service service = new Service();
        	    
        	    if (service.returnBookService(issueRecord)) {
        	        UtilMethods.showAlert(Alert.AlertType.INFORMATION, "Success", "Book returned successfully!");
        	        System.out.println("Book returned successfully");
        	        bookIdField.clear();
        	        memberIdField.clear();
        	          
        	    } else {
        	    	UtilMethods.showAlert(Alert.AlertType.ERROR, "Failure", "Failed to return the book.");
        	    	System.out.println("Book return failed");
        	    }

        	} catch (NumberFormatException ex) {
        		UtilMethods.showAlert(Alert.AlertType.ERROR, "Input Error", "Book ID and Member ID must be numbers.");
        	}
       
        });

        grid.add(new Label("Book ID:"), 0, 0);
        grid.add(bookIdField, 1, 0);
        grid.add(new Label("Member ID:"), 0, 1);
        grid.add(memberIdField, 1, 1);
        grid.add(submit, 1, 2);

        this.getChildren().add(grid);
    }
    

    private void addDigitLimiter(TextField field, int maxLength) {
        field.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*") || newVal.length() > maxLength) {
                field.setText(oldVal);
            }
        });
    }
}
