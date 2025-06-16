package com.casestudyjunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.casestudy.Availability;
import com.casestudy.Book;
import com.casestudy.Status;

public class BookTest {

    @Test
    public void testConstructorWithBookId() {
        Book book = new Book(1, "Java Basics", "John Doe", "Programming", Status.ACTIVE, Availability.AVAILABLE);

        assertEquals(Integer.valueOf(1), book.getBookId());
        assertEquals("Java Basics", book.getTitle());
        assertEquals("John Doe", book.getAuthor());
        assertEquals("Programming", book.getCategory());
        assertEquals(Status.ACTIVE, book.getStatus());
        assertEquals(Availability.AVAILABLE, book.getAvailable());
    }

    @Test
    public void testConstructorWithoutBookId() {
        Book book = new Book("Python", "Jane Smith", "Programming", Status.INACTIVE, Availability.ISSUED);

       // assertNull(book.getBookId());  // bookId not set
        assertEquals("Python", book.getTitle());
        assertEquals("Jane Smith", book.getAuthor());
        assertEquals("Programming", book.getCategory());
        assertEquals(Status.INACTIVE, book.getStatus());
        assertEquals(Availability.ISSUED, book.getAvailable());
    }

    @Test
    public void testSettersAndGetters() {
        Book book = new Book("C++", "Bjarne", "Programming", Status.ACTIVE, Availability.AVAILABLE);

        book.setBookId(10);
        book.setTitle("C++ Updated");
        book.setAuthor("Stroustrup");
        book.setCategory("Systems");
        book.setStatus(Status.INACTIVE);
        book.setAvailable(Availability.ISSUED);

        assertEquals(Integer.valueOf(10), book.getBookId());
        assertEquals("C++ Updated", book.getTitle());
        assertEquals("Stroustrup", book.getAuthor());
        assertEquals("Systems", book.getCategory());
        assertEquals(Status.INACTIVE, book.getStatus());
        assertEquals(Availability.ISSUED, book.getAvailable());
    }

    @Test
    public void testToString() {
        Book book = new Book(2, "Spring", "Rod Johnson", "Framework", Status.ACTIVE, Availability.AVAILABLE);
        String result = book.toString();
        assertTrue(result.contains("Spring"));
        assertTrue(result.contains("Rod Johnson"));
        assertTrue(result.contains("Framework"));
        assertTrue(result.contains("ACTIVE"));
        assertTrue(result.contains("AVAILABLE"));
    }
}

