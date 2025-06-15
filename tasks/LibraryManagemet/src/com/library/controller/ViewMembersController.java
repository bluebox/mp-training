package com.library.controller;

import java.util.List;

import com.library.domain.Member;
import com.library.service.MemberService;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ViewMembersController {

	@FXML
	private TableView<Member> memberTable;

	@FXML
	private TableColumn<Member, Integer> idCol;

	@FXML
	private TableColumn<Member, String> nameCol;

	@FXML
	private TableColumn<Member, String> emailCol;

	@FXML
	private TableColumn<Member, Long> mobileCol;

	@FXML
	private TableColumn<Member, Character> genderCol;

	@FXML
	private TableColumn<Member, String> addressCol;

	private MemberService memberService = new MemberService();

	@FXML
	public void initialize() {
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
		genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
		addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

		List<Member> members = memberService.viewAllMembers(); 
		memberTable.setItems(FXCollections.observableArrayList(members));
	}

	@FXML
	private void goBack(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/library/UI/Home.fxml")); 
																								
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
