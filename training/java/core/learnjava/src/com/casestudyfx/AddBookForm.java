package com.casestudyfx;
import com.casestudy.Availability;
import com.casestudy.Book;
import com.casestudy.Service;
import com.casestudy.Status;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AddBookForm extends VBox {

    public AddBookForm() {
        this.setPadding(new Insets(10));

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        TextField titleField = new TextField();
        TextField authorField = new TextField();
        TextField categoryField = new TextField();

        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("A - Active", "I - Inactive");

        ComboBox<String> availabilityBox = new ComboBox<>();
        availabilityBox.getItems().addAll("A - Available", "I - Issued");

        Button submit = new Button("Add Book");
        submit.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");
        
        try {
        	submit.setOnAction(e -> {
            	
            	Service service = new Service();
            	String title = titleField.getText().trim();
            	String author = authorField.getText().trim();
            	String category = categoryField.getText().trim();

            	// Get selected status and availability (e.g., "A - Active")
            	String statusSelected = statusBox.getValue(); // e.g., "A - Active"
            	String availabilitySelected = availabilityBox.getValue(); // e.g., "I - Issued"
            	
            	if (title.isEmpty() || author.isEmpty() || category.isEmpty() ||
            		    statusSelected == null || availabilitySelected == null) {
            		    UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all fields.");
            		    return;
            	}
            	// Extract the first character from each selection
            	String status = statusSelected.substring(0,1);         // 'A' or 'I'
            	String  availability = availabilitySelected.substring(0,1); // 'A' or 'I'

            	// Create the Book object
            	Book book = new Book(title, author, category.toLowerCase(), Status.fromCode(status), Availability.fromCode(availability));
            	if(service.addBook(book)) {
            		UtilMethods.showAlert(Alert.AlertType.INFORMATION, "Success", "Book created successfully!");
            		 titleField.clear();
                     authorField.clear();
                     categoryField.clear();
                     statusBox.setValue(null);
                     availabilityBox.setValue(null);
                     System.out.println("Book added: " + titleField.getText());
            	}
            	else {
                    UtilMethods.showAlert(Alert.AlertType.ERROR, "Failure", "Book creation failed.");
                }
                // TODO: Add logic to insert book
                
            });
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        

        grid.add(new Label("Title:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Author:"), 0, 1);
        grid.add(authorField, 1, 1);
        grid.add(new Label("Category:"), 0, 2);
        grid.add(categoryField, 1, 2);
        grid.add(new Label("Status:"), 0, 3);
        grid.add(statusBox, 1, 3);
        grid.add(new Label("Availability:"), 0, 4);
        grid.add(availabilityBox, 1, 4);
        grid.add(submit, 1, 5);

        this.getChildren().add(grid);
    }
}
