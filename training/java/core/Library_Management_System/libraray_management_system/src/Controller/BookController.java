package Controller;

import java.io.IOException;
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

	@FXML
	public void showAlert(String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(msg);
		alert.show();
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

	public Status updatestatus = null;

	public void handleupdateStatusSelection(ActionEvent event) {
		if (uActive.isSelected()) {
			updatestatus = domain.checking_enum.Status.ACTIVE;
		} else if (uInactive.isSelected()) {
			updatestatus = domain.checking_enum.Status.INACTIVE;
		}
	}

	public Status status = null;

	public void handleStatusSelection(ActionEvent event) {
		if (Active.isSelected()) {
			status = domain.checking_enum.Status.ACTIVE;
		} else if (Inactive.isSelected()) {
			status = domain.checking_enum.Status.INACTIVE;
		}
	}

	public Availability available = null;

	public void handleAvailabilitySelection(ActionEvent event) {
		if (Available.isSelected()) {
			available = domain.checking_enum.Availability.AVAILABLE;
		} else if (Issued.isSelected()) {
			available = domain.checking_enum.Availability.ISSUED;
		}
	}

	public void addBook(ActionEvent event) throws IOException {
		try {
			addBook(titlefield.getText(), Authorfield.getText(), categoryfield.getText(), status, available);
			showAlert("Adding Book to the Book table ......");
			gotoHome(event);
		} catch (Exception e) {
			e.printStackTrace();
			showAlert(e.getMessage());
		}
	}

	public void updatebook(ActionEvent event) throws IOException {
		try {
			updateBookDetails(Integer.parseInt(uIDfield.getText()), utitlefield.getText(), uAuthor.getText(),
					ucategory.getText(), updatestatus);
			showAlert("Updating the Book record ......");
			gotoHome(event);
		} catch (Exception e) {
			e.printStackTrace();
			showAlert(e.getMessage());
		}
	}

	public String addBook(String Title, String Author, String category, Status status, Availability availability)
			throws Exception {
		try {
			Book book = new Book(Title, Author, category, status, availability);
			service.addbooks(book);
			return "Book Added to DB Successfully";
		} catch (Exception e) {
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
}

