
package com;

import javax.activation.MailcapCommandMap;

import com.services.BookService;
import com.services.IssueRecordService;
import com.services.MemberService;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        
  	try {
//    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/UI/AddBookForm.fxml"));
//
//    		Parent root = loader.load();
//
//            Scene scene = new Scene(root, 400, 350); 
//            stage.setTitle("Add Book Form");
//            stage.setScene(scene);
//           stage.show();
            
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/UI/ViewMembers.fxml"));
           Parent root = loader.load();
          stage.setScene(new Scene(root, 800, 400));
         stage.setTitle("View Members");
        stage.show();
  		
  		
//  		MemberService ms= new MemberService();
//  		ms.addMember("Pavan","pasham@gmail.com",1351661022,'M',"Reddy Gudem");
  		
  		
//  		BookService bs=new BookService();
//  		bs.updateBook(2,"Royal Gun","xyz","Moral",'I', 'I');
  		
//  		BookService bs=new BookService();
//  		bs.returnBook(1);
  		
  		IssueRecordService issueRecordService=new IssueRecordService();
//  		issueRecordService.issueBook(1, 1);
//  		issueRecordService.returnBook(1, 1);
  		issueRecordService.issueBook(1, 2);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage()+" yes");
		}
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
