package Library.src.main.java.com.LibraryManagement.ui;

import Library.src.main.java.com.LibraryManagement.model.Member;
import Library.src.main.java.com.LibraryManagement.service.MemberService;
import Library.src.main.java.com.LibraryManagement.service.MemberServiceImpl;
import Library.src.main.java.com.LibraryManagement.dao.MemberDAOImpl;
import Library.src.main.java.com.LibraryManagement.util.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.List;

public class ViewMembersUI {
    private final MemberService memberService;

    public ViewMembersUI() {
        try {
            Connection conn = DBConnection.getConnection();
            this.memberService = new MemberServiceImpl(new MemberDAOImpl(conn));
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize service: " + e.getMessage());
        }
    }

    public void start(Stage stage) {
        TableView<Member> table = new TableView<>();

        TableColumn<Member, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));

        TableColumn<Member, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Member, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Member, Integer> mobileCol = new TableColumn<>("Mobile");
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));

        TableColumn<Member, Character> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Member, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        table.getColumns().addAll(idCol, nameCol, emailCol, mobileCol, genderCol, addressCol);

        try {
            List<Member> members = memberService.getAllMembers();
            ObservableList<Member> data = FXCollections.observableArrayList(members);
            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        VBox root = new VBox(10, table);
        root.setPadding(new Insets(10));

        stage.setScene(new Scene(root, 800, 400));
        stage.setTitle("View All Members");
        stage.show();
    }
}
