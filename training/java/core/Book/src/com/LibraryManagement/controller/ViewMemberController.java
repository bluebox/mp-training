package com.LibraryManagement.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;

import com.LibraryManagement.models.Member;
import com.LibraryManagement.service.implementation.MemberServiceImplementation;

public class ViewMemberController {

    @FXML private TableView<Member> membersTable;
    @FXML private TableColumn<Member, Integer> memberIdColumn;
    @FXML private TableColumn<Member, String> nameColumn;
    @FXML private TableColumn<Member, String> emailCol;
    @FXML private TableColumn<Member, Long> mobileCol;
    @FXML private TableColumn<Member, String> genderCol;
    @FXML private TableColumn<Member, String> addressCol;
    @FXML private TableColumn<Member, Void> actionCol;

    private final MemberServiceImplementation memberService = new MemberServiceImplementation();

    @FXML
    public void initialize() throws Exception {
    	memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadMemberData();
    }
    @FXML
    public void Back(ActionEvent event) {
        try {
            MainController.switchScene("members/MembersForm.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   


    private void loadMemberData() throws Exception {
        List<Member> members = memberService.getAllMembers();
        ObservableList<Member> memberList = FXCollections.observableArrayList(members);
        membersTable.setItems(memberList);

        actionCol.setCellFactory(col -> new TableCell<Member,Void>() {
            private final Button editBtn = new Button("Update");

            {
                editBtn.setOnAction((ActionEvent event) -> {
                    Member member = getTableView().getItems().get(getIndex());
                    openEditMemberPopup(member);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(editBtn));
                }
            }
        });
    }

    private void openEditMemberPopup(Member member) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/LibraryManagement/application/members/UpdateMemberForm.fxml"));
            Parent root = loader.load();

            UpdateMemberController controller = loader.getController();
            controller.setMember(member);

            Stage stage = new Stage();
            stage.setTitle("Edit Member");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadMemberData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
