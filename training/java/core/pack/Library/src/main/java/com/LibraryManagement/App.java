package Library.src.main.java.com.LibraryManagement;

import Library.src.main.java.com.LibraryManagement.ui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Custom functional interface to launch a UI window
@FunctionalInterface
interface WindowLauncher {
    void launch(Stage stage);
}

public class App extends Application {

    private Stage activeWindow; // Track currently open child window

    @Override
    public void start(Stage primaryStage) {
        Button addBookBtn = new Button("Add Book");
        Button addMemberBtn = new Button("Add Member");
        Button issueBookBtn = new Button("Issue Book");
        Button returnBookBtn = new Button("Return Book");
        Button viewBooksBtn = new Button("View Books");
        Button viewMembersBtn = new Button("View Members");

        // Use lambda to launch windows
        addBookBtn.setOnAction(e -> openWindow(stage -> new AddBookUI().start(stage)));
        addMemberBtn.setOnAction(e -> openWindow(stage -> new AddMemberUI().start(stage)));
        issueBookBtn.setOnAction(e -> openWindow(stage -> new IssueBookUI().start(stage)));
        returnBookBtn.setOnAction(e -> openWindow(stage -> new ReturnBookUI().start(stage)));
        viewBooksBtn.setOnAction(e -> openWindow(stage -> new ViewBooksUI().start(stage)));
        viewMembersBtn.setOnAction(e -> openWindow(stage -> new ViewMembersUI().start(stage)));

        VBox root = new VBox(10, addBookBtn, addMemberBtn, issueBookBtn, returnBookBtn, viewBooksBtn, viewMembersBtn);
        root.setPadding(new javafx.geometry.Insets(20));

        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private void openWindow(WindowLauncher launcher) {
        if (activeWindow != null && activeWindow.isShowing()) {
            activeWindow.toFront();
            return;
        }

        Stage newStage = new Stage();
        activeWindow = newStage;

        try {
            launcher.launch(newStage);
            newStage.setOnCloseRequest(e -> activeWindow = null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}










//package Library.src.main.java.com.LibraryManagement;
//
///**
// * Hello world!
// *
// */
//
//import Library.src.main.java.com.LibraryManagement.ui.*;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class App extends Application {
//    @Override
//    public void start(Stage primaryStage) {
//        Button addBookBtn = new Button("Add Book");
//        Button addMemberBtn = new Button("Add Member");
//        Button issueBookBtn = new Button("Issue Book");
//        Button returnBookBtn = new Button("Return Book");
//        Button viewBooksBtn = new Button("View Books");
//        Button viewMembersBtn = new Button("View Members");
//
//        // Launch respective UIs
//        addBookBtn.setOnAction(e -> new AddBookUI().start(new Stage()));
//        addMemberBtn.setOnAction(e -> new AddMemberUI().start(new Stage()));
//        issueBookBtn.setOnAction(e -> new IssueBookUI().start(new Stage()));
//        returnBookBtn.setOnAction(e -> new ReturnBookUI().start(new Stage()));
//        viewBooksBtn.setOnAction(e -> new ViewBooksUI().start(new Stage()));
//        viewMembersBtn.setOnAction(e -> new ViewMembersUI().start(new Stage()));
//
//        VBox root = new VBox(10, addBookBtn, addMemberBtn, issueBookBtn, returnBookBtn, viewBooksBtn, viewMembersBtn);
//        root.setPadding(new javafx.geometry.Insets(20));
//
//        primaryStage.setTitle("Library Management System");
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//
