package Controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import domain.Book;
import domain.checking_enum;
import domain.checking_enum.Availability;
import domain.checking_enum.Status;
import Service.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BookController {

    BookService service = new BookService();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Book> Book;

    @FXML
    private TableColumn<Book, Number> BookId;

    @FXML
    private TableColumn<Book, String> BookName;

    @FXML
    private TableColumn<Book, String> Author;

    @FXML
    private TableColumn<Book, String> Category;

    @FXML
    private TableColumn<Book, Status> Status;

    @FXML
    private TableColumn<Book, Availability> Availability;

    private final ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    public TextField titlefield;
    @FXML
    public TextField Authorfield;
    @FXML
    public TextField categoryfield;
    @FXML
    public RadioButton Active;
    @FXML
    public RadioButton Inactive;
    @FXML
    public ToggleGroup availability;
    @FXML
    public RadioButton Available;
    @FXML
    public RadioButton Issued;

    @FXML
    public TextField utitlefield;
    @FXML
    public TextField ucategory;
    @FXML
    public TextField uAuthor;
    @FXML
    public TextField uIDfield;
    @FXML
    public RadioButton uActive;
    @FXML
    public RadioButton uInactive;

    public Status status = null;
    public Status updatestatus = null;
    public Availability available = null;

    @FXML
    public void initialize() throws Exception {
        try {
            if (BookId != null && BookName != null && Author != null && Category != null
                    && Status != null && Availability != null && Book != null) {

                BookId.setCellValueFactory(new PropertyValueFactory<>("bookid"));
                BookName.setCellValueFactory(new PropertyValueFactory<>("title"));
                Author.setCellValueFactory(new PropertyValueFactory<>("author"));
                Category.setCellValueFactory(new PropertyValueFactory<>("category"));
                Status.setCellValueFactory(new PropertyValueFactory<>("status"));
                Availability.setCellValueFactory(new PropertyValueFactory<>("availability"));

                List<Book> returnlist = getBooksList();
                bookList.addAll(returnlist);
                Book.setItems(bookList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error initializing table: " + e.getMessage());
        }
    }

    @FXML
    public void handleStatusSelection(ActionEvent event) {
        if (Active.isSelected()) {
            status =domain.checking_enum.Status.ACTIVE;
        } else if (Inactive.isSelected()) {
            status = domain.checking_enum.Status.INACTIVE;
        }
    }

    @FXML
    public void handleupdateStatusSelection(ActionEvent event) {
        if (uActive.isSelected()) {
            updatestatus = domain.checking_enum.Status.ACTIVE;
        } else if (uInactive.isSelected()) {
            updatestatus = domain.checking_enum.Status.INACTIVE;
        }
    }

    @FXML
    public void handleAvailabilitySelection(ActionEvent event) {
        if (Available.isSelected()) {
            available = domain.checking_enum.Availability.AVAILABLE;
        } else if (Issued.isSelected()) {
            available = domain.checking_enum.Availability.ISSUED;
        }
    }

    @FXML
    public void addBook(ActionEvent event) throws IOException {
        try {
            String title = titlefield.getText();
            String author = Authorfield.getText();
            String category = categoryfield.getText();

            if (title == null || title.trim().isEmpty()
                    || author == null || author.trim().isEmpty()
                    || category == null || category.trim().isEmpty()) {
                showAlert("Please fill in Title, Author, and Category fields.");
                return;
            }

            if (status == null) {
                showAlert("Please select the book status (Active/Inactive).");
                return;
            }

            if (available == null) {
                showAlert("Please select the book availability (Available/Issued).");
                return;
            }

            addBook(title.trim(), author.trim(), category.trim(), status, available);
            showAlert("Book added successfully!");
            gotoHome(event);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error: " + e.getMessage());
        }
    }

    @FXML
    public void updatebook(ActionEvent event) throws IOException {
        try {
            String idText = uIDfield.getText();
            String title = utitlefield.getText();
            String author = uAuthor.getText();
            String category = ucategory.getText();

            if (idText == null || idText.trim().isEmpty()) {
                showAlert("Please enter the Book ID to update.");
                return;
            }

            int id = Integer.parseInt(idText.trim());

            if (title == null || title.trim().isEmpty()
                    || author == null || author.trim().isEmpty()
                    || category == null || category.trim().isEmpty()) {
                showAlert("Please fill in Title, Author, and Category fields.");
                return;
            }

            if (updatestatus == null) {
                showAlert("Please select the book status (Active/Inactive).");
                return;
            }

            updateBookDetails(id, title.trim(), author.trim(), category.trim(), updatestatus);
            
            showAlert("Book updated successfully!");
            gotoHome(event);

        } catch (NumberFormatException e) {
            showAlert("Invalid Book ID. Please enter a valid number.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error: " + e.getMessage());
        }
    }

    public String addBook(String Title, String Author, String category, Status status, Availability availability)
            throws Exception {
        try {
            Book book = new Book(Title, Author, category, status, availability);
            service.addbooks(book);
            return "Book Added to DB Successfully";
        }
        catch(SQLIntegrityConstraintViolationException e) {
        	System.out.println("hi hello how are you");
            showAlert("duplicate entry.");
            throw new SQLIntegrityConstraintViolationException(e.getMessage());

        	
        }
        
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public String updateBookDetails(int id, String Title, String Author, String category, Status status)
            throws Exception {
        try {
            Book book = new Book(Title, Author, category, status, domain.checking_enum.Availability.AVAILABLE);
            book.setBookid(id);
            service.updatebookdetails(id, book);
            return "Book details after updating: " + book.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public List<Book> getBooksList() throws Exception {
        try {
            return service.viewallbooks();
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception(e.getMessage());
        }
    }

    @FXML
    public void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void gotoHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/applicationview/HomeView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Library Management System");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gotoAddBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/applicationview/addBook.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Library Management System");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gotoUpdateBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/applicationview/updatebook.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Library Management System");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
   
    public void goToPreviousScene(ActionEvent event) {
    	try {
    		Parent root=FXMLLoader.load(getClass().getResource("/applicationView/Books.fxml"));
    		Scene scene=new Scene(root);
    		 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
    		 stage.setScene(scene);
    		 stage.show();}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
}