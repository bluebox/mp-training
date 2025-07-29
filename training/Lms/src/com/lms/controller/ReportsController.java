package com.lms.controller;

import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.service.ReportServiceInterface;
import com.lms.serviceImpl.ReportServiceImpl;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Map;

public class ReportsController {

    @FXML
    private ComboBox<String> reportTypeComboBox;

    @FXML
    private TableView reportTable;

    private final ReportServiceInterface reportService = new ReportServiceImpl();

    @FXML
    public void initialize() {
        reportTypeComboBox.getItems().addAll("Overdue Books", "Books by Category", "Active Members");
        reportTypeComboBox.getSelectionModel().selectFirst();

        reportTypeComboBox.setOnAction(e -> updateReport());

        reportTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // Optional for auto-fit
        updateReport();
    }

    private void updateReport() {
        String selected = reportTypeComboBox.getValue();
        reportTable.getItems().clear();
        reportTable.getColumns().clear();

        switch (selected) {
            case "Overdue Books":
                loadOverdueBooks();
                break;
            case "Books by Category":
                loadBookCountByCategory();
                break;
            case "Active Members":
                loadMembersWithActiveIssues();
                break;
        }
    }

    private void loadOverdueBooks() {
        TableColumn<Book, String> id = new TableColumn<>("Book ID");
        id.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> title = new TableColumn<>("Title");
        title.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));

        TableColumn<Book, String> author = new TableColumn<>("Author");
        author.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));

        TableColumn<Book, String> category = new TableColumn<>("Category");
        category.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));

        reportTable.getColumns().addAll(id, title, author, category);

        List<Book> books = reportService.fetchOverdueBooks();
        reportTable.getItems().setAll(books);
    }

    private void loadBookCountByCategory() {
        TableColumn<Map.Entry<String, Long>, String> category = new TableColumn<>("Category");
        category.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKey()));

        TableColumn<Map.Entry<String, Long>, Long> count = new TableColumn<>("Count");
        count.setCellValueFactory(data -> new SimpleLongProperty(data.getValue().getValue()).asObject());

        reportTable.getColumns().addAll(category, count);

        Map<String, Long> data = reportService.fetchBookCountByCategory();
        reportTable.getItems().setAll(data.entrySet());
    }

    private void loadMembersWithActiveIssues() {
        TableColumn<Member, Integer> id = new TableColumn<>("Member ID");
        id.setCellValueFactory(new PropertyValueFactory<>("memberId"));

        TableColumn<Member, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Member, String> email = new TableColumn<>("Email");
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Member, String> mobile = new TableColumn<>("Mobile");
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));

        TableColumn<Member, String> gender = new TableColumn<>("Gender");
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Member, String> address = new TableColumn<>("Address");
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        reportTable.getColumns().addAll(id, name, email, mobile, gender, address);

        List<Member> members = reportService.fetchMembersWithActiveIssues();
        reportTable.getItems().setAll(members);
    }
}
