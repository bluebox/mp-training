package com.casestudyfx;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.beans.property.SimpleStringProperty;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.casestudy.IssueRecord;

public class ActiveIssuedMembersReport extends VBox {

    public ActiveIssuedMembersReport(List<IssueRecord> issueRecords) {
        this.setSpacing(10);
        this.getChildren().add(new Text("📋 All Issue Records"));

        TableView<IssueRecord> table = new TableView<>();
        table.setItems(FXCollections.observableArrayList(issueRecords));

        TableColumn<IssueRecord, String> issueIdCol = new TableColumn<>("Issue ID");
        issueIdCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getIssueId())));

        TableColumn<IssueRecord, String> memberIdCol = new TableColumn<>("Member ID");
        memberIdCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getMemberId())));

        TableColumn<IssueRecord, String> bookIdCol = new TableColumn<>("Book ID");
        bookIdCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getBookId())));

        TableColumn<IssueRecord, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus().toString()));

        TableColumn<IssueRecord, String> issueDateCol = new TableColumn<>("Issue Date");
        issueDateCol.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getIssueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        ));

        TableColumn<IssueRecord, String> returnDateCol = new TableColumn<>("Return Date");
        returnDateCol.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getReturnDate() != null
                        ? data.getValue().getReturnDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        : "N/A"
        ));

        table.getColumns().addAll(issueIdCol, memberIdCol, bookIdCol, statusCol, issueDateCol, returnDateCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        this.getChildren().add(table);
    }
}
