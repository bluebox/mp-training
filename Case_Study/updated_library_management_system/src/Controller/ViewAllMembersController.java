package Controller;

import domain.Member;
import domain.checking_enum.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import Service.MemberService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewAllMembersController implements Initializable {

    @FXML private TableView<Member> memberTable;
    @FXML private TableColumn<Member, Integer> idCol;
    @FXML private TableColumn<Member, String> nameCol;
    @FXML private TableColumn<Member, String> emailCol;
    @FXML private TableColumn<Member, Integer> mobileCol;
    @FXML private TableColumn<Member, String> addressCol;
    @FXML private TableColumn<Member, Gender> genderCol;

    @FXML private Button backButton;
    @FXML private Button addButton;
    @FXML private Button updateButton;

    private final MemberService memberService = new MemberService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("memberid"));

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        ObservableList<Member> members = FXCollections.observableArrayList(memberService.getAllMembers());
        memberTable.setItems(members);
    }

    @FXML
    public void handleBack() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/applicationView/HomeView.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Home");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddMember() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/applicationView/AddMember.fxml"));
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Member");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleUpdateMember() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/applicationView/UpdateMember.fxml"));
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Update Member");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
