package controller;

import java.io.IOException;
import java.util.List;

import Domain.Book;
import Domain.BookAvailability;
import Domain.BookStatus;
import Service.ServiceLayer;
import application.Main;
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
	ServiceLayer service=new ServiceLayer();
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	//for showing tables
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
	    private TableColumn<Book, BookStatus> Status;

	    @FXML
	    private TableColumn<Book, BookAvailability> Availability;

	    private final ObservableList<Book> bookList = FXCollections.observableArrayList();
	
	    @FXML
	    public void initialize() throws Exception {
	    	  try {
	    	        if (BookId != null && BookName != null && Author != null && Category != null
	    	                && Status != null && Availability != null && Book != null) {

	    	            BookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
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
	    
	    

	
	// for adding books
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
	   
	  //for updating books 
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
	   
	// for going to books
   public void gotoHome(ActionEvent event) throws IOException {
	   root= FXMLLoader.load(getClass().getResource("/applicationview/HomeView.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    stage.setTitle("Library Management System");
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
   }
   
   public void gotoAddBook(ActionEvent event) throws IOException {
	    root= FXMLLoader.load(getClass().getResource("/applicationview/addBook.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    stage.setTitle("Library Management System");
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
   }
   
   public void gotoUpdateBook(ActionEvent event) throws IOException {
	   root= FXMLLoader.load(getClass().getResource("/applicationview/updatebook.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    stage.setTitle("Library Management System");
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
   }
   
    public BookStatus updatestatus=null;
   public void handleupdateStatusSelection(ActionEvent event) {
       if (uActive.isSelected()) {
           updatestatus= BookStatus.ACTIVE;
       } else if (uInactive.isSelected()) {
    	   updatestatus= BookStatus.INACTIVE;
       }
   }
   
   public BookStatus status=null;
   public void handleStatusSelection(ActionEvent event) {
       if (Active.isSelected()) {
           status= BookStatus.ACTIVE;
       } else if (Inactive.isSelected()) {
    	   status= BookStatus.INACTIVE;
       }
     
   }
 
   public BookAvailability available=null;
   public void handleAvailabilitySelection(ActionEvent event) {
       if (Available.isSelected()) {
           available= BookAvailability.AVAILABLE;
       } else if (Issued.isSelected()) {
    	   available=BookAvailability.ISSUED;
       }
	 
   }
   

   public void addBook(ActionEvent event) throws IOException{
	   try{
		   addBook(titlefield.getText(),Authorfield.getText(),categoryfield.getText(),status,available);
		   showAlert("Adding Book to the Book table ......");
	   root= FXMLLoader.load(getClass().getResource("/applicationview/HomeView.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    stage.setTitle("Library Management System");
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	   }catch(Exception e) {
		   e.printStackTrace();
		  showAlert(e.getMessage());
	   }
   }
   
   
   public void updatebook(ActionEvent event)throws IOException {
	 try {
		 updateBookDetails(Integer.parseInt(uIDfield.getText()),utitlefield.getText(),uAuthor.getText(),ucategory.getText(),updatestatus);
		 showAlert("Updating the Book record ......");
		 root= FXMLLoader.load(getClass().getResource("/applicationview/HomeView.fxml"));
		    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		    stage.setTitle("Library Management System");
		    scene=new Scene(root);
		    stage.setScene(scene);
		    stage.show();
	 }catch(Exception e) {
		 showAlert(e.getMessage());
	 }
   }

  
		
	public String addBook(String Title,String Author,String category,BookStatus status,BookAvailability availability) throws Exception{
		try{
			service.addBook(Title, Author, category, status, availability);
		    return "Book Added to DB Successfully";
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
			
		}
	}
	
	public String updateBookDetails(int id,String Title,String Author,String category,BookStatus status) throws Exception{
		try{
			Book book_1=service.getBookbyId(id);
			Book book=null;
			if(book_1 !=null) {
			book=service.updateBookDetails(id, Title, Author, category, status, BookAvailability.AVAILABLE);
			}
			return "Book details after updating"+book.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
			
		}
	}
	
	
//	public String updateAvailability(int BookId,BookAvailability availability) {
//		try {
//			Book book=service.getBookbyId(BookId);
//			Book book_1=null;
//			if(book != null) {
//				book_1=service.updateAvailability(BookId, book.getTitle(), book.getAuthor(), book.getCategory(), book.getStatus(), availability);
//			}
//			return "Book's Availability status is"+book_1.getAvailability();
//		}catch(Exception e) {
//			e.printStackTrace();
//			return "Exception occured during updation of the Book"+e.getMessage();
//		}
//	}
	
	public List<Book> getBooksList() throws Exception {
		List<Book> books=null;
		try {
		books=service.getBooks();
		}
		catch(Exception e) {
			System.out.println(e);
			throw new Exception(e.getMessage());
		}
		return books;
	}

}
