package com.library.controller;

import com.library.model.Member;
import com.library.service.impl.MemberServiceImplementation;
import com.library.service.interfaces.MemberService;
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

public class ViewMembersController {

    @FXML private TableView<Member> memberTable;
    @FXML private TableColumn<Member, Integer> idCol;
    @FXML private TableColumn<Member, String> nameCol;
    @FXML private TableColumn<Member, String> emailCol;
    @FXML private TableColumn<Member, Long> mobileCol;
    @FXML private TableColumn<Member, String> genderCol;
    @FXML private TableColumn<Member, String> addressCol;
    @FXML private TableColumn<Member, Void> actionCol;

    private final MemberService memberService = new MemberServiceImplementation();

    @FXML
    public void initialize() throws Exception {
        idCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Custom cell factory to convert 'M'/'F' to "Male"/"Female"
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        genderCol.setCellFactory(column -> new TableCell<Member, String>() {
            @Override
            protected void updateItem(String gender, boolean empty) {
                super.updateItem(gender, empty);
                if (empty || gender == null) {
                    setText(null);
                } else {
                    setText(gender.equalsIgnoreCase("M") ? "Male" : "Female");
                }
            }
        });

        loadMemberData();
    }

    @FXML
    private void handleBack() throws IOException {
        MainController.switchScene("MainDashboard.fxml");
    }

    private void loadMemberData() throws Exception {
        List<Member> members = memberService.fetchAllMembers();
        ObservableList<Member> memberList = FXCollections.observableArrayList(members);
        memberTable.setItems(memberList);

        actionCol.setCellFactory(col -> new TableCell<Member, Void>() {
            private final Button editBtn = new Button("Edit");

            {
            	editBtn.setOnAction(event -> {
            	    Member selected = getTableView().getItems().get(getIndex());
            	    EditMemberController.setSelectedMember(selected);

            	    try {
            	        MainController.switchScene("EditMember.fxml");
            	    } catch (Exception e) {
            	        e.printStackTrace();
            	    }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditMember.fxml"));
            Parent root = loader.load();

            EditMemberController controller = loader.getController();
//            controller.setMember(member);
            EditMemberController.setSelectedMember(member);

            Stage stage = new Stage();
            stage.setTitle("Edit Member");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Refresh table after update
            loadMemberData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
