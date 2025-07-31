package com.lms.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReportsController {

    @FXML
    private AnchorPane reportTables;

    @FXML
    private void handleShowOverdue() {
        TableView<OverdueRecord> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<OverdueRecord, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(100);

        TableColumn<OverdueRecord, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setPrefWidth(300);

        TableColumn<OverdueRecord, String> issueDateCol = new TableColumn<>("Issue Date");
        issueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        issueDateCol.setPrefWidth(200);

        table.getColumns().addAll(idCol, titleCol, issueDateCol);
        showTable(table);
    }

    @FXML
    private void handleShowBooksPerCategory() {
        TableView<BookCategorySummary> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<BookCategorySummary, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setPrefWidth(400);

        TableColumn<BookCategorySummary, Integer> countCol = new TableColumn<>("Count");
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        countCol.setPrefWidth(200);

        table.getColumns().addAll(categoryCol, countCol);
        showTable(table);
    }

    @FXML
    private void handleShowIssuedMembers() {
        TableView<IssuedMember> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<IssuedMember, String> memberName = new TableColumn<>("Name");
        memberName.setCellValueFactory(new PropertyValueFactory<>("name"));
        memberName.setPrefWidth(350);

        TableColumn<IssuedMember, Integer> books = new TableColumn<>("Books Issued");
        books.setCellValueFactory(new PropertyValueFactory<>("booksIssued"));
        books.setPrefWidth(300);

        table.getColumns().addAll(memberName, books);
        showTable(table);
    }

    private void showTable(Node table) {
        reportTables.getChildren().setAll(table);
        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
    }

    @FXML
    private void handlePrint() {
        System.out.println("Export functionality to be implemented...");
    }
    public static class OverdueRecord {
        private String id, title, issueDate;
        public OverdueRecord(String id, String title, String issueDate) {
            this.id = id; this.title = title; this.issueDate = issueDate;
        }
        public String getId() { return id; }
        public String getTitle() { return title; }
        public String getIssueDate() { return issueDate; }
    }

    public static class BookCategorySummary {
        private String category;
        private int count;
        public BookCategorySummary(String category, int count) {
            this.category = category; this.count = count;
        }
        public String getCategory() { return category; }
        public int getCount() { return count; }
    }

    public static class IssuedMember {
        private String name;
        private int booksIssued;
        public IssuedMember(String name, int booksIssued) {
            this.name = name; this.booksIssued = booksIssued;
        }
        public String getName() { return name; }
        public int getBooksIssued() { return booksIssued; }
    }
}
