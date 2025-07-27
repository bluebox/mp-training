package com.library.controller;

import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UpdateAvailabilityController {

    @FXML private ChoiceBox<String> availabilityChoice;
    @FXML private Label statusLabel;

    private Book book;
    private final BookServiceImplementation bookService = new BookServiceImplementation();

    public void setBook(Book book) {
        this.book = book;
        availabilityChoice.getItems().clear();
        availabilityChoice.getItems().addAll("A", "I");
        availabilityChoice.setValue(String.valueOf(book.getAvailability()));
    }

    @FXML
    public void handleUpdate() {
        try {
            String selected = availabilityChoice.getValue();
            if (selected == null) {
                statusLabel.setText("Please select availability");
                return;
            }

            char newAvailability = selected.charAt(0);
            if (newAvailability == book.getAvailability()) {
                statusLabel.setText("No changes made.");
                return;
            }

            bookService.updateAvailability(book.getBookId(), newAvailability);
            statusLabel.setText("Availability updated!");

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                Stage stage = (Stage) availabilityChoice.getScene().getWindow();
                stage.close();
            });
            pause.play();

        } catch (Exception e) {
            statusLabel.setText("Error updating availability.");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancel() {
        Stage stage = (Stage) availabilityChoice.getScene().getWindow();
        stage.close();
    }
}
