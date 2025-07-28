package controller;

import java.util.List;

import Domain.Book;
import Domain.Member;
import Service.ServiceLayer;
import Domain.BookAvailability;
import Domain.BookStatus;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import Domain.Gender;
public class ViewAllMembersController {
	

	
	
	
	 ServiceLayer memberService=new ServiceLayer();
	    

	    public void handleviewallmembers() throws Exception {
	    	BorderPane root = new BorderPane();
	    	List<Member> list=memberService.getAllMembers();
	    	TableView<Member> table = new TableView<Member>();

	    	TableColumn <Member, String> Member_name= new TableColumn<Member, String>("Membername"); 
	    	Member_name.setCellValueFactory (new PropertyValueFactory<Member, String>("Name"));

	    	TableColumn <Member, String> Email = new TableColumn<Member, String>("Memberemail"); 
	    	Email.setCellValueFactory (new PropertyValueFactory<Member, String>("Email"));

	    	TableColumn <Member, String> Mobile = new TableColumn <Member, String>("Mobile");
	    	Mobile.setCellValueFactory(new PropertyValueFactory<Member, String>("Mobile"));
	    	
	    	TableColumn <Member, Gender> Gender = new TableColumn <Member, Gender>("Gender");
	    	Gender.setCellValueFactory(new PropertyValueFactory<Member, Gender>("gender"));
	    	TableColumn <Member, String> Address = new TableColumn <Member, String>("Address");
	    	Address.setCellValueFactory(new PropertyValueFactory<Member, String>("Address"));
	    	
	    	table.getColumns().add(Member_name);
	    	System.out.println(Member_name.toString());
	    	System.out.println(Email.toString());
	    	table.getColumns().add(Email);

	    	table.getColumns().add(Mobile);
	    	table.getColumns().add(Address);
	    	table.getColumns().add(Gender);
	    	
	    	table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    	for(Member m:list)
	    	table.getItems().add(m); 
	    	
	    	root.setCenter(table);

	    	Scene scene = new Scene(root, 588, 388);

	    	Stage primaryStage =new Stage();
	    	primaryStage.setTitle("TableView Demo");

	    	primaryStage.setScene(scene);

	    	primaryStage.show();
	    }
}
