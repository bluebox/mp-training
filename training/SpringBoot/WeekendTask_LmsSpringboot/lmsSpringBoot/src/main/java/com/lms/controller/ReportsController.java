package com.lms.controller;

import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.dto.BookDTO;
import com.lms.service.ReportsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:3000")  // allow React frontend
public class ReportsController {

    private final ReportsService service;

    public ReportsController(ReportsService service) {
        this.service = service;
    }

    @GetMapping("/overdue-books")
    public ResponseEntity<List<BookDTO>> overdueBooks() {
        List<Book> books = service.getOverdueBooks();

        List<BookDTO> dtoList = books.stream()
                .map(book -> new BookDTO(
                        book.getBookId(),
                        book.getBookTitle(),
                        book.getBookAuthor(),
                        book.getBookCategory() != null ? book.getBookCategory().name() : null,
                        book.getStatus(),
                        book.getAvailability()
                ))
                .collect(Collectors.toList());

        if (dtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/books-by-category")
    public ResponseEntity<Map<String, Long>> booksByCategory() {
        Map<String, Long> categoryReport = service.getBookCountByCategory();
        if (categoryReport.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categoryReport);
    }

    @GetMapping("/active-members")
    public ResponseEntity<List<Member>> activeMembers() {
        List<Member> members = service.getMembersWithActiveIssues();
        if (members.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(members);
    }
}
