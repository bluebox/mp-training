package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import Service.ServiceLayer;
import Domain.Book;
import Domain.IssueRecord;
import Domain.IssueStatus;
import Domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import DAO.BookImplementation;
import DAO.IssueRecordImplementation;

public class ReportMenuController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	BookImplementation b= new BookImplementation();
	IssueRecordImplementation i = new IssueRecordImplementation();
	
	
    @FXML
    private void handleOverdueBooks(ActionEvent event) throws IOException {
    	System.out.println("reachedme");
    	root= FXMLLoader.load(getClass().getResource("/applicationview/OverdueBooks.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
    }

    @FXML
    private void handleBooksPerCategory(ActionEvent event) throws IOException {
    	System.out.println("reachedme");
    	root= FXMLLoader.load(getClass().getResource("/applicationview/BooksPerCategory.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
    }

    @FXML
    private void handleActiveIssuedBooks(ActionEvent event) throws IOException {
    	System.out.println("reachedme");
    	root= FXMLLoader.load(getClass().getResource("/applicationview/ActiveIssuedMembers.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show(); 
    }
    
    
    @FXML 
    private void gotoHome(ActionEvent event) throws IOException{
    	root= FXMLLoader.load(getClass().getResource("/applicationview/HomeView.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show(); 
    }

	
		 
		public Map<String,Long> count_of_books_percategory() throws SQLException {
			List<Book> list=b.getBooks();
			
			Map<String,Long> map=list.stream().collect(Collectors.groupingBy(Book::getCategory,Collectors.counting()));
			map.forEach((x,y)->System.out.println(x+" "+y));
			
			return map;
		}
		

  
}
