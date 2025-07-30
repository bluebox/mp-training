import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Date;

public class LibraryApp extends Application {
    private BookService bookService;
    private MemberService memberService;
    private IssueService issueService;

    private ObservableList<Book> bookData = FXCollections.observableArrayList();
    private ObservableList<Member> memberData = FXCollections.observableArrayList();
    private ObservableList<IssueRecord> issueData = FXCollections.observableArrayList();
    private ObservableList<IssueRecord> allIssueData = FXCollections.observableArrayList();

    private TableView<Book> bookTable = new TableView<>();
    private TableView<Member> memberTable = new TableView<>();
    private TableView<IssueRecord> issueTable = new TableView<>();
    private TableView<IssueRecord> reportTable = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        // Initialize services
        BookDAO bookDAO = new BookDAOImpl();
        MemberDAO memberDAO = new MemberDAOImpl();
        IssueRecordDAO issueRecordDAO = new IssueRecordDAOImpl();

        bookService = new BookService(bookDAO);
        memberService = new MemberService(memberDAO);
        issueService = new IssueService(issueRecordDAO, bookDAO, memberDAO);

        // Load data
        refreshBookTable();
        refreshMemberTable();
        refreshIssueTable();

        TabPane tabPane = new TabPane();

        Tab bookTab = new Tab("Books", createBookTab());
        Tab memberTab = new Tab("Members", createMemberTab());
        Tab issueTab = new Tab("Issue/Return", createIssueTab());
        Tab reportTab = new Tab("Reports", createReportTab());

        tabPane.getTabs().addAll(bookTab, memberTab, issueTab, reportTab);

