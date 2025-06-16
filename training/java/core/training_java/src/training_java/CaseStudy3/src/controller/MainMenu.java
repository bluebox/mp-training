package controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {
    private Stage stage;

    public MainMenu(Stage primaryStage) {
        this.stage = primaryStage;
    }
    
    public VBox getView() {
        VBox root = new VBox(15);
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("main-container");

        Label title = new Label("Welcome to Library Management System");
        title.getStyleClass().add("title");

        Button addBook = new Button("➕ Add Book");
        Button addMember = new Button("➕ Add Member");
        Button viewBooks = new Button("📚 View All Books");
        Button viewMembers = new Button("👥 View All Members");
        Button issueBook = new Button("📖 Issue Book");
        Button returnBook = new Button("📥 Return Book");
        Button updateBook = new Button("🔄 Update Book Details");
        Button updateMember = new Button("🔄 Update Member");
        Button issuedRecords = new Button("📜 Show Issued Records");
        Button memberIssued = new Button("🔍 Member Issued Books");
        Button bookUsedBy = new Button("🔎 Book Used By Members");

        addBook.setOnAction(e -> switchScene(new AddBook().getView()));
        addMember.setOnAction(e -> switchScene(new AddMember().getView()));
        viewBooks.setOnAction(e -> {
			try {
				switchScene(new ViewBooks().getView());
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		});
        viewMembers.setOnAction(e -> {
			try {
				switchScene(new ViewMembers().getView());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
        issueBook.setOnAction(e -> switchScene(new IssueBook().getView()));
        returnBook.setOnAction(e -> switchScene(new ReturnBook().getView()));
        updateBook.setOnAction(e -> switchScene(new UpdateBookDetails().getView()));
        updateMember.setOnAction(e -> switchScene(new UpdateMember().getView()));
        issuedRecords.setOnAction(e -> {
			try {
				switchScene(new IssuedRecords().getView());
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		});
        memberIssued.setOnAction(e -> switchScene(new MemberIssuedBooks().getView()));
        bookUsedBy.setOnAction(e -> switchScene(new BookUsedByMembers().getView()));

        root.getChildren().addAll(
            title,
            addBook, addMember, viewBooks, viewMembers,
            issueBook, returnBook, updateBook, updateMember,
            issuedRecords, memberIssued, bookUsedBy
        );

        return root;
    }


    private void switchScene(VBox featureView) {
        VBox container = new VBox(15);
        container.setAlignment(Pos.TOP_CENTER);
        container.getStyleClass().add("feature-container");

        Button backBtn = new Button("⬅ Back to Menu");
        backBtn.getStyleClass().add("back-button");
        backBtn.setOnAction(e -> {
            VBox mainMenu = getView(); 
            Scene scene = new Scene(mainMenu, 600, 500);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            stage.setScene(scene);
        });

        container.getChildren().addAll(backBtn, featureView);
        Scene scene = new Scene(container, 600, 500);
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        stage.setScene(scene);
    }
}
