package com.library.controller;

import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.Member;
import com.library.service.impl.BookServiceImplementation;
import com.library.service.impl.IssueRecordServiceImplementation;
import com.library.service.impl.MemberServiceImplementation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReportsController {

    // Overdue Books
    @FXML private TableView<IssueRecordRow> overdueTable;
    @FXML private TableColumn<IssueRecordRow, Integer> colOverdueBookId;
    @FXML private TableColumn<IssueRecordRow, String> colOverdueTitle;
    @FXML private TableColumn<IssueRecordRow, Integer> colOverdueMemberId;
    @FXML private TableColumn<IssueRecordRow, LocalDate> colOverdueIssueDate;

    // Book Category Count
    @FXML private TableView<CategoryCountRow> categoryTable;
    @FXML private TableColumn<CategoryCountRow, String> colCategory;
    @FXML private TableColumn<CategoryCountRow, Long> colCategoryCount;

    // Active Members
    @FXML private TableView<MemberRow> activeMembersTable;
    @FXML private TableColumn<MemberRow, Integer> colActiveMemberId;
    @FXML private TableColumn<MemberRow, String> colActiveMemberName;
    @FXML private TableColumn<MemberRow, String> colActiveMemberEmail;

    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();
    private final BookServiceImplementation bookService = new BookServiceImplementation();
    private final MemberServiceImplementation memberService = new MemberServiceImplementation();

    @FXML
    public void initialize() {
        try {
            List<IssueRecord> allIssues = issueService.getAllIssues();
            List<Book> allBooks = bookService.getAllBooks();

            // 1. Overdue Books
            colOverdueBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            colOverdueTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            colOverdueMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
            colOverdueIssueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

            List<IssueRecordRow> overdueList = allIssues.stream()
                    .filter(issue -> issue.getReturnDate() == null)
                    .filter(issue -> issue.getIssueDate().isBefore(LocalDate.now().minusDays(-1)))
                    .map(issue -> {
                        try {
                            Book book = bookService.getBookById(issue.getBookId());
                            return new IssueRecordRow(issue.getBookId(), book.getTitle(), issue.getMemberId(), issue.getIssueDate());
                        } catch (Exception e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            overdueTable.setItems(FXCollections.observableArrayList(overdueList));

            // 2. Book Count by Category
            colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
            colCategoryCount.setCellValueFactory(new PropertyValueFactory<>("count"));

            Map<String, Long> countByCategory = allBooks.stream()
                    .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));

            List<CategoryCountRow> categoryRows = countByCategory.entrySet().stream()
                    .map(entry -> new CategoryCountRow(entry.getKey(), entry.getValue()))
                    .sorted((a, b) -> Long.compare(b.getCount(), a.getCount())) // Sorting in descending order
                    .collect(Collectors.toList());

            categoryTable.setItems(FXCollections.observableArrayList(categoryRows));


            // 3. Active Members with Issued Books
            colActiveMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
            colActiveMemberName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colActiveMemberEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            Set<Integer> activeMemberIds = allIssues.stream()
                    .filter(issue -> issue.getReturnDate() == null)
                    .map(IssueRecord::getMemberId)
                    .collect(Collectors.toSet());

            List<MemberRow> memberRows = new ArrayList<>();
            for (int id : activeMemberIds) {
                Member m = memberService.fetchMemberById(id);
                memberRows.add(new MemberRow(m.getMemberId(), m.getName(), m.getEmail()));
            }

            activeMembersTable.setItems(FXCollections.observableArrayList(memberRows));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            com.library.controller.MainController.switchScene("MainDashboard.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Utility row classes for TableView

    public static class IssueRecordRow {
        private final int bookId;
        private final String title;
        private final int memberId;
        private final LocalDate issueDate;

        public IssueRecordRow(int bookId, String title, int memberId, LocalDate issueDate) {
            this.bookId = bookId;
            this.title = title;
            this.memberId = memberId;
            this.issueDate = issueDate;
        }

        public int getBookId() { return bookId; }
        public String getTitle() { return title; }
        public int getMemberId() { return memberId; }
        public LocalDate getIssueDate() { return issueDate; }
    }

    public static class CategoryCountRow {
        private final String category;
        private final long count;

        public CategoryCountRow(String category, long count) {
            this.category = category;
            this.count = count;
        }

        public String getCategory() { return category; }
        public long getCount() { return count; }
    }

    public static class MemberRow {
        private final int memberId;
        private final String name;
        private final String email;

        public MemberRow(int memberId, String name, String email) {
            this.memberId = memberId;
            this.name = name;
            this.email = email;
        }

        public int getMemberId() { return memberId; }
        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}
