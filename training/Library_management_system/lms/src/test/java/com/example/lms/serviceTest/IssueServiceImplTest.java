 package com.example.lms.serviceTest;

import com.example.lms.model.Book;
import com.example.lms.model.IssueRecord;
import com.example.lms.repository.BookRepository;
import com.example.lms.repository.IssueRepository;
import com.example.lms.service.impl.IssueServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IssueServiceImplTest {

    @Mock
    private IssueRepository issueRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private IssueServiceImpl issueService;

    private final Long bookId = 1L;
    private final Long memberId = 100L;
    private final Long issueId = 1L;

    private Book availableBook;
    private Book issuedBook;

    private IssueRecord issuedRecord;
    private IssueRecord returnedRecord;

    @BeforeEach
    void setUp() {
        availableBook = Book.builder()
                .bookId(bookId)
                .title("Sample Book")
                .availability("A")
                .build();

        issuedBook = Book.builder()
                .bookId(bookId)
                .title("Sample Book")
                .availability("I")
                .build();

        issuedRecord = new IssueRecord(issueId, bookId, memberId, "I", LocalDate.now(), null);
        returnedRecord = new IssueRecord(issueId, bookId, memberId, "R", LocalDate.now().minusDays(3), LocalDate.now());
    }

    @Test
    void issueBook_shouldSucceed_whenBookIsAvailable() {
        given(bookRepository.findById(bookId)).willReturn(availableBook);

        String result = issueService.issueBook(bookId, memberId);

        assertThat(result).isEqualTo("Book issued successfully");
        verify(bookRepository).update(availableBook);
        verify(issueRepository).save(any(IssueRecord.class));
    }

    @Test
    void issueBook_shouldFail_whenBookNotFound() {
        given(bookRepository.findById(bookId)).willReturn(null);

        String result = issueService.issueBook(bookId, memberId);

        assertThat(result).isEqualTo("Book not found");
        verify(bookRepository, never()).update(any());
        verify(issueRepository, never()).save(any());
    }

    @Test
    void issueBook_shouldFail_whenBookAlreadyIssued() {
        given(bookRepository.findById(bookId)).willReturn(issuedBook);

        String result = issueService.issueBook(bookId, memberId);

        assertThat(result).isEqualTo("Book is already issued");
        verify(bookRepository, never()).update(any());
        verify(issueRepository, never()).save(any());
    }

    @Test
    void returnBook_shouldSucceed_whenBookIsIssued() {
        given(issueRepository.findById(issueId)).willReturn(issuedRecord);
        given(bookRepository.findById(bookId)).willReturn(issuedBook);

        String result = issueService.returnBook(issueId);

        assertThat(result).isEqualTo("Book returned successfully");
        verify(issueRepository).markReturned(eq(issueId), any(LocalDate.class));
        verify(bookRepository).update(issuedBook);
    }

    @Test
    void returnBook_shouldFail_whenAlreadyReturned() {
        given(issueRepository.findById(issueId)).willReturn(returnedRecord);

        String result = issueService.returnBook(issueId);

        assertThat(result).isEqualTo("Book already returned");
        verify(issueRepository, never()).markReturned(anyLong(), any());
        verify(bookRepository, never()).update(any());
    }

    @Test
    void returnBook_shouldFail_whenRecordNotFound() {
        given(issueRepository.findById(issueId)).willReturn(null);

        String result = issueService.returnBook(issueId);

        assertThat(result).isEqualTo("Issue record not found");
        verify(issueRepository, never()).markReturned(anyLong(), any());
        verify(bookRepository, never()).update(any());
    }

    @Test
    void getIssueById_shouldReturnIssueRecord() {
        given(issueRepository.findById(issueId)).willReturn(issuedRecord);

        IssueRecord result = issueService.getIssueById(issueId);

        assertThat(result).isEqualTo(issuedRecord);
        verify(issueRepository).findById(issueId);
    }

    @Test
    void getAllIssues_shouldReturnListOfRecords() {
        IssueRecord anotherRecord = new IssueRecord(2L, 2L, 101L, "I", LocalDate.now(), null);
        given(issueRepository.findAll()).willReturn(List.of(issuedRecord, anotherRecord));

        List<IssueRecord> records = issueService.getAllIssues();

        assertThat(records).hasSize(2);
        assertThat(records).containsExactly(issuedRecord, anotherRecord);
        verify(issueRepository).findAll();
    }
}
