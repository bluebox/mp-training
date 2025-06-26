package com.casestudy.fx;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.casestudy.domain.IssueRecord;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class ViewAllRecords extends VBox {

    public ViewAllRecords(List<IssueRecord> records) {
        TableView<IssueRecord> table = new TableView<>();
        table.setItems(FXCollections.observableArrayList(records));

        TableColumn<IssueRecord, Integer> idCol = new TableColumn<>("Issue ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIssueId()).asObject());

        TableColumn<IssueRecord, Integer> bookIdCol = new TableColumn<>("Book ID");
        bookIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookId()).asObject());

        TableColumn<IssueRecord, Integer> memberIdCol = new TableColumn<>("Member ID");
        memberIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberId()).asObject());

        TableColumn<IssueRecord, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus().toString()));

        TableColumn<IssueRecord, String> issueDateCol = new TableColumn<>("Issue Date");
        issueDateCol.setCellValueFactory(data -> {
            String formatted = data.getValue().getIssueDate() != null
                ? data.getValue().getIssueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                : "";
            return new javafx.beans.property.SimpleStringProperty(formatted);
        });

        TableColumn<IssueRecord, String> returnDateCol = new TableColumn<>("Return Date");
        returnDateCol.setCellValueFactory(data -> {
            String formatted = data.getValue().getReturnDate() != null
                ? data.getValue().getReturnDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                : "";
            return new javafx.beans.property.SimpleStringProperty(formatted);
        });

        table.getColumns().addAll(idCol, bookIdCol, memberIdCol, statusCol, issueDateCol, returnDateCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        this.getChildren().addAll(new Label("📦 All Issue Records"), table);
    }
}
