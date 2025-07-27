package com.library.controller;

import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.Member;
import com.library.service.impl.BookServiceImplementation;
import com.library.service.impl.IssueRecordServiceImplementation;
import com.library.service.impl.MemberServiceImplementation;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReportsController {

    @FXML private TextArea overdueBooksArea;
    @FXML private TextArea categoryCountArea;
    @FXML private TextArea activeMembersArea;

    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();
    private final BookServiceImplementation bookService = new BookServiceImplementation();
    private final MemberServiceImplementation memberService = new MemberServiceImplementation();

    @FXML
    public void initialize() {
        try {
            List<IssueRecord> allIssues = issueService.getAllIssues();

            // 1. Overdue Books
            List<IssueRecord> overdue = allIssues.stream()
                    .filter(issue -> issue.getReturnDate() == null)
                    .filter(issue -> issue.getIssueDate().isBefore(LocalDate.now().minusDays(14)))
                    .collect(Collectors.toList());

            StringBuilder overdueText = new StringBuilder("Overdue Books:\n");
            for (IssueRecord issue : overdue) {
                Book book = bookService.getBookById(issue.getBookId());
                overdueText.append("Book ID: ").append(book.getBookId())
                           .append(", Title: ").append(book.getTitle())
                           .append(", Issued on: ").append(issue.getIssueDate())
                           .append("\n");
            }
            overdueBooksArea.setText(overdueText.toString());

            // 2. Book Count by Category
            List<Book> allBooks = bookService.getAllBooks();
            Map<String, Long> countByCategory = allBooks.stream()
                    .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));

            StringBuilder categoryText = new StringBuilder("Book Count by Category:\n");
            for (Map.Entry<String, Long> entry : countByCategory.entrySet()) {
                categoryText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            categoryCountArea.setText(categoryText.toString());

            // 3. Members with Active Issued Books
            Set<Integer> memberIdsWithIssuedBooks = allIssues.stream()
                    .filter(issue -> issue.getReturnDate() == null)
                    .map(IssueRecord::getMemberId)
                    .collect(Collectors.toSet());

            StringBuilder activeMembersText = new StringBuilder("Members with Active Issued Books:\n");
            for (int memberId : memberIdsWithIssuedBooks) {
                Member member = memberService.fetchMemberById(memberId);
                activeMembersText.append("ID: ").append(member.getMemberId())
                        .append(", Name: ").append(member.getName())
                        .append(", Email: ").append(member.getEmail())
                        .append("\n");
            }
            activeMembersArea.setText(activeMembersText.toString());

        } catch (Exception e) {
            overdueBooksArea.setText("Error loading report: " + e.getMessage());
            categoryCountArea.setText("Error loading report: " + e.getMessage());
            activeMembersArea.setText("Error loading report: " + e.getMessage());
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

    
}
