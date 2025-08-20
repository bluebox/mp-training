//
//
//package com.example.lms.service.impl;
//
//import com.example.lms.model.Book;
//import com.example.lms.repository.BookRepository;
//import com.example.lms.service.BookService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class BookServiceImplTest {
//
//    private BookRepository bookRepository;
//    private BookService bookService;
//
//    @BeforeEach
//    void setUp() {
//        bookRepository = mock(BookRepository.class);
//        bookService = new BookServiceImpl(bookRepository);
//    }
//
//    @Test
//    void addBook_shouldCallSave() {
//        Book book = createSampleBook(null);
//        bookService.addBook(book);
//
//        verify(bookRepository, times(1)).save(book);
//    }
//
//    @Test
//    void updateBook_shouldUpdateExistingBook() {
//        Book book = createSampleBook(null);
//        Book existing = createSampleBook(1L);
//
//        when(bookRepository.findById(1L)).thenReturn(existing);
//
//        bookService.updateBook(1L, book);
//
//        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
//        verify(bookRepository).update(captor.capture());
//
//        Book updated = captor.getValue();
//        assertEquals(1L, updated.getBookId());
//        assertEquals(book.getTitle(), updated.getTitle());
//    }
//
//    @Test
//    void updateBook_shouldThrowExceptionWhenBookNotFound() {
//        when(bookRepository.findById(1L)).thenReturn(null);
//        Book book = createSampleBook(null);
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            bookService.updateBook(1L, book);
//        });
//
//        assertEquals("Book not found with id 1", exception.getMessage());
//    }
//
//    @Test
//    void deleteBook_shouldCallDeleteById() {
//        bookService.deleteBook(1L);
//        verify(bookRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    void getBookById_shouldReturnBook() {
//        Book book = createSampleBook(1L);
//        when(bookRepository.findById(1L)).thenReturn(book);
//
//        Book result = bookService.getBookById(1L);
//
//        assertEquals(book, result);
//    }
//
//    @Test
//    void getBookById_shouldThrowExceptionIfNotFound() {
//        when(bookRepository.findById(1L)).thenReturn(null);
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            bookService.getBookById(1L);
//        });
//
//        assertEquals("Book not found with id 1", exception.getMessage());
//    }
//
//    @Test
//    void getAllBooks_shouldReturnAllBooks() {
//        List<Book> books = Arrays.asList(createSampleBook(1L), createSampleBook(2L));
//        when(bookRepository.findAll()).thenReturn(books);
//
//        List<Book> result = bookService.getAllBooks();
//
//        assertEquals(2, result.size());
//        verify(bookRepository, times(1)).findAll();
//    }
//
//    // Helper method to create sample book
//    private Book createSampleBook(Long id) {
//        Book book = new Book();
//        book.setBookId(id);
//        book.setTitle("Sample Book");
//        book.setAuthor("Author");
//        book.setCategory("Fiction");
//        book.setStatus("Available");
//        book.setAvailability("Yes");
//        return book;
//    }
//}


//----------------------


