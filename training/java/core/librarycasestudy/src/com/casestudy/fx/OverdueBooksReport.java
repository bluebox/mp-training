package com.casestudy.fx;
import java.util.List;

import com.casestudy.domain.IssueRecord;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class OverdueBooksReport extends VBox {

    public OverdueBooksReport(List<IssueRecord> issueRecords) {
        this.setSpacing(10);
        this.getChildren().add(new Label("Book IDs from Issue Records"));

        TableView<IssueRecord> table = new TableView<>();
        table.setItems(FXCollections.observableArrayList(issueRecords));

        TableColumn<IssueRecord, String> bookIdCol = new TableColumn<>("Book ID");
        bookIdCol.setCellValueFactory(data -> 
            new SimpleStringProperty(String.valueOf(data.getValue().getBookId()))
        );

        table.getColumns().add(bookIdCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        this.getChildren().add(table);
    }
}
