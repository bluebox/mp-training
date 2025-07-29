package com.LibraryManagement.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.LibraryManagement.models.Book;
import com.LibraryManagement.models.IssueRecords;
import com.LibraryManagement.models.Member;
import com.LibraryManagement.service.implementation.BookServiceImplementation;
import com.LibraryManagement.service.implementation.IssueRecordServiceImplementation;
import com.LibraryManagement.service.implementation.MemberServiceImplementation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

public class ReportsController {

    @FXML private StackPane tableContainer;

    @FXML private TableView<OverdueBook> overdueBooksTable;
    @FXML private TableColumn<OverdueBook, String> overdueTitleCol;
    @FXML private TableColumn<OverdueBook, String> overdueMemberCol;
    @FXML private TableColumn<OverdueBook, LocalDate> overdueDateCol;

    @FXML private TableView<CategoryCount> categoryCountTable;
    @FXML private TableColumn<CategoryCount, String> categoryNameCol;
    @FXML private TableColumn<CategoryCount, Long> categoryCountCol;

    @FXML private TableView<ActiveMember> activeMembersTable;
    @FXML private TableColumn<ActiveMember, String> memberNameCol;
    @FXML private TableColumn<ActiveMember, Integer> booksIssuedCol;

    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();
    private final BookServiceImplementation bookService = new BookServiceImplementation();
    private final MemberServiceImplementation memberService = new MemberServiceImplementation();

    @FXML
    public void initialize() {
        overdueTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        overdueMemberCol.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        overdueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

        categoryNameCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCountCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        memberNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        booksIssuedCol.setCellValueFactory(new PropertyValueFactory<>("booksIssued"));

        hideAllTables();
    }

    private void hideAllTables() {
        overdueBooksTable.setVisible(false);
        categoryCountTable.setVisible(false);
        activeMembersTable.setVisible(false);
    }

    @FXML
    public void Back(ActionEvent event) {
        try {
            MainController.switchScene("MainPageForm.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OverDue(ActionEvent event) throws Exception {
        hideAllTables();

        List<IssueRecords> allIssues = issueService.getAllIssues();
        List<IssueRecords> overdue = allIssues.stream()
                .filter(issue -> issue.getReturnDate() == null)
                .filter(issue -> issue.getIssueDate().isBefore(LocalDate.now().minusDays(1)))
                .collect(Collectors.toList());

        ObservableList<OverdueBook> data = FXCollections.observableArrayList();
        for (IssueRecords issue : overdue) {
            Book book = bookService.getBookById(issue.getBookId());
            Member member = memberService.fetchMemberById(issue.getMemberId());
            data.add(new OverdueBook(book.getTitle(), member.getName(), issue.getIssueDate()));
        }

        overdueBooksTable.setItems(data);
        overdueBooksTable.setVisible(true);
    }

    @FXML
    public void BooksPerCategory(ActionEvent event) {
        hideAllTables();

        List<Book> allBooks = bookService.getAllBooks();
        Map<String, Long> countByCategory = allBooks.stream()
                .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));

        ObservableList<CategoryCount> data = FXCollections.observableArrayList(
            countByCategory.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(entry -> new CategoryCount(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList())
        );

        categoryCountTable.setItems(data);
        categoryCountTable.setVisible(true);
    }

    @FXML
    public void ActiveIssuedMembers(ActionEvent event) throws Exception {
        hideAllTables();

        List<IssueRecords> allIssues = issueService.getAllIssues();
        Map<Integer, Long> memberIssueCount = allIssues.stream()
                .filter(issue -> issue.getReturnDate() == null)
                .collect(Collectors.groupingBy(IssueRecords::getMemberId, Collectors.counting()));

        ObservableList<ActiveMember> data = FXCollections.observableArrayList();
        for (Map.Entry<Integer, Long> entry : memberIssueCount.entrySet()) {
            Member member = memberService.fetchMemberById(entry.getKey());
            data.add(new ActiveMember(member.getName(), entry.getValue().intValue()));
        }

        activeMembersTable.setItems(data);
        activeMembersTable.setVisible(true);
    }


    public static class OverdueBook {
        private final String title;
        private final String memberName;
        private final LocalDate issueDate;

        public OverdueBook(String title, String memberName, LocalDate issueDate) {
            this.title = title;
            this.memberName = memberName;
            this.issueDate = issueDate;
        }

        public String getTitle() { return title; }
        public String getMemberName() { return memberName; }
        public LocalDate getIssueDate() { return issueDate; }
    }

    public static class CategoryCount {
        private final String category;
        private final Long count;

        public CategoryCount(String category, Long count) {
            this.category = category;
            this.count = count;
        }

        public String getCategory() { return category; }
        public Long getCount() { return count; }
    }

    public static class ActiveMember {
        private final String name;
        private final int booksIssued;

        public ActiveMember(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;
        }

        public String getName() { return name; }
        public int getBooksIssued() { return booksIssued; }
    }
}