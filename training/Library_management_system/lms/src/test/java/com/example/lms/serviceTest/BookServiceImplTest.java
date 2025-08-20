package com.example.lms.serviceTest;

import com.example.lms.model.Book;
import com.example.lms.repository.BookRepository;
import com.example.lms.service.impl.BookServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private final Long bookId = 1L;

    @BeforeEach
    void setUp() {
        book = Book.builder()
                .bookId(bookId)
                .title("Sample Book")
                .author("Author")
                .category("Fiction")
                .status("Available")
                .availability("Yes")
                .build();
    }

    @Test
    void addBook_shouldSaveBook() {
        given(bookRepository.save(any(Book.class))).willReturn(1);

        int result= bookService.addBook(book);

        assertThat(result).isEqualTo(1);
        verify(bookRepository).save(book);
    }

    @Test
    void getBookById_shouldReturnBook() {
        given(bookRepository.findById(bookId)).willReturn(book);

        Book foundBook = bookService.getBookById(bookId);

        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getBookId()).isEqualTo(bookId);
    }


    @Test
    void getAllBooks_shouldReturnListOfBooks() {
        Book book2 = Book.builder()
                .bookId(2L)
                .title("Another Book")
                .author("Another Author")
                .category("Non-Fiction")
                .status("Available")
                .availability("Yes")
                .build();

        given(bookRepository.findAll()).willReturn(List.of(book, book2));

        List<Book> books = bookService.getAllBooks();

        assertThat(books).isNotNull();
        assertThat(books).hasSize(2);
    }

    @Test
    void deleteBook_shouldCallDeleteById() {
        given(bookRepository.deleteById(bookId)).willReturn(1);

        int result = bookService.deleteBook(bookId);

        assertThat(result).isEqualTo(1);
        verify(bookRepository).deleteById(bookId);
    }

//    @Test
//    void updateBook_shouldUpdateExistingBook() {
//    	 given(bookRepository.update(book)).willReturn(1);
//
//         int result = bookService.updateBook(bookId,book);
//
//         assertThat(result).isEqualTo(1);
//         verify(bookRepository).update(book);
//    }

   
}


