package com.library_management.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.library_management.App;
import com.library_management.domain.Member;
import com.library_management.exceptions.DatabaseException;
import com.library_management.services.implementation.MemberServiceImplementation;
import com.library_management.utilities.MemberTableView;
import com.library_management.utilities.ResponseHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ViewAllMembersController {

    @FXML
    private TableView<MemberTableView> viewMembersTable;
    @FXML
    private TableColumn<MemberTableView, Integer> memberIdColumn;
    @FXML
    private TableColumn<MemberTableView, String> memberNameColumn;
    @FXML
    private TableColumn<MemberTableView, String> memberMailColumn;
    @FXML
    private TableColumn<MemberTableView, String> memberMobileNoColumn;
    @FXML
    private TableColumn<MemberTableView, String> memberGenderColumn;
    @FXML
    private TableColumn<MemberTableView, String> memberAddressColumn;
    @FXML
    private TableColumn<MemberTableView, Void> deleteColumn;
    @FXML
    private Text message;

    private ObservableList<MemberTableView> memberData = FXCollections.observableArrayList();

    @FXML
    private void backButton() throws IOException {
        App.setRoot("memberOptions");
    }

    @FXML
    private void homeButton() throws IOException {
        App.setRoot("home");
    }

    @FXML
    public void initialize() throws SQLException {

        memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        memberNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        memberMailColumn.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        memberMobileNoColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
        memberGenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        memberAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            List<Member> members = new MemberServiceImplementation().validateViewAllMembers();
            for (Member member : members) {
                memberData.add(new MemberTableView(
                        member.getMemberId(),
                        member.getMemberName(),
                        member.getMemberMail(),
                        member.getMobileNo(),
                        member.getGender(),
                        member.getMemberAddress()
                ));
            }
        } catch (DatabaseException e) {
            ResponseHandler.showResponse(message, "Cannot Fetch Members Data...", Color.RED);
        }

        viewMembersTable.setItems(memberData);
        ResponseHandler.showResponse(message, "Members Fetched Successfully...", null);

        addDeleteButtonToTable();
    }

    private void addDeleteButtonToTable() {
        deleteColumn.setCellFactory(new Callback<TableColumn<MemberTableView, Void>, TableCell<MemberTableView, Void>>() {
            @Override
            public TableCell<MemberTableView,Void> call(final TableColumn<MemberTableView, Void> param) {
                return new TableCell<MemberTableView, Void>() {

                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setStyle("-fx-background-color: #ef5350; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
                        deleteButton.setOnAction(event -> {
                            MemberTableView member = getTableView().getItems().get(getIndex());

                            try {
                                boolean deleted = new MemberServiceImplementation().deleteMemberById(member.getMemberId());
                                if (deleted) {
                                    getTableView().getItems().remove(member);
                                    ResponseHandler.showResponse(message, "Member Deleted Successfully...", Color.GREEN);
                                } else {
                                    ResponseHandler.showResponse(message, "Failed to Delete Member...", Color.RED);
                                }
                            } catch (DatabaseException e) {
                                ResponseHandler.showResponse(message, e.getMessage(), Color.RED);
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox buttons = new HBox(10, deleteButton);
                            buttons.setPadding(new Insets(5));
                            setGraphic(buttons);
                        }
                    }
                };
            }
        });
    }
}
