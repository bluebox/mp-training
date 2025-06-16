//package PustakaLokam.library.testing;
//import PustakaLokam.library.enums.AvailabilityStatus;
//import PustakaLokam.library.enums.BookCondition;
//import PustakaLokam.library.model.Book;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//public class BookServiceTesting {
//	@Test
//	public void testingBookConstructorAndGetters() {
//		Book book = new Book(1, "World of Deep Learning", "Rachit Singh", "Soft Computing", BookCondition.ACTIVE, AvailabilityStatus.AVAILABLE);
//		
//		assertEquals(1, book.getBookID());
//		assertEquals("World of Deep Learning", book.getTitle());
//		assertEquals("Rachit Singh", book.getAuthor());
//		assertEquals("Soft Computing", book.getCategory());
//		assertEquals("Active", book.getCondition());
//		assertEquals("Available", book.getAvailability());
//	}
//	
//	@Test
//	public void testingSetters() {
//		Book book = new Book(0,"","","","","");
//		book.setBookID(2);
//		book.setTitle("World of Deep Learning");
//	}
//}
