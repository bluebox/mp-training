package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import Pojo.*;
import Service.LibraryService;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class AddBook {
    public VBox getView() {
        VBox root = new VBox(10);

        Label heading = new Label("Add Book");

        TextField title = new TextField();
        title.setPromptText("Title");

        TextField author = new TextField();
        author.setPromptText("Author");

        TextField category = new TextField();
        category.setPromptText("Category");

        ComboBox<String> status = new ComboBox<>();
        status.getItems().addAll("A", "I"); // A = Active, I = Inactive
        status.setPromptText("Status");

        ComboBox<String> availability = new ComboBox<>();
        availability.getItems().addAll("Available", "Issued"); // Y = Available, N = Not Available
        availability.setPromptText("Availability");

        Button submit = new Button("Add Book");
        Label message = new Label();

        submit.setOnAction(e -> {
            String auth = author.getText();
            String titl =title.getText();
            String stat = status.getValue();
            String cat=category.getText();
            String avail=availability.getValue();
            Book book=new Book();
            book.setAuthor(auth);
            book.setAvailability(avail.toUpperCase().charAt(0));
            book.setCategory(cat);
            book.setStatus(stat.toUpperCase().charAt(0));
            book.setTitle(titl);
            LibraryService lib;
			try {
				lib = new LibraryService();
				lib.addBook(book);
				message.setText("Book Added Successfully");
			} catch (Exception e1) {
				message.setText("Error Occured");
				e1.printStackTrace();
			}
           
        });

        root.getChildren().addAll(
            heading,
            title,
            author,
            category,
            status,
            availability,
            submit,message
        );

        return root;
    }
}
