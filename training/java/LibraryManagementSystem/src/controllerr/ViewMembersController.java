package controllerr;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Member;
import service.MemberService;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewMembersController {
    @FXML private TableView<Member> membersTable;
    @FXML private TableColumn<Member, Integer> memberIdCol;
    @FXML private TableColumn<Member, String> nameCol;
    @FXML private TableColumn<Member, String> emailCol;
    @FXML private TableColumn<Member, Long> mobileCol;
    @FXML private TableColumn<Member, Character> genderCol;
    @FXML private TableColumn<Member, String> addressCol;

    private final MemberService memberService = new MemberService();

    @FXML
    public void initialize() {
        memberIdCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        loadMembers();
    }

    private void loadMembers() {
        try {
            List<Member> members = memberService.getAllMembers();
            ObservableList<Member> observableMembers = FXCollections.observableArrayList(members);
            membersTable.setItems(observableMembers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 