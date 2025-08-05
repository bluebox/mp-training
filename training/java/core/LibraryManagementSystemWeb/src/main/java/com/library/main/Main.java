package com.library.main;

import com.library.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainDashboard.fxml"));
        Parent root = loader.load();
        stage.setTitle("Library Management System");
        stage.setScene(new Scene(root, 800, 500));
        stage.setResizable(false);
        stage.show();

        //primary stage global
        MainController.setPrimaryStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}



//
//LibraryManagementSystemWeb/
//│
//├── src/main/java/
//│   └── com.library
//│       ├── controller/        # Replaces JavaFX controllers with Servlets
//│       │    ├── AddBookServlet.java
//│       │    ├── AddMemberServlet.java
//│       │    ├── EditMemberServlet.java
//│       │    ├── IssueBookServlet.java
//│       │    ├── ReturnBookServlet.java
//│       │    ├── UpdateBookServlet.java
//│       │    ├── ViewBooksServlet.java
//│       │    └── ViewMembersServlet.java
//│       │
//│       ├── dao/
//│       │   ├── interfaces/    # No change
//│       │   │    ├── BookDao.java
//│       │   │    ├── IssueRecordDao.java
//│       │   │    └── MemberDao.java
//│       │   └── impl/          # No change
//│       │        ├── BookDaoImplementation.java
//│       │        ├── IssueRecordDaoImplementation.java
//│       │        └── MemberDaoImplementation.java
//│       │
//│       ├── service/
//│       │   ├── interfaces/    # No change
//│       │   │    ├── BookService.java
//│       │   │    ├── IssueRecordService.java
//│       │   │    └── MemberService.java
//│       │   └── impl/          # No change
//│       │        ├── BookServiceImplementation.java
//│       │        ├── IssueRecordServiceImplementation.java
//│       │        └── MemberServiceImplementation.java
//│       │
//│       ├── model/             # No change
//│       │    ├── Book.java
//│       │    ├── IssueRecord.java
//│       │    └── Member.java
//│       │
//│       ├── util/              # No change
//│       └── exception/         # No change
//│
//├── src/main/webapp/
//│   ├── WEB-INF/
//│   │    ├── web.xml           # Servlet mappings
//│   │    └── lib/              # (If you add external JARs)
//│   │
//│   ├── jsp/                   # Replaces FXML files
//│   │    ├── addBook.jsp
//│   │    ├── addMember.jsp
//│   │    ├── editBook.jsp
//│   │    ├── editMember.jsp
//│   │    ├── issueBook.jsp
//│   │    ├── returnBook.jsp
//│   │    ├── updateBook.jsp
//│   │    ├── viewBooks.jsp
//│   │    ├── viewMembers.jsp
//│   │    └── reports.jsp
//│   │
//│   ├── css/                   # Existing CSS
//│   │    ├── dashboard.css
//│   │    ├── viewBooks.css
//│   │    └── viewMembers.css
//│   │
//│   ├── js/                    # If needed for scripts
//│   └── index.jsp              # Main dashboard page
//│
//├── pom.xml                    # If Maven project
//└── README.md
//























