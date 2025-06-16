package Controller;

import Service.BookService;
import Service.IssueService;
import Service.MemberService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
    private BookService bookService = new BookService();
    private MemberService memberService = new MemberService();
    private IssueService issueService = new IssueService();
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void showAddBookForm() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/AddBookForm.fxml"));
        loader.setController(new AddBookController(primaryStage));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
    }

    @FXML
    private void showViewBooksForm() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/ViewBooksForm.fxml"));
        loader.setController(new ViewBooksController(primaryStage));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
    }

    @FXML
    private void showAddMemberForm() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/AddMemberForm.fxml"));
        loader.setController(new AddMemberController(primaryStage));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
    }

    @FXML
    private void showViewMembersForm() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/ViewMembersForm.fxml"));
        loader.setController(new ViewMembersController(primaryStage));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
    }

    @FXML
    private void showIssueBookForm() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/IssueBookForm.fxml"));
        loader.setController(new IssueBookController(primaryStage));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
    }

    @FXML
    private void showReturnBookForm() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/ReturnBookForm.fxml"));
        loader.setController(new ReturnBookController(primaryStage));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
    }

    @FXML
    private void showViewOverdueBooksForm() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/ViewOverdueBooksForm.fxml"));
        loader.setController(new ViewOverdueBooksController(primaryStage));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
    }

    @FXML
    private void showViewMemberIssuesForm() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/ViewMemberIssuesForm.fxml"));
        loader.setController(new ViewMemberIssuesController(primaryStage));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
    }

    @FXML
    private void showViewBookMembersForm() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/ViewBookMembersForm.fxml"));
        loader.setController(new ViewBookMembersController(primaryStage));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
    }

    @FXML
    private void showViewBooksByCategoryForm() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/ViewBooksByCategoryForm.fxml"));
        loader.setController(new ViewBooksByCategoryController(primaryStage));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
    }
}