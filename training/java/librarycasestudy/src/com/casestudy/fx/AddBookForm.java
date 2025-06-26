package com.casestudy.fx;

import com.casestudy.domain.Availability;
import com.casestudy.domain.Book;
import com.casestudy.domain.Status;
import com.casestudy.serviceimpl.Service;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

        // Realtime limit: 50 characters
        addTextLimiter(titleField, 50);
        addTextLimiter(authorField, 50);
        addNameLimiter(categoryField, 50);

        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("A - Active", "I - Inactive");

        ComboBox<String> availabilityBox = new ComboBox<>();
        availabilityBox.getItems().addAll("A - Available", "I - Issued");

        Button submit = new Button("Add Book");
        submit.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");

        submit.setOnAction(e -> {
            Service service = new Service();
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String category = categoryField.getText().trim();
            String statusSelected = statusBox.getValue();
            String availabilitySelected = availabilityBox.getValue();

            // Validation
            if (title.isEmpty() || author.isEmpty() || category.isEmpty()
                    || statusSelected == null || availabilitySelected == null) {
                UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all fields.");
                return;
            }

            if (title.length() > 50 || author.length() > 50 || category.length() > 50) {
                UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Fields must be at most 50 characters.");
                return;
            }

            
            String status = statusSelected.substring(0, 1);
            String availability = availabilitySelected.substring(0, 1);

            Book book = new Book(title, author, category.toLowerCase(), Status.fromCode(status), Availability.fromCode(availability));

            if (service.addBook(book)) {
                UtilMethods.showAlert(Alert.AlertType.INFORMATION, "Success", "Book created successfully!");
                titleField.clear();
                authorField.clear();
                categoryField.clear();
                statusBox.setValue(null);
                availabilityBox.setValue(null);
                System.out.println("Book added: " + title);
            } else {
                UtilMethods.showAlert(Alert.AlertType.ERROR, "Failure", "Book creation failed.");
            }
        });

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
    
    private void addNameLimiter(TextField field, int maxLength) {
        field.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() > maxLength || !newVal.matches("[a-zA-Z ]*") || newVal.matches(".*\\s{2,}.*")) {
                field.setText(oldVal);
            }
        });
    }
    // Reusable method for input character limiting
    private void addTextLimiter(TextField textField, int maxLength) {
        textField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() > maxLength) {
                textField.setText(oldText);
            }
        });
    }
}
