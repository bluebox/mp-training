package com.lms.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.lms.model.Member;
import com.lms.serviceImpl.MemberService;
import com.lms.exceptions.DAOException;

import java.util.List;

public class ViewMemberController {

    @FXML private TableView<Member> memberTable;
    @FXML private TableColumn<Member, Integer> memberIdColumn;
    @FXML private TableColumn<Member, String> nameColumn;
    @FXML private TableColumn<Member, String> mailColumn;
    @FXML private TableColumn<Member, String> mobileNumberColumn;
    @FXML private TableColumn<Member, String> genderColumn;
    @FXML private TableColumn<Member, String> addressColumn;

    private final MemberService memberService = new MemberService();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadMembers();
    }

    private void setupTableColumns() {
        memberIdColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getMemberId()).asObject());
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        mailColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));
        mobileNumberColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMobile()));
        genderColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getGender()));
        addressColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getAddress()));
    }

    private void loadMembers() {
        try {
            List<Member> members = memberService.getAllMembers();
            memberTable.setItems(FXCollections.observableArrayList(members));
        } catch (DAOException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load members: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}