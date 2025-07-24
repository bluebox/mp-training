//import controller.ConController;
//import controller.MainController;
//import dao.bookDao;
//import service.BookService;
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	 public void start(Stage primaryStage) throws Exception {
	        Parent root = FXMLLoader.load(getClass().getResource("/resources/Main.fxml"));
	        primaryStage.setTitle("Library Management System");
	        primaryStage.setScene(new Scene(root, 400, 400));
	        primaryStage.show();
	    }

	 public static void main(String[] args) {
		//ConController bc=new ConController(new bookService(new bookDao()));
		//bc.addBook();
		//bc.updateBookDetails();
		//bc.updateBookAvailability();
		//bc.viewAllBooks();
		//MainController mn=new MainController();
		launch(args);
		
	}

}
