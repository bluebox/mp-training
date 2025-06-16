package com.library.test;

import com.library.service.LibraryServiceImplementation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReturnBookTest {

    LibraryServiceImplementation service = new LibraryServiceImplementation();


    @Test
    void invalidBookId() throws Exception {
        int invalidBookId = -1;
        int memberId = 101;

        boolean result = service.returnBook(invalidBookId, memberId);
        assertFalse(result, "Expected return to fail for invalid book ID");
    }

    @Test
    void invalidMemberId() throws Exception {
        int bookId = 1;
        int invalidMemberId = -99;

        boolean result = service.returnBook(bookId, invalidMemberId);
        assertFalse(result, "Expected return to fail for invalid member ID");
    }
    @Test
    void noBookId() throws Exception {
        int bookId = 1;
        int invalidMemberId = -99;

        boolean result = service.returnBook(bookId, invalidMemberId);
        assertFalse(result, "Expected return to fail for invalid member ID");
    }

    @Test
    void notIssuedBook() throws Exception {
        int bookId = 5;
        int memberId = 101;

        boolean result = service.returnBook(bookId, memberId);
        assertFalse(result, "Expected return to fail for book not currently issued");
    }
}
