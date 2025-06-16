package com.LibraryManagement.controller;

import com.LibraryManagement.services.BookService;
import com.LibraryManagement.services.IssueRecordService;
import com.LibraryManagement.services.MemberService;
import com.LibraryManagement.services.impl.BookServiceImpl;
import com.LibraryManagement.services.impl.IssueRecordServiceImpl;
import com.LibraryManagement.services.impl.MemberServiceImpl;
import com.LibraryManagement.utilites.pojos.Book;
import com.LibraryManagement.utilites.pojos.IssueRecord;
import com.LibraryManagement.utilites.pojos.Member;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ui extends Application {

    private StackPane contentArea;
    private BookService bookServiceImpl=new BookServiceImpl();
    private MemberService memberServiceImpl=new MemberServiceImpl();
    private IssueRecordService issueRecordServiceImpl=new IssueRecordServiceImpl();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        
        VBox menu = new VBox(10);
        menu.setPadding(new Insets(15));
        menu.setStyle("-fx-background-color: grey;");
        menu.setPrefWidth(200);
        Button btnHome = new Button("Home");
        Button btnAddBook = new Button("Add Book");
        Button btnAddMember = new Button("Add Member");
        Button btnIssueBook = new Button("Issue Book to Member");
        Button btnViewBooks = new Button("View All Books");
        Button btnViewMembers = new Button("View All Members");
        Button btnViewIssues = new Button("View IssueRecord");
        Button btnReturnBook = new Button("Return Book");
        Button btnUpdateBook = new Button("Update Book Status");
        Button btnUpdateMember = new Button("Update Member Details");
        Button btnViewBookLog = new Button("View Book Log");
        Button btnViewMemberLog = new Button("View Member Log");
        Button btnViewIssueRecordLog = new Button("View IssueRecord Log");

        for (Button btn : new Button[]{
                btnHome,btnAddBook, btnAddMember, btnIssueBook,btnViewBooks, btnViewMembers,
                btnViewIssues, btnReturnBook, btnUpdateBook, btnUpdateMember,btnViewBookLog,
                btnViewMemberLog,btnViewIssueRecordLog
        }) {
            btn.setMinWidth(170);
        }

        menu.getChildren().addAll(
                btnHome,btnAddBook, btnAddMember, btnIssueBook, btnViewBooks, btnViewMembers,
                btnViewIssues, btnReturnBook,
                btnUpdateBook, btnUpdateMember,btnViewBookLog,
                btnViewMemberLog,btnViewIssueRecordLog
        );

        contentArea = new StackPane();
        contentArea.setPadding(new Insets(15));
        showWelcomeMessage();
        btnHome.setOnAction(e ->showWelcomeMessage());
        btnAddBook.setOnAction(e -> showAddBookForm());
        btnAddMember.setOnAction(e -> showAddMemberForm());
        btnViewBooks.setOnAction(e -> showViewBooks());
        btnViewMembers.setOnAction(e -> showViewMembers());
        btnViewIssues.setOnAction(e -> showViewIssueRecords());
        btnIssueBook.setOnAction(e -> showIssueBookForm());
        btnReturnBook.setOnAction(e -> showReturnBookForm());
        btnUpdateBook.setOnAction(e -> showUpdateBookForm());
        btnUpdateMember.setOnAction(e -> showUpdateMemberForm());
        btnViewBookLog.setOnAction(e -> showViewBookLog());
        btnViewMemberLog.setOnAction(e -> showViewMemberLog());
        btnViewIssueRecordLog.setOnAction(e -> showViewIssueRecordLog());
        BorderPane root = new BorderPane();
        root.setLeft(menu);
        root.setCenter(contentArea);

        Scene scene = new Scene(root,1100, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showWelcomeMessage() {
        VBox welcomeBox = new VBox(10);
        welcomeBox.setAlignment(Pos.CENTER);
        Label title = new Label("Welcome to the Library Management System");
        title.setStyle("-fx-font-size: 20px; -fx-text-fill: #333;");
        Label subtitle = new Label("Use the menu on the left to get started.");
        subtitle.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        welcomeBox.getChildren().addAll(title, subtitle);
        contentArea.getChildren().setAll(welcomeBox);
    }
    private void restrictTextField(TextField textField, int maxLength) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z]*") || newValue.length() > maxLength) {
                textField.setText(oldValue);
            }
        });
    }


    private void showAddBookForm() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(20));

        TextField titleField = new TextField();
        titleField.setPromptText("Enter book title");
        restrictTextField(titleField,20);

        TextField authorField = new TextField();
        authorField.setPromptText("Enter author name");
        restrictTextField(authorField,20);

        TextField categoryField = new TextField();
        categoryField.setPromptText("Enter category");
        restrictTextField(categoryField,15);

        Button submitBtn = new Button("Submit");

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        form.getChildren().addAll(
                new Label("Title:"), titleField,
                new Label("Author:"), authorField,
                new Label("Category:"), categoryField,
                submitBtn, messageLabel
        );

        submitBtn.setOnAction(e -> {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String category = categoryField.getText().trim();

            if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
                messageLabel.setText("All fields are required.");
                return;
            }

            Book book = new Book(title, author, category, 'A', 'A');
            try {
				if(bookServiceImpl.addBookService(book)) {
					messageLabel.setStyle("-fx-text-fill: green;");
				    messageLabel.setText("Book added successfully!");
				    titleField.clear();
				    authorField.clear();
				    categoryField.clear();

				}else {
					messageLabel.setStyle("-fx-text-fill: red;");
				    messageLabel.setText("Book Already Exists!");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        });

        contentArea.getChildren().setAll(new ScrollPane(form));
    }

    private void showAddMemberForm() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("Enter member name");
        restrictTextField(nameField,20);

        TextField emailField = new TextField();
        emailField.setPromptText("Enter email");
        
        TextField numberField = new TextField();
        numberField.setPromptText("Enter phone number");
        restrictNumericField(numberField, 10);

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);

        HBox genderBox = new HBox(10, maleRadio, femaleRadio);

        Button submitBtn = new Button("Submit");
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        form.getChildren().addAll(
            new Label("Name:"), nameField,
            new Label("Email:"), emailField,
            new Label("Phone Number:"), numberField,
            new Label("Gender:"), genderBox,
            submitBtn, messageLabel
        );

        submitBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String numberText = numberField.getText().trim();
            RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();

            if (name.isEmpty() || email.isEmpty() || numberText.isEmpty() || selectedGender == null) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required.");
                return;
            }
		    if (!isValidGmail(email)) {
		        showAlert(Alert.AlertType.ERROR, "Invalid Email", "Email must be in '@gmail.com' or '@gmail.in' format.");
		        return;
		    }
		
		    if (!numberText.matches("\\d{10}")) {
		        showAlert(Alert.AlertType.ERROR, "Invalid Phone", "Phone number must be exactly 10 digits.");
		        return;
		    }
            long phone;
            try {
                phone = Long.parseLong(numberText);
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Mobile number must be numeric.");
                return;
            }

            char gender = selectedGender.getText().equalsIgnoreCase("Male") ? 'M' : 'F';

            Member member = new Member(name, email, phone, gender);
            try {
                if (memberServiceImpl.addMemberService(member)) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Member added successfully.");
                    nameField.clear();
                    emailField.clear();
                    numberField.clear();
                    genderGroup.selectToggle(null);
                    messageLabel.setText("");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Member already exists.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while adding member.");
            }
        });

        contentArea.getChildren().setAll(new ScrollPane(form));
    }
    private void restrictNumericField(TextField textField, int maxLength) {
        textField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*") || newVal.length() > maxLength) {
                textField.setText(oldVal);
            }
        });
    }

    private boolean isValidGmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@gmail\\.(com|in)$");
    }
    private void showViewBooks() {
        TableView<Book> table = new TableView<>();

        TableColumn<Book, Integer> idCol = new TableColumn<>("Book ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookId()).asObject());

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));

        TableColumn<Book, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));

        TableColumn<Book, Character> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStatus()));

        TableColumn<Book, Character> availabilityCol = new TableColumn<>("Availability");
        availabilityCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getAvailability()));

        table.getColumns().addAll(idCol, titleCol, authorCol, categoryCol, statusCol, availabilityCol);

        try {
            table.getItems().addAll(bookServiceImpl.viewAllBooksService());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load books from database.");
            return;
        }


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(new Label("List of All Books:"), table);

        contentArea.getChildren().setAll(new ScrollPane(layout));
    }

    private void showViewMembers() {
        TableView<Member> table = new TableView<>();


        TableColumn<Member, Integer> idCol = new TableColumn<>("Member ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberId()).asObject());

        TableColumn<Member, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        TableColumn<Member, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));

        TableColumn<Member, Long> mobileCol = new TableColumn<>("Mobile Number");
        mobileCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getMobile()).asObject());

        TableColumn<Member, Character> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getGender()));

        table.getColumns().addAll(idCol, nameCol, emailCol, mobileCol, genderCol);

        try {
            table.getItems().addAll(memberServiceImpl.viewAllMembersService());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load members from database.");
            return;
        }

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(new Label("List of All Members:"), table);

        contentArea.getChildren().setAll(new ScrollPane(layout));
    }

    private void showIssueBookForm() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(20));

        TextField bookIdField = new TextField();
        bookIdField.setPromptText("Enter Book ID");

        TextField memberIdField = new TextField();
        memberIdField.setPromptText("Enter Member ID");

        Button submitBtn = new Button("Issue Book");
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        form.getChildren().addAll(
                new Label("Book ID:"), bookIdField,
                new Label("Member ID:"), memberIdField,
                submitBtn, messageLabel
        );

        submitBtn.setOnAction(e -> {
            String bookIdText = bookIdField.getText().trim();
            String memberIdText = memberIdField.getText().trim();

            if (bookIdText.isEmpty() || memberIdText.isEmpty()) {
                messageLabel.setText("Both fields are required.");
                return;
            }

            try {
                int bookId = Integer.parseInt(bookIdText);
                int memberId = Integer.parseInt(memberIdText);

                boolean issued = issueRecordServiceImpl.issueBookToMember(bookId, memberId);

                if (!issued) {
                    messageLabel.setStyle("-fx-text-fill: green;");
                    messageLabel.setText("Book issued successfully!");
                }

            } catch (NumberFormatException ex) {
                messageLabel.setText("Book ID and Member ID must be numeric.");
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while issuing the book.");
            }
        });

        contentArea.getChildren().setAll(new ScrollPane(form));
    }
    private void showViewIssueRecords() {
        TableView<IssueRecord> table = new TableView<>();

        TableColumn<IssueRecord, Integer> issueIdCol = new TableColumn<>("Issue ID");
        issueIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIssueId()).asObject());

        TableColumn<IssueRecord, Integer> bookIdCol = new TableColumn<>("Book ID");
        bookIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookId()).asObject());

        TableColumn<IssueRecord, Integer> memberIdCol = new TableColumn<>("Member ID");
        memberIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberId()).asObject());

        TableColumn<IssueRecord, Character> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStatus()));

        TableColumn<IssueRecord, String> issuedDateCol = new TableColumn<>("Issued Date");
        issuedDateCol.setCellValueFactory(data -> {
            java.util.Date date = data.getValue().getIssueDate();
            String formattedDate = (date == null) ? "" : new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
            return new javafx.beans.property.SimpleStringProperty(formattedDate);
        });

        TableColumn<IssueRecord, String> returnedDateCol = new TableColumn<>("Returned Date");
        returnedDateCol.setCellValueFactory(data -> {
            java.util.Date date = data.getValue().getReturnDate();
            String formattedDate = (date == null) ? "" : new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
            return new javafx.beans.property.SimpleStringProperty(formattedDate);
        });

        table.getColumns().addAll(issueIdCol, bookIdCol, memberIdCol, statusCol, issuedDateCol, returnedDateCol);

        try {
            table.getItems().addAll(issueRecordServiceImpl.viewAllIssuedRecords());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load issue records from database.");
            return;
        }

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(new Label("List of Issue Records:"), table);

        contentArea.getChildren().setAll(new ScrollPane(layout));
    }

    private void showReturnBookForm() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(20));

        TextField issueIdField = new TextField();
        issueIdField.setPromptText("Enter Issue ID");

        Button returnBtn = new Button("Return Book");
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        form.getChildren().addAll(new Label("Issue ID:"), issueIdField, returnBtn, messageLabel);

        returnBtn.setOnAction(e -> {
            String issueIdText = issueIdField.getText().trim();
            if (issueIdText.isEmpty()) {
                messageLabel.setText("Issue ID is required.");
                return;
            }
            int issueId;
            try {
                issueId = Integer.parseInt(issueIdText);
            } catch (NumberFormatException ex) {
                messageLabel.setText("Issue ID must be a valid number.");
                return;
            }

            try {
                boolean returned = issueRecordServiceImpl.returnBook(issueId);
                if (returned) {
                    messageLabel.setStyle("-fx-text-fill: green;");
                    messageLabel.setText("Book returned successfully!");
                } else {
                    messageLabel.setStyle("-fx-text-fill: red;");
                    messageLabel.setText("Unable to return: Book is not currently issued or invalid Issue ID.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Error occurred while returning the book.");
            }
        });

        contentArea.getChildren().setAll(new ScrollPane(form));
    }

    private void showUpdateBookForm() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(20));

        TextField bookIdField = new TextField();
        bookIdField.setPromptText("Enter Book ID");

        ToggleGroup availabilityGroup = new ToggleGroup();
        RadioButton availableRadio = new RadioButton("Active");
        availableRadio.setToggleGroup(availabilityGroup);
        availableRadio.setUserData('A');  
        RadioButton issuedRadio = new RadioButton("InActive");
        issuedRadio.setToggleGroup(availabilityGroup);
        issuedRadio.setUserData('I');   

        HBox availabilityBox = new HBox(10, availableRadio, issuedRadio);

        Button updateBtn = new Button("Update Book Status");
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        form.getChildren().addAll(
            new Label("Book ID:"), bookIdField,
            new Label("Availability:"), availabilityBox,
            updateBtn, messageLabel
        );

        updateBtn.setOnAction(e -> {
            String bookIdText = bookIdField.getText().trim();
            if (bookIdText.isEmpty()) {
                messageLabel.setText("Book ID is required.");
                return;
            }

            int bookId;
            try {
                bookId = Integer.parseInt(bookIdText);
            } catch (NumberFormatException ex) {
                messageLabel.setText("Book ID must be a valid number.");
                return;
            }

            Toggle selectedToggle = availabilityGroup.getSelectedToggle();
            if (selectedToggle == null) {
                messageLabel.setText("Please select availability.");
                return;
            }

            char availability = (char) selectedToggle.getUserData();

            try {
                boolean updated = bookServiceImpl.updateBookService(bookId, availability);
                if (updated) {
                    messageLabel.setStyle("-fx-text-fill: green;");
                    messageLabel.setText("Book availability updated successfully!");
                } else {
                    messageLabel.setStyle("-fx-text-fill: red;");
                    messageLabel.setText("Book not found in the database.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Error occurred while updating book availability.");
            }
        });

        contentArea.getChildren().setAll(new ScrollPane(form));
    }
    
    private void showUpdateMemberForm() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(20));

        TextField idField = new TextField();
        idField.setPromptText("Enter Member ID");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter new name");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter new email");

        TextField mobileField = new TextField();
        mobileField.setPromptText("Enter new mobile number");

        Button updateBtn = new Button("Update Member");
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        form.getChildren().addAll(
            new Label("Member ID:"), idField,
            new Label("Name:"), nameField,
            new Label("Email:"), emailField,
            new Label("Mobile Number:"), mobileField,
            updateBtn, messageLabel
        );

        updateBtn.setOnAction(e -> {
            String idText = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String mobileText = mobileField.getText().trim();

            if (idText.isEmpty() || name.isEmpty() || email.isEmpty() || mobileText.isEmpty()) {
                messageLabel.setText("All fields are required.");
                return;
            }

            int memberId;
            long mobile;

            try {
                memberId = Integer.parseInt(idText);
                mobile = Long.parseLong(mobileText);
            } catch (NumberFormatException ex) {
                messageLabel.setText("Member ID and Mobile must be numeric.");
                return;
            }

            try {
                boolean updated = memberServiceImpl.updateMemberService(memberId, name, email, mobile);
                if (updated) {
                    messageLabel.setStyle("-fx-text-fill: green;");
                    messageLabel.setText("Member updated successfully!");
                    idField.clear();
                    nameField.clear();
                    emailField.clear();
                    mobileField.clear();
                } else {
                    messageLabel.setStyle("-fx-text-fill: red;");
                    messageLabel.setText("Member not found in the database.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                messageLabel.setText("Error occurred while updating member.");
            }
        });

        contentArea.getChildren().setAll(new ScrollPane(form));
    }
    
    private void showViewBookLog() {
        TableView<Book> table = new TableView<>();

        TableColumn<Book, Integer> idCol = new TableColumn<>("Book ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookId()).asObject());

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));

        TableColumn<Book, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));

        TableColumn<Book, Character> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStatus()));

        TableColumn<Book, Character> availabilityCol = new TableColumn<>("Availability");
        availabilityCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getAvailability()));

        table.getColumns().addAll(idCol, titleCol, authorCol, categoryCol, statusCol, availabilityCol);

        try {
            table.getItems().addAll(bookServiceImpl.viewAllBooksLogService());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load book log.");
            return;
        }

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(new Label("Book Log History:"), table);

        contentArea.getChildren().setAll(new ScrollPane(layout));
    }

    private void showViewMemberLog() {
        TableView<Member> table = new TableView<>();

        TableColumn<Member, Integer> idCol = new TableColumn<>("Member ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberId()).asObject());

        TableColumn<Member, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        TableColumn<Member, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));

        TableColumn<Member, Long> mobileCol = new TableColumn<>("Mobile Number");
        mobileCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getMobile()).asObject());

        TableColumn<Member, Character> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getGender()));

        table.getColumns().addAll(idCol, nameCol, emailCol, mobileCol, genderCol);

        try {
            table.getItems().addAll(memberServiceImpl.viewAllMembersLogService());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load member log.");
            return;
        }

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(new Label("Member Log History:"), table);

        contentArea.getChildren().setAll(new ScrollPane(layout));
    }

    private void showViewIssueRecordLog() {
        TableView<IssueRecord> table = new TableView<>();

        TableColumn<IssueRecord, Integer> issueIdCol = new TableColumn<>("Issue ID");
        issueIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIssueId()).asObject());

        TableColumn<IssueRecord, Integer> bookIdCol = new TableColumn<>("Book ID");
        bookIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookId()).asObject());

        TableColumn<IssueRecord, Integer> memberIdCol = new TableColumn<>("Member ID");
        memberIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberId()).asObject());

        TableColumn<IssueRecord, Character> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStatus()));

        TableColumn<IssueRecord, String> issueDateCol = new TableColumn<>("Issued Date");
        issueDateCol.setCellValueFactory(data -> {
            java.util.Date date = data.getValue().getIssueDate();
            String formattedDate = (date == null) ? "" : new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
            return new javafx.beans.property.SimpleStringProperty(formattedDate);
        });

        TableColumn<IssueRecord, String> returnDateCol = new TableColumn<>("Returned Date");
        returnDateCol.setCellValueFactory(data -> {
            java.util.Date date = data.getValue().getReturnDate();
            String formattedDate = (date == null) ? "" : new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
            return new javafx.beans.property.SimpleStringProperty(formattedDate);
        });

        table.getColumns().addAll(issueIdCol, bookIdCol, memberIdCol, statusCol, issueDateCol, returnDateCol);

        try {
            table.getItems().addAll(issueRecordServiceImpl.viewAllIssuedRecordLog());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load issue record log.");
            return;
        }

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(new Label("Issue Record Log History:"), table);

        contentArea.getChildren().setAll(new ScrollPane(layout));
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