//
//package com.example.lms.service.impl;
//
//import com.example.lms.model.Book;
//import com.example.lms.model.IssueRecord;
//import com.example.lms.repository.BookRepository;
//import com.example.lms.repository.IssueRepository;
//import com.example.lms.service.IssueService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class IssueServiceImplTest {
//
//    private IssueRepository issueRepository;
//    private BookRepository bookRepository;
//    private IssueService issueService;
//
//    @BeforeEach
//    void setUp() {
//        issueRepository = mock(IssueRepository.class);
//        bookRepository = mock(BookRepository.class);
//        issueService = new IssueServiceImpl(issueRepository, bookRepository);
//    }
//
//    @Test
//    void issueBook_shouldIssueSuccessfully() {
//        Book book = createBook(1L, "A");
//        when(bookRepository.findById(1L)).thenReturn(book);
//
//        String result = issueService.issueBook(1L, 100L);
//
//        assertEquals("Book issued successfully", result);
//        verify(bookRepository).update(book);
//        verify(issueRepository).save(any(IssueRecord.class));
//    }
//
//    @Test
//    void issueBook_shouldFailWhenBookNotFound() {
//        when(bookRepository.findById(1L)).thenReturn(null);
//
//        String result = issueService.issueBook(1L, 100L);
//
//        assertEquals("Book not found", result);
//        verify(bookRepository, never()).update(any());
//        verify(issueRepository, never()).save(any());
//    }
//
//    @Test
//    void issueBook_shouldFailWhenBookAlreadyIssued() {
//        Book book = createBook(1L, "I");
//        when(bookRepository.findById(1L)).thenReturn(book);
//
//        String result = issueService.issueBook(1L, 100L);
//
//        assertEquals("Book is already issued", result);
//        verify(bookRepository, never()).update(any());
//        verify(issueRepository, never()).save(any());
//    }
//
//    @Test
//    void returnBook_shouldReturnSuccessfully() {
//        IssueRecord record = createIssuedRecord(1L, 1L, 100L);
//        Book book = createBook(1L, "I");
//
//        when(issueRepository.findById(1L)).thenReturn(record);
//        when(bookRepository.findById(1L)).thenReturn(book);
//
//        String result = issueService.returnBook(1L);
//
//        assertEquals("Book returned successfully", result);
//        verify(issueRepository).markReturned(eq(1L), any(LocalDate.class));
//        verify(bookRepository).update(book);
//    }
//
//    @Test
//    void returnBook_shouldFailIfAlreadyReturned() {
//        IssueRecord record = createReturnedRecord(1L, 1L, 100L);
//        when(issueRepository.findById(1L)).thenReturn(record);
//
//        String result = issueService.returnBook(1L);
//
//        assertEquals("Book already returned", result);
//        verify(issueRepository, never()).markReturned(anyLong(), any());
//        verify(bookRepository, never()).update(any());
//    }
//
//    @Test
//    void returnBook_shouldFailIfIssueRecordNotFound() {
//        when(issueRepository.findById(1L)).thenReturn(null);
//
//        String result = issueService.returnBook(1L);
//
//        assertEquals("Issue record not found", result);
//        verify(issueRepository, never()).markReturned(anyLong(), any());
//        verify(bookRepository, never()).update(any());
//    }
//
//    @Test
//    void getIssueById_shouldReturnIssueRecord() {
//        IssueRecord record = createIssuedRecord(1L, 1L, 100L);
//        when(issueRepository.findById(1L)).thenReturn(record);
//
//        IssueRecord result = issueService.getIssueById(1L);
//
//        assertEquals(record, result);
//        verify(issueRepository).findById(1L);
//    }
//
//    @Test
//    void getAllIssues_shouldReturnAllRecords() {
//        List<IssueRecord> records = Arrays.asList(
//                createIssuedRecord(1L, 1L, 100L),
//                createIssuedRecord(2L, 2L, 101L)
//        );
//        when(issueRepository.findAll()).thenReturn(records);
//
//        List<IssueRecord> result = issueService.getAllIssues();
//
//        assertEquals(2, result.size());
//        verify(issueRepository).findAll();
//    }
//
//    // === Helper Methods ===
//
//    private Book createBook(Long id, String availability) {
//        Book book = new Book();
//        book.setBookId(id);
//        book.setTitle("Sample Book");
//        book.setAvailability(availability);  // "A" or "I"
//        return book;
//    }
//
//    private IssueRecord createIssuedRecord(Long issueId, Long bookId, Long memberId) {
//        return new IssueRecord(issueId, bookId, memberId, "I", LocalDate.now(), null);
//    }
//
//    private IssueRecord createReturnedRecord(Long issueId, Long bookId, Long memberId) {
//        return new IssueRecord(issueId, bookId, memberId, "R", LocalDate.now().minusDays(5), LocalDate.now());
//    }
//}


//-----------


//package com.example.lms.service.impl;
//
//import com.example.lms.model.Member;
//import com.example.lms.repository.MemberRepository;
//import com.example.lms.service.MemberService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class MemberServiceImplTest {
//
//    private MemberRepository memberRepository;
//    private MemberService memberService;
//
//    @BeforeEach
//    void setUp() {
//        memberRepository = mock(MemberRepository.class);
//        memberService = new MemberServiceImpl(memberRepository);
//    }
//
//    @Test
//    void addMember_shouldCallSave() {
//        Member member = createSampleMember(null);
//        when(memberRepository.save(member)).thenReturn(1);
//
//        int result = memberService.addMember(member);
//
//        verify(memberRepository, times(1)).save(member);
//        assertEquals(1, result);
//    }
//
//    @Test
//    void updateMember_shouldCallUpdate() {
//        Member member = createSampleMember(1L);
//        when(memberRepository.update(member)).thenReturn(1);
//
//        int result = memberService.updateMember(member);
//
//        verify(memberRepository, times(1)).update(member);
//        assertEquals(1, result);
//    }
//
//    @Test
//    void deleteMember_shouldCallDeleteById() {
//        when(memberRepository.deleteById(1L)).thenReturn(1);
//
//        int result = memberService.deleteMember(1L);
//
//        verify(memberRepository, times(1)).deleteById(1L);
//        assertEquals(1, result);
//    }
//
//    @Test
//    void getMemberById_shouldReturnMember() {
//        Member member = createSampleMember(1L);
//        when(memberRepository.findById(1L)).thenReturn(member);
//
//        Member result = memberService.getMemberById(1L);
//
//        verify(memberRepository, times(1)).findById(1L);
//        assertEquals(member, result);
//    }
//
//    @Test
//    void getAllMembers_shouldReturnAllMembers() {
//        List<Member> members = Arrays.asList(createSampleMember(1L), createSampleMember(2L));
//        when(memberRepository.findAll()).thenReturn(members);
//
//        List<Member> result = memberService.getAllMembers();
//
//        verify(memberRepository, times(1)).findAll();
//        assertEquals(2, result.size());
//    }
//
//    // Helper method to create sample member
//    private Member createSampleMember(Long id) {
//        Member member = new Member();
//        member.setMemberId(id);
//        member.setName("John Doe");
//        member.setEmail("john@example.com");
//        member.setMobile("1234567890");
//        member.setGender("Male");
//        member.setAddress("123 Main Street");
//        return member;
//    }
//}


//---------------------
