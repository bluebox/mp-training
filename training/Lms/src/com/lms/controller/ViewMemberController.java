package com.lms.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.lms.model.*;
import com.lms.serviceImpl.*;

import java.util.List;

public class ViewMemberController {

    @FXML private TableView<Member> memberTable;
    @FXML private TableColumn<Member, Integer> memberIdColumn;
    @FXML private TableColumn<Member, String> nameColumn;
    @FXML private TableColumn<Member, String> mailColumn;
    @FXML private TableColumn<Member, String> mobileNumberColumn;
    @FXML private TableColumn<Member, String> genderColumn;
    @FXML private TableColumn<Member, String> addressColumn;

    @FXML
    public void initialize() {
        memberIdColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getMemberId()).asObject());
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        mailColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));
        mobileNumberColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMobile()));
        genderColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getGender()));
        addressColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getAddress()));

        loadMembers();
    }
    MemberService memberService = new MemberService();
    private void loadMembers() {
        List<Member> members = memberService.getAllMembers(); 
        memberTable.setItems(FXCollections.observableArrayList(members));
    }
}
