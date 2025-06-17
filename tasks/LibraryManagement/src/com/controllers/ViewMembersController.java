package com.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import com.models.Member;
import com.services.MemberService;

public class ViewMembersController {

    @FXML private TableView<Member> membersTable;
    @FXML private TableColumn<Member, Integer> idColumn;
    @FXML private TableColumn<Member, String> nameColumn;
    @FXML private TableColumn<Member, String> emailColumn;
    @FXML private TableColumn<Member, Long> mobileColumn;
    @FXML private TableColumn<Member, String> genderColumn;
    @FXML private TableColumn<Member, String> addressColumn;

    private ObservableList<Member> memberList = FXCollections.observableArrayList();
   


    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadMembersFromDatabase();
    }

    private void loadMembersFromDatabase() {
//        String url = "jdbc:mysql://localhost:3306/your_database";
//        String user = "pavan";
//        String password = "Pavan@02";

        try {
//        	Connection conn = DriverManager.getConnection(url, user, password);
//            String query = "SELECT * FROM members";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();

//            while (rs.next()) {
//                );
        	
//            }
        	Member m = new Member(1,"pavan Reddy","pashampavan02@gmail.com", 1251661022,'M',"Suryapet");
        	Member m2 = new Member(2,"Nandini Reddy","pashampavan02@gmail.com", 1851661022,'F',"Hyderabad");
        	
        	memberList.add(m);
        	memberList.add(m2);
        	//memberList= MemberService.fetchAll();
        	 membersTable.setItems(memberList);

           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
