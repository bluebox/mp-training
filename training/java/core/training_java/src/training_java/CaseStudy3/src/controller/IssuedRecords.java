package controller;

import java.util.List;

import Pojo.IssueRecord;
import Service.LibraryService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IssuedRecords  {
    public VBox getView() throws Exception {
        VBox root = new VBox(10);
        Stage viewStage = new Stage();
       

        TableView<IssueRecord> table = new TableView<>();

        TableColumn<IssueRecord, Integer> issueIdCol = new TableColumn<>("Issue ID");
        issueIdCol.setCellValueFactory(new PropertyValueFactory<>("issueId"));

        TableColumn<IssueRecord, Integer> bookIdCol = new TableColumn<>("Book ID");
        bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<IssueRecord, Integer> memberIdCol = new TableColumn<>("Member ID");
        memberIdCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));

        TableColumn<IssueRecord, Character> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<IssueRecord, String> issueDateCol = new TableColumn<>("Issue Date");
        issueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

        TableColumn<IssueRecord, String> returnDateCol = new TableColumn<>("Return Date");
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        table.getColumns().addAll(List.of(issueIdCol, bookIdCol, memberIdCol, statusCol, issueDateCol, returnDateCol));

        LibraryService lib = new LibraryService();
        List<IssueRecord> records = lib.viewIssuedRecords();
        table.getItems().addAll(records);

        root.getChildren().addAll(new Label("Issued Records"), table);
        root.setAlignment(Pos.CENTER);
        viewStage.setScene(new Scene(root, 800, 400));
        viewStage.show();
        return root;
    }
}
