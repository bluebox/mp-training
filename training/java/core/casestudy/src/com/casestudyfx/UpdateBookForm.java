package com.casestudyfx;
import com.casestudy.Availability;
import com.casestudy.Book;
import com.casestudy.Service;
import com.casestudy.Status;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class UpdateBookForm extends VBox {

    public UpdateBookForm() {
        this.setPadding(new Insets(10));

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        TextField bookIdField = new TextField();
        TextField titleField = new TextField();
        TextField authorField = new TextField();
        TextField categoryField = new TextField();
        
        addTextLimiter(bookIdField, 10);
        addTextLimiter(titleField, 50);
        addTextLimiter(authorField, 50);
        addTextLimiter(categoryField, 50);

        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("A - Active", "I - Inactive");

        ComboBox<String> availabilityBox = new ComboBox<>();
        availabilityBox.getItems().addAll("A - Available", "I - Issued");

        Button submit = new Button("Update Book");
        submit.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");
        
        try {
        	submit.setOnAction(e -> {
            	
            	Service service = new Service();
            	String id = bookIdField.getText().trim();
            	int bookId = Integer.parseInt(id);
            	
            	String title = titleField.getText().trim();
            	String author = authorField.getText().trim();
            	String category = categoryField.getText().trim();

            	// Get selected status and availability (e.g., "A - Active")
            	String statusSelected = statusBox.getValue(); 
            	String availabilitySelected = availabilityBox.getValue(); // e.g., "I - Issued"
            	
            	if (id.isEmpty() || title.isEmpty() || author.isEmpty() || category.isEmpty() ||
            		    statusSelected == null || availabilitySelected == null) {
            		    UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all fields.");
            		    return;
            	}
            	// Extract the first character from each selection
            	String status = statusSelected.substring(0,1);         // 'A' or 'I'
            	String  availability = availabilitySelected.substring(0,1); // 'A' or 'I'

            	// Create the Book object
            	Book book = new Book(bookId , title, author, category.toLowerCase(), Status.fromCode(status), Availability.fromCode(availability));
            	if(service.updateBookService(book)) {
            		UtilMethods.showAlert(Alert.AlertType.INFORMATION, "Success", "Book updated successfully!");
            		bookIdField.clear();
            		titleField.clear();
                     authorField.clear();
                     categoryField.clear();
                     statusBox.setValue(null);
                     availabilityBox.setValue(null);
            	}
            	else {
                    UtilMethods.showAlert(Alert.AlertType.ERROR, "Failure", "Book updation failed.");
                }
                // TODO: Write JDBC update logic here
                System.out.println("Updated Book ID: " + bookIdField.getText());
            });
        }
        catch(Exception e) {
        	e.printStackTrace();
        }

        

        grid.add(new Label("Book ID:"), 0, 0);
        grid.add(bookIdField, 1, 0);
        grid.add(new Label("Title:"), 0, 1);
        grid.add(titleField, 1, 1);
        grid.add(new Label("Author:"), 0, 2);
        grid.add(authorField, 1, 2);
        grid.add(new Label("Category:"), 0, 3);
        grid.add(categoryField, 1, 3);
        grid.add(new Label("Status:"), 0, 4);
        grid.add(statusBox, 1, 4);
        grid.add(new Label("Availability:"), 0, 5);
        grid.add(availabilityBox, 1, 5);
        grid.add(submit, 1, 6);

        this.getChildren().add(grid);
    }
    private void addTextLimiter(TextField textField, int maxLength) {
        textField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() > maxLength) {
                textField.setText(oldText);
            }
        });
    }
}