        Scene scene = new Scene(tabPane, 900, 600);
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createBookTab() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));

        // Form
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();
        Label authorLabel = new Label("Author:");
        TextField authorField = new TextField();
        Label categoryLabel = new Label("Category:");
        TextField categoryField = new TextField();

        form.add(titleLabel, 0, 0);
        form.add(titleField, 1, 0);
        form.add(authorLabel, 0, 1);
        form.add(authorField, 1, 1);
        form.add(categoryLabel, 0, 2);
        form.add(categoryField, 1, 2);

        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> {
            try {
                Book book = new Book();
                book.setTitle(titleField.getText());
                book.setAuthor(authorField.getText());
                book.setCategory(categoryField.getText());
                book.setStatus('A');
                book.setAvailability('A');
                bookService.addBook(book);
                refreshBookTable();
                titleField.clear();
                authorField.clear();
                categoryField.clear();
            } catch (IllegalArgumentException ex) {
                showAlert("Error", ex.getMessage());
            }
        });

        // Table setup
        setupBookTable();

        layout.getChildren().addAll(form, addButton, bookTable);
        return layout;
    }

    private void setupBookTable() {
        bookTable.getColumns().clear();

        TableColumn<Book, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Book, String> availCol = new TableColumn<>("Availability");
        availCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

        bookTable.getColumns().addAll(idCol, titleCol, authorCol, categoryCol, statusCol, availCol);
        bookTable.setItems(bookData);
    }

    private void refreshBookTable() {
        bookData.setAll(bookService.getAllBooks());
    }

    private VBox createMemberTab() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));

        // Form
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label mobileLabel = new Label("Mobile:");
        TextField mobileField = new TextField();
        Label genderLabel = new Label("Gender (M/F):");
        TextField genderField = new TextField();
        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();

        form.add(nameLabel, 0, 0);
        form.add(nameField, 1, 0);
        form.add(emailLabel, 0, 1);
        form.add(emailField, 1, 1);
        form.add(mobileLabel, 0, 2);
        form.add(mobileField, 1, 2);
        form.add(genderLabel, 0, 3);
        form.add(genderField, 1, 3);
        form.add(addressLabel, 0, 4);
        form.add(addressField, 1, 4);

        Button addButton = new Button("Register Member");
        addButton.setOnAction(e -> {
            try {
                Member member = new Member();
                member.setName(nameField.getText());
                member.setEmail(emailField.getText());
                member.setMobile(mobileField.getText());
                member.setGender(genderField.getText().charAt(0));
                member.setAddress(addressField.getText());
                memberService.registerMember(member);
                refreshMemberTable();
                nameField.clear();
                emailField.clear();
                mobileField.clear();
                genderField.clear();
                addressField.clear();
            } catch (IllegalArgumentException ex) {
                showAlert("Error", ex.getMessage());
            }
        });

        // Table setup
        setupMemberTable();

        layout.getChildren().addAll(form, addButton, memberTable);
        return layout;
    }

    private void setupMemberTable() {
        memberTable.getColumns().clear();

        TableColumn<Member, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));

        TableColumn<Member, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Member, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Member, String> mobileCol = new TableColumn<>("Mobile");
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));

        TableColumn<Member, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Member, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        memberTable.getColumns().addAll(idCol, nameCol, emailCol, mobileCol, genderCol, addressCol);
        memberTable.setItems(memberData);
    }

    private void refreshMemberTable() {
        memberData.setAll(memberService.getAllMembers());
    }

    private VBox createIssueTab() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));

        // Issue section
        HBox issueBox = new HBox(10);
        Label bookIdLabel = new Label("Book ID:");
        TextField bookIdField = new TextField();
        Label memberIdLabel = new Label("Member ID:");
        TextField memberIdField = new TextField();
        Button issueButton = new Button("Issue Book");

        issueButton.setOnAction(e -> {
            try {
                int bookId = Integer.parseInt(bookIdField.getText());
                int memberId = Integer.parseInt(memberIdField.getText());
                issueService.issueBook(bookId, memberId, new Date());
                refreshIssueTable();
                bookIdField.clear();
                memberIdField.clear();
            } catch (Exception ex) {
                showAlert("Error", ex.getMessage());
            }
        });
        issueBox.getChildren().addAll(bookIdLabel, bookIdField, memberIdLabel, memberIdField, issueButton);

        // Return section
        HBox returnBox = new HBox(10);
        Label issueIdLabel = new Label("Issue ID:");
        TextField issueIdField = new TextField();
        Button returnButton = new Button("Return Book");

        returnButton.setOnAction(e -> {
            try {
                int issueId = Integer.parseInt(issueIdField.getText());
                issueService.returnBook(issueId, new Date());
                refreshIssueTable();
                issueIdField.clear();
            } catch (Exception ex) {
                showAlert("Error", ex.getMessage());
            }
        });
        returnBox.getChildren().addAll(issueIdLabel, issueIdField, returnButton);

        // Table setup
        setupIssueTable();

        layout.getChildren().addAll(issueBox, returnBox, issueTable);
        return layout;
    }

    private void setupIssueTable() {
        issueTable.getColumns().clear();

        TableColumn<IssueRecord, Integer> issueIdCol = new TableColumn<>("Issue ID");
        issueIdCol.setCellValueFactory(new PropertyValueFactory<>("issueId"));

        TableColumn<IssueRecord, Integer> bookIdCol = new TableColumn<>("Book ID");
        bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<IssueRecord, Integer> memberIdCol = new TableColumn<>("Member ID");
        memberIdCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));

        TableColumn<IssueRecord, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<IssueRecord, Date> issueDateCol = new TableColumn<>("Issue Date");
        issueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

        issueTable.getColumns().addAll(issueIdCol, bookIdCol, memberIdCol, statusCol, issueDateCol);
        issueTable.setItems(issueData);
    }

    private void refreshIssueTable() {
        issueData.setAll(issueService.getActiveIssueRecords());
    }

    private VBox createReportTab() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));

        // Report controls
        HBox controls = new HBox(10);
        Button allIssuesBtn = new Button("All Issues");
        Button byMemberBtn = new Button("By Member");
        Button byBookBtn = new Button("By Book");

        TextField searchField = new TextField();
        searchField.setPromptText("Enter ID");

        allIssuesBtn.setOnAction(e -> {
            allIssueData.setAll(issueService.getAllIssueRecords());
            reportTable.setItems(allIssueData);
        });

        byMemberBtn.setOnAction(e -> {
            try {
                int memberId = Integer.parseInt(searchField.getText());
                allIssueData.setAll(issueService.getIssueRecordsByMember(memberId));
                reportTable.setItems(allIssueData);
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid Member ID");
            }
        });

        byBookBtn.setOnAction(e -> {
            try {
                int bookId = Integer.parseInt(searchField.getText());
                allIssueData.setAll(issueService.getIssueRecordsByBook(bookId));
                reportTable.setItems(allIssueData);
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid Book ID");
            }
        });

        controls.getChildren().addAll(allIssuesBtn, byMemberBtn, byBookBtn, searchField);

        // Table setup
        setupReportTable();

        layout.getChildren().addAll(controls, reportTable);
        return layout;
    }

    private void setupReportTable() {
        reportTable.getColumns().clear();

        TableColumn<IssueRecord, Integer> issueIdCol = new TableColumn<>("Issue ID");
        issueIdCol.setCellValueFactory(new PropertyValueFactory<>("issueId"));

        TableColumn<IssueRecord, Integer> bookIdCol = new TableColumn<>("Book ID");
        bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<IssueRecord, Integer> memberIdCol = new TableColumn<>("Member ID");
        memberIdCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));

        TableColumn<IssueRecord, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<IssueRecord, Date> issueDateCol = new TableColumn<>("Issue Date");
        issueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

        TableColumn<IssueRecord, Date> returnDateCol = new TableColumn<>("Return Date");
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        reportTable.getColumns().addAll(issueIdCol, bookIdCol, memberIdCol, statusCol, issueDateCol, returnDateCol);
        reportTable.setItems(allIssueData);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}