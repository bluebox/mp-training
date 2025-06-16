package com.LibraryManagement.ui;

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

import com.LibraryManagement.dao.MemberDAO;
import com.LibraryManagement.dao.MemberDAOImpl;
import com.LibraryManagement.model.Member;
import com.LibraryManagement.service.MemberService;
import com.LibraryManagement.service.MemberServiceImpl;
import com.LibraryManagement.util.DBConnection;

public class ViewMembersUI {
    private final MemberService memberService;

    public ViewMembersUI() {
        this.memberService=null;
        try {
            Connection conn = DBConnection.getConnection();
            MemberDAO memberDAO = new MemberDAOImpl(conn);
            MemberService memberService = new MemberServiceImpl(memberDAO);
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
