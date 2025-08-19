package com.lms.controller;

import com.lms.model.ReturnBook;
import com.lms.service.ReturnBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/return")  
@RequiredArgsConstructor
public class ReturnBookController {

    private final ReturnBookService returnBookService;

    @GetMapping("/member-name/{mobile}")
    public ResponseEntity<String> getMemberName(@PathVariable String mobile) {
        String name = returnBookService.getMemberNameByMobile(mobile);
        if (name == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(name);
    }

    @GetMapping("/issued-books/{mobile}")
    public ResponseEntity<List<String>> getIssuedBooks(@PathVariable String mobile) {
        List<String> books = returnBookService.getIssuedBooksByMobile(mobile);
        if (books == null || books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<String> returnBook(@RequestBody ReturnBook returnBook) {
        boolean success = returnBookService.returnBook(returnBook);
        return success
                ? ResponseEntity.ok("Book returned successfully")
                : ResponseEntity.badRequest().body("Book return failed");
    }
}
