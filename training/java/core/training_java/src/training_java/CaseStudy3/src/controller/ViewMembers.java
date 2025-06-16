package controller;

import java.sql.SQLException;
import java.util.List;

import Pojo.Member;
import Service.LibraryService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewMembers {
    public VBox getView() throws Exception {
        VBox root = new VBox(10);
        Stage viewStage = new Stage();
        TableView<Member> table = new TableView<>();

		TableColumn<Member, Integer> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));

		TableColumn<Member, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Member, String> emailCol = new TableColumn<>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

		TableColumn<Member, Long> mobileCol = new TableColumn<>("Mobile");
		mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));

		TableColumn<Member, Character> genderCol = new TableColumn<>("Gender");
		genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

		TableColumn<Member, String> addressCol = new TableColumn<>("Address");
		addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

		table.getColumns().addAll(List.of(idCol, nameCol, emailCol, mobileCol, genderCol, addressCol));

		LibraryService lib = new LibraryService();
		List<Member> members = lib.viewAllMembers();
		table.getItems().addAll(members);

		root.getChildren().addAll(new Label("All Members"), table);
		root.setAlignment(Pos.CENTER);
		viewStage.setScene(new Scene(root, 700, 400));
		viewStage.show();
	    return root;
 
}}
