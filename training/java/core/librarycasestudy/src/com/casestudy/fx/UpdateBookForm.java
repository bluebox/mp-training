package com.casestudy.fx;

import com.casestudy.domain.Book;
import com.casestudy.domain.Status;
import com.casestudy.serviceimplimentation.Service;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class UpdateBookForm extends VBox {
	
	

    private final Service service = new Service();

    public UpdateBookForm() {
        this.setPadding(new Insets(10));
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        // Fields
        TextField bookIdField = new TextField();
        Button fetchButton = new Button("Fetch Book");

        TextField titleField = new TextField();
        TextField authorField = new TextField();
        TextField categoryField = new TextField();
        ComboBox<String> statusBox = new ComboBox<>();
        Button updateButton = new Button("Update Book");

        // Initially disable all editable fields
        titleField.setDisable(true);
        authorField.setDisable(true);
        categoryField.setDisable(true);
        statusBox.setDisable(true);
        updateButton.setDisable(true);

        // Text limiters
        addTextLimiter(bookIdField, 10);
        addTextLimiter(titleField, 50);
        addTextLimiter(authorField, 50);
        addTextLimiter(categoryField, 50);

        statusBox.getItems().addAll("A - Active", "I - Inactive");

        // Fetch Button Action
        fetchButton.setOnAction(e -> {
            String id = bookIdField.getText().trim();
            if (id.isEmpty()) {
                UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter a Book ID.");
                return;
            }

            try {
                int bookId = Integer.parseInt(id);
                Book book = service.getBookById(bookId);
                if (book != null) {
                    // Enable fields and populate values
                    titleField.setDisable(false);
                    authorField.setDisable(false);
                    categoryField.setDisable(false);
                    statusBox.setDisable(false);
                    updateButton.setDisable(false);

                    titleField.setText(book.getTitle());
                    authorField.setText(book.getAuthor());
                    categoryField.setText(book.getCategory());
                    statusBox.setValue(book.getStatus().getCode().equals("A") ? "A - Active" : "I - Inactive");
                } else {
                    UtilMethods.showAlert(Alert.AlertType.ERROR, "Not Found", "Book ID not found.");
                }
            } catch (NumberFormatException ex) {
                UtilMethods.showAlert(Alert.AlertType.ERROR, "Invalid Input", "Book ID must be numeric.");
            }
        });

        // Update Button Action
        updateButton.setOnAction(e -> {
            String id = bookIdField.getText().trim();
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String category = categoryField.getText().trim();
            String statusSelected = statusBox.getValue();

            if (id.isEmpty() || title.isEmpty() || author.isEmpty() || category.isEmpty()
                    || statusSelected == null) {
                UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all fields.");
                return;
            }

            try {
                int bookId = Integer.parseInt(id);
                String status = statusSelected.substring(0, 1);

                // Book object without availability
                Book book = new Book(bookId, title, author, category.toLowerCase(), Status.fromCode(status), null);

                if (service.updateBookService(book)) {
                    UtilMethods.showAlert(Alert.AlertType.INFORMATION, "Success", "Book updated successfully!");
                    clearAllFields(bookIdField, titleField, authorField, categoryField, statusBox);
                    titleField.setDisable(true);
                    authorField.setDisable(true);
                    categoryField.setDisable(true);
                    statusBox.setDisable(true);
                    updateButton.setDisable(true);
                } else {
                    UtilMethods.showAlert(Alert.AlertType.ERROR, "Failure", "Book update failed.");
                }
            } catch (NumberFormatException ex) {
                UtilMethods.showAlert(Alert.AlertType.ERROR, "Invalid Input", "Book ID must be numeric.");
            }
        });

        // UI Layout
        grid.add(new Label("Book ID:"), 0, 0);
        grid.add(bookIdField, 1, 0);
        grid.add(fetchButton, 2, 0);

        grid.add(new Label("Title:"), 0, 1);
        grid.add(titleField, 1, 1);
        grid.add(new Label("Author:"), 0, 2);
        grid.add(authorField, 1, 2);
        grid.add(new Label("Category:"), 0, 3);
        grid.add(categoryField, 1, 3);
        grid.add(new Label("Status:"), 0, 4);
        grid.add(statusBox, 1, 4);
        grid.add(updateButton, 1, 5);

        this.getChildren().add(grid);
    }

    private void addTextLimiter(TextField textField, int maxLength) {
        textField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() > maxLength) {
                textField.setText(oldText);
            }
        });
    }

    private void clearAllFields(TextField id, TextField title, TextField author, TextField category,
                                ComboBox<String> statusBox) {
        id.clear();
        title.clear();
        author.clear();
        category.clear();
        statusBox.setValue(null);
    }
}
