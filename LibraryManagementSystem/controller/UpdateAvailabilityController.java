package com.library.controller;

import java.io.IOException;

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
    private static Book selectedBook;

    public static void setSelectedBook(Book book) {
        selectedBook = book;
    }

    private Book book;
    private final BookServiceImplementation bookService = new BookServiceImplementation();

    @FXML
    public void initialize() {
        if (selectedBook != null) {
            setBook(selectedBook);
        }
    }



    public void setBook(Book book) {
        this.book = book;

        availabilityChoice.getItems().clear();
        availabilityChoice.getItems().addAll("Available", "Issued");

        // Set choice based on book's current availability
        if (book.getAvailability() == 'A') {
            availabilityChoice.setValue("Available");
        } else {
            availabilityChoice.setValue("Issued");
        }
    }

    @FXML
    public void handleUpdate() {
        try {
            String selected = availabilityChoice.getValue();
            if (selected == null) {
                statusLabel.setText("Please select availability");
                return;
            }

            char newAvailability = selected.equals("Available") ? 'A' : 'I';

            if (newAvailability == book.getAvailability()) {
                statusLabel.setText("No changes made.");
                return;
            }

            bookService.updateAvailability(book.getBookId(), newAvailability);
            statusLabel.setText("Availability updated!");

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
            	try {
					MainController.switchScene("ViewBooks.fxml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            });
            pause.play();

        } catch (Exception e) {
            statusLabel.setText("Error updating availability.");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancel() throws IOException {
    	MainController.switchScene("ViewBooks.fxml");
//        Stage stage = (Stage) availabilityChoice.getScene().getWindow();
//        stage.close();
    }
    @FXML
    private void handleBack() throws IOException {
        MainController.switchScene("ViewBooks.fxml");
    }
    @FXML
    private void handleBackDash() throws IOException {
        MainController.switchScene("MainDashboard.fxml");
    }

}
