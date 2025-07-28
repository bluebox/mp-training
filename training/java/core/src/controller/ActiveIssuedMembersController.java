package controller;

import Domain.Member;
import Service.ServiceInterface;
import Service.ServiceLayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ActiveIssuedMembersController {

    private ServiceInterface service = new ServiceLayer();

    @FXML private TableView<Member> membersTable;
    @FXML private TableColumn<Member, Integer> idColumn;
    @FXML private TableColumn<Member, String> nameColumn;
    @FXML private TableColumn<Member, String> emailColumn;
    @FXML private TableColumn<Member, String> mobileColumn;
    @FXML private TableColumn<Member, String> addressColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        emailColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        mobileColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getMobile())));
        addressColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));

        loadMembers();
    }

    private void loadMembers() {
        try {
            membersTable.getItems().clear();
            membersTable.getItems().addAll(service.getAllMembers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}