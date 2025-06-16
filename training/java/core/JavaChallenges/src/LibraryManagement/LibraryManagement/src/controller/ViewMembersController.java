package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Member;
import service.MemberService;

public class ViewMembersController {

    @FXML private TableView<Member> memberTable;
    @FXML private TableColumn<Member, String> nameCol;
    @FXML private TableColumn<Member, String> emailCol;
    @FXML private TableColumn<Member, Long> mobileCol;

    private final MemberService memberService = new MemberService();

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        mobileCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getMobile()).asObject());

        loadMembers();
    }

    private void loadMembers() {
        try {
            ObservableList<Member> members = FXCollections.observableArrayList(memberService.getAllMembers());
            memberTable.setItems(members);
        } catch (Exception e) {
            showAlert("Error loading members: " + e.getMessage());
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("View Members");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
