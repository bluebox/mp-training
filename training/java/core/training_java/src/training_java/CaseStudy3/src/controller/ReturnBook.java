package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import Pojo.IssueRecord;
import Service.LibraryService;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ReturnBook {
	 public VBox getView() {
	        VBox root = new VBox(10);

	        Label heading = new Label("Return Book");

	        TextField issueIdField = new TextField();
	        issueIdField.setPromptText("Issued ID");
	        
	        ComboBox<String> statusBox = new ComboBox<>();
	        statusBox.getItems().addAll("Returned", "Issued"); // A - Active I - Inactive
	        statusBox.setPromptText("Status");


	        DatePicker returnDatePicker = new DatePicker();
	        returnDatePicker.setPromptText("Return Date");
	        returnDatePicker.setValue(LocalDate.now()); 


	        Button submitButton = new Button("Submit");

	        Label message = new Label();

	        submitButton.setOnAction(e -> {
	            String issueId = issueIdField.getText();
	            LocalDate returnDate = returnDatePicker.getValue();

	            if (issueId.isEmpty()) {
	                message.setText("Please fill all required fields.");
	            } else {
	                message.setText("Book transaction recorded (not yet saved to DB).");
	            }
	            try {
	            	LibraryService lib = new LibraryService();
	            	lib.returnBook(Integer.parseInt(issueId));
	            	List<IssueRecord> rec=lib.viewIssuedRecords();
	            	var obj=rec.stream()
	                  .filter(r -> r.getIssueId() == Integer.parseInt(issueId))
	                  .map(IssueRecord::getBookId)
	                  .findFirst().orElse(null);
	            	System.out.println(obj);
	            	lib.updateBookAvailability(Integer.valueOf(obj),'A');
	            	
	            }
	            catch(Exception ex){
	            	ex.printStackTrace();
	            	
	            }
	        });

	        root.getChildren().addAll(
	            heading,
	            issueIdField,
	            statusBox,
	            returnDatePicker,
	            submitButton,
	            message
	        );

	        return root;
	    }

}
