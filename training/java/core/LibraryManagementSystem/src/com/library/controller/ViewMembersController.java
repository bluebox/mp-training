package com.library.controller;

import com.Models.Member;
import com.Service.MemberService;
//import com.library.util.AlertMsg;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class ViewMembersController {

  @FXML private TableView<Member> memberTable;
  @FXML private TableColumn<Member, Integer> colId;
  @FXML private TableColumn<Member, String> colName;
  @FXML private TableColumn<Member, String> colEmail;
  @FXML private TableColumn<Member, String> colMobile;
  @FXML private TableColumn<Member, String> colGender;
  @FXML private TableColumn<Member, String> colAddress;

  private final MemberService memberService = new MemberService();

  @FXML
  public void initialize() {
      try {
          List<Member> members = memberService.getAllMembers();
          ObservableList<Member> memberList = FXCollections.observableArrayList(members);

          colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberId()).asObject());
          colName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
          colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
          colMobile.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty(data.getValue().getMobile()));
          colGender.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getGender())));
          colAddress.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));

          memberTable.setItems(memberList);

      } catch (Exception e) {
//          AlertMsg.showError(e.getMessage());
      }
  }
}