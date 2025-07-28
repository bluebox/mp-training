package controller;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import application.Main;
import Domain.Member;
import Service.ServiceLayer;
import Domain.Gender;
public class MemberHome {
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	
	ServiceLayer memberService=new ServiceLayer();

	
	public void gotoHome(ActionEvent event) throws IOException {
		root= FXMLLoader.load(getClass().getResource("/applicationview/HomeView.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();		
	}
	
	public void addMember(ActionEvent event) throws IOException {
		root= FXMLLoader.load(getClass().getResource("/applicationview/AddMember.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	
	
	public void memberupdate(ActionEvent event) throws IOException {
		root= FXMLLoader.load(getClass().getResource("/applicationview/UpdateMember.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	
//	public void temphomeviewmember(ActionEvent e) {
//		Parent root=null;
//		try {
//			root = FXMLLoader.load(getClass().getResource("/applicationView/ViewALLMembers.fxml"));
//			Main.history.add("/applicationView/ViewALLMembers.fxml");
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		stage=(Stage)((Node)e.getSource()).getScene().getWindow();
//		scene=new Scene(root);
//		stage.setScene(scene);
//		stage.show();
//		
//		
//	}

	
	

	    

	    public void handleviewallmembers() throws Exception {
	    	BorderPane root = new BorderPane();
	    	List<Member> list=memberService.getAllMembers();
	    	TableView<Member> table = new TableView<Member>();

	    	TableColumn <Member, String> Member_name= new TableColumn<Member, String>("Membername"); 
	    	Member_name.setCellValueFactory (new PropertyValueFactory<Member, String>("Name"));

	    	TableColumn <Member, String> Email = new TableColumn<Member, String>("Memberemail"); 
	    	Email.setCellValueFactory (new PropertyValueFactory<Member, String>("Email"));

	    	TableColumn <Member, Integer> Mobile = new TableColumn <Member, Integer>("Mobile");
	    	Mobile.setCellValueFactory(new PropertyValueFactory<Member, Integer>("Mobile"));
	    	TableColumn <Member, String> Address = new TableColumn <Member, String>("Address");
	    	Address.setCellValueFactory(new PropertyValueFactory<Member, String>("Address"));
	    	TableColumn <Member, Gender> Gender = new TableColumn <Member, Gender>("Gender");
	    	Gender.setCellValueFactory(new PropertyValueFactory<Member, Gender>("gender"));
	    	table.getColumns().add(Member_name);
	    	System.out.println(Member_name.toString());
	    	System.out.println(Email.toString());
	    	table.getColumns().add(Email);

	    	table.getColumns().add(Mobile);
	    	table.getColumns().add(Address);
	    	table.getColumns().add(Gender);
	    	
	    	table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    	for(Member m:list) {	
	        System.out.println(m.getGender())	;	
	    	table.getItems().add(m); 
	    	}
	    	root.setCenter(table);

	    	Scene scene = new Scene(root, 588, 388);

	    	Stage primaryStage =new Stage();
	    	primaryStage.setTitle("TableView Demo");

	    	primaryStage.setScene(scene);

	    	primaryStage.show();
	    }
}
