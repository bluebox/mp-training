package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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
import Domain.IssueRecord;
import Domain.IssueStatus;
public class IssueHome {
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
	
	public void addIssue(ActionEvent event) throws IOException {
		root= FXMLLoader.load(getClass().getResource("/applicationview/IssueBook.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	
	
	public void issueUpdate(ActionEvent event) throws IOException {
		root= FXMLLoader.load(getClass().getResource("/applicationview/Returnbook.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	
	public void getIssues(ActionEvent event) throws IOException {
		root= FXMLLoader.load(getClass().getResource("/applicationview/Issueprintallrecords.fxml"));
	    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene=new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	

	
    @FXML
    public Service.ServiceLayer issueService = new Service.ServiceLayer();
    

    public void handleissueallmembers() throws SQLException {
    	BorderPane root = new BorderPane();
    	List<IssueRecord> list=issueService.getAllIssueRecords();
    	TableView<IssueRecord> table = new TableView<IssueRecord>();

    	TableColumn <IssueRecord, Integer> bookid= new TableColumn<IssueRecord, Integer>("BookId"); 
    	bookid.setCellValueFactory (new PropertyValueFactory<IssueRecord, Integer>("BookId"));

    	TableColumn <IssueRecord, Integer> memberid = new TableColumn<IssueRecord, Integer>("MemberId"); 
    	memberid.setCellValueFactory (new PropertyValueFactory<IssueRecord, Integer>("MemberId"));

    	TableColumn <IssueRecord, IssueStatus> Status = new TableColumn <>("Status");
    	Status.setCellValueFactory(new PropertyValueFactory<IssueRecord, IssueStatus>("status"));
    	
    	TableColumn <IssueRecord,Date> IssueDate =new TableColumn<IssueRecord,Date>("IssueDate");
    	IssueDate.setCellValueFactory(new PropertyValueFactory<IssueRecord,Date>("issueDate"));
    	
    	TableColumn <IssueRecord, Date> ReturnDate = new TableColumn <>("ReturnDate");
    	ReturnDate.setCellValueFactory(new PropertyValueFactory<IssueRecord,Date>("ReturnDate"));
    	
    	table.getColumns().add(bookid);
    	//System.out.println(Member_name.toString());
    	//System.out.println(Email.toString());
    	table.getColumns().add(memberid);

    	table.getColumns().add(Status);
    	table.getColumns().add(IssueDate);
    	table.getColumns().add(ReturnDate);
    	
    	table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    	
    	//table.getItems().add(new Member("rajesh","email",9766,"ojofa",domain.checking_enum.Gender.MALE)); 
    	
    	root.setCenter(table);
    	
    	
    	for(IssueRecord m:list)
    	table.getItems().add(m); 
    	
    	root.setCenter(table);

    	Scene scene = new Scene(root, 588, 388);

    	Stage primaryStage =new Stage();
    	primaryStage.setTitle("TableView Demo");

    	primaryStage.setScene(scene);

    	primaryStage.show();
    }
	
}

	
	
