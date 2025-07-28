package controller;

import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import util.DBUtil;

public class MainController {
    @FXML
    private void handleBookManagement(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/BookManagement.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    @FXML
    private void handleMemberManagement(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/MemberManagement.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));}
    @FXML
    private void handleIssueBook(ActionEvent event) throws Exception {
    	Connection conn=DBUtil.getConnection();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/IssueReturn.fxml"));
        Parent root = loader.load();

        // Inject the DB connection (replace with your actual connection variable)
        IssueController controller = loader.getController();
        controller.setConnection(conn); // <-- make sure you have this

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void handleReportManagement(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("/resources/RecordOperations.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    	
    }

    
}
