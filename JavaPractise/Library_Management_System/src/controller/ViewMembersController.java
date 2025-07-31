package controller;

import domain.Gender;
import domain.Member;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.MemberManagementService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewMembersController {

    @FXML private TableView<Member> memberTable;
    @FXML private TableColumn<Member, Integer> idColumn;
    @FXML private TableColumn<Member, String> nameColumn;
    @FXML private TableColumn<Member, String> emailColumn;
    @FXML private TableColumn<Member, String> mobileColumn;
    @FXML private TableColumn<Member, String> genderColumn;
    @FXML private TableColumn<Member, String> addressColumn;
    @FXML private TableColumn<Member, Void> updateColumn;
    @FXML private TableColumn<Member, String> colDateAdded;
    @FXML private TableColumn<Member, String> addedByColumn;

    private final MemberManagementService service = new MemberManagementService();

    @FXML
    public void initialize() {
        setupColumns();
        loadMembers();
        addUpdateButton();
    }

    private void setupColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        genderColumn.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getGender().getCode()));

        genderColumn.setCellFactory(column -> new TableCell<Member, String>() {
            @Override
            protected void updateItem(String genderCode, boolean empty) {
                super.updateItem(genderCode, empty);
                if (empty || genderCode == null) {
                    setText(null);
                } else {
                    try {
                        Gender gender = Gender.fromCode(genderCode);
                        setText(gender.name()); 
                        } catch (Exception e) {
                        setText("Unknown");
                    }
                }
            }
        });

        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        addedByColumn.setCellValueFactory(new PropertyValueFactory<>("addedBy"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        colDateAdded.setCellValueFactory(cellData -> {
            LocalDateTime dateAdded = cellData.getValue().getDateAdded();
            String formatted = dateAdded != null ? dateAdded.format(formatter) : "";
            return new SimpleStringProperty(formatted);
        });
    }

    private void loadMembers() {
        try {
            List<Member> members = service.getAllMembers();
            ObservableList<Member> memberList = FXCollections.observableArrayList(members);
            memberTable.setItems(memberList);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Load Error", "Unable to load members: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void addUpdateButton() {
        Callback<TableColumn<Member, Void>, TableCell<Member, Void>> cellFactory =
            new Callback<TableColumn<Member, Void>, TableCell<Member, Void>>() {
                @Override
                public TableCell<Member, Void> call(final TableColumn<Member, Void> param) {
                    return new TableCell<Member, Void>() {
                        private final Button btn = new Button("Update");

                        {
                            btn.setOnAction(event -> {
                                Member selectedMember = getTableView().getItems().get(getIndex());
                                openUpdateForm(selectedMember);
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            setGraphic(empty ? null : btn);
                        }
                    };
                }
            };
        updateColumn.setCellFactory(cellFactory);
    }

    private void openUpdateForm(Member member) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/UpdateMember.fxml"));
            Parent root = loader.load();
            UpdateMemberController controller = loader.getController();
            controller.setMemberData(member);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setOnHidden(e -> refreshTable());
            stage.setTitle("Update Member - ID: " + member.getMemberId());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to open update form.");
        }
    }

    private void refreshTable() {
        try {
            List<Member> updatedMembers = service.getAllMembers();
            memberTable.setItems(FXCollections.observableArrayList(updatedMembers));
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Refresh Error", "Unable to refresh members: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resources/MemberManagement.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not navigate back.");
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