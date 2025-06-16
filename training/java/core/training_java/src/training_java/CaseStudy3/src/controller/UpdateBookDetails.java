package controller;

import Pojo.Book;
import Service.LibraryService;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class UpdateBookDetails {
    public VBox getView() {
        VBox root = new VBox(10);

        Label heading = new Label("Update Book");

        TextField bookIdField = new TextField();
        bookIdField.setPromptText("Book ID");

        TextField titleField = new TextField();
        titleField.setPromptText("Title");

        TextField authorField = new TextField();
        authorField.setPromptText("Author");

        TextField categoryField = new TextField();
        categoryField.setPromptText("Category");

        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("Active", "Inactive");
        statusBox.setPromptText("Status");

        ComboBox<String> availabilityBox = new ComboBox<>();
        availabilityBox.getItems().addAll("Available", "Issued");
        availabilityBox.setPromptText("Availability");

        Button updateButton = new Button("Update Book");
        Label message=new Label();
        
        updateButton.setOnAction(e -> {
        	String id=bookIdField.getText();
            String auth = authorField.getText();
            String titl =titleField.getText();
            String stat = statusBox.getValue();
            String cat=categoryField.getText();
            String avail=availabilityBox.getValue();
            Book book=new Book();
            book.setBookId(Integer.parseInt(id));
            book.setAuthor(auth);
            book.setAvailability(avail.toUpperCase().charAt(0));
            book.setCategory(cat);
            book.setStatus(stat.toUpperCase().charAt(0));
            book.setTitle(titl);
            LibraryService lib;
			try {
				lib = new LibraryService();
				lib.updateBook(book);
				lib.updateBookAvailability(Integer.parseInt(id), avail.toUpperCase().charAt(0));
				message.setText("Book Updated Successfully");
			} catch (Exception e1) {
				message.setText("Error Occured");
				e1.printStackTrace();
			}
           
        });

        root.getChildren().addAll(
            heading,
            bookIdField,
            titleField,
            authorField,
            categoryField,
            statusBox,
            availabilityBox,
            updateButton,message
        );

        return root;
    }
}
