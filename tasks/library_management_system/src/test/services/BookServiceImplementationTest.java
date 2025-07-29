//package test.services;
//
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//import org.junit.After;
//
//import DAO.Databasemanager;
//import domain.Book;
//import domain.checking_enum.Availability;
//import domain.checking_enum.Status;
//import Service.BookService;
//
//
//public class BookServiceImplementationTest {
//	private BookService service;
//	private int generatedId;
//
//    @Before
//    public void setUp() {
//        service = new BookService();
//        Databasemanager dao=new Databasemanager();
//        DBConnection.connectToDB("jdbc:mysql://localhost:3306/library_management_system");
//        this.generatedId=dao.addBooks(new Book("test", "test", BookCategory.FICTION));
//    }
//
////    @Test(expected = InvalidDetailsException.class)
////    public void testValidateAddBook_WithEmptyTitle_ShouldThrowException() throws Exception {
////        service.validateAddBook("", "Visaal", BookCategory.NON_FICTION);
////    }
//
////    @Test(expected = InvalidDetailsException.class)
////    public void testValidateAddBook_WithNullCategory_ShouldThrowException() throws Exception {
////        service.validateAddBook("Love Story", "Rohit", null);
////    }
//
////    @Test(expected = InvalidDetailsException.class)
////    public void testValidateAddBook_WithTooLongTitle_ShouldThrowException() throws Exception {
////    	StringBuilder sb=new StringBuilder("");
////    	for(int i=1; i<=256; i++) {
////    		sb.append("R");
////    	}
////        String overflowedTitle = sb.toString();
////        service.validateAddBook(overflowedTitle, "Author", BookCategory.SCIENCE_FICTION);
////    }
//
//    @Test
//    public void testValidateViewAllBooks_ShouldReturnList() throws Exception {
//        assertNotNull(service.viewallbooks());
//    }
//
//    @Test//(expected = InvalidDetailsException.class)
//    public void testValidateUpdateBookDetails_WithEmptyFields_ShouldThrowException() throws Exception {
//        service.validateUpdateBookDetails(this.generatedId, "", "", BookCategory.FICTION, BookStatus.ACTIVE);
//    }
//
//    @Test//(expected = InvalidDetailsException.class)
//    public void testValidateUpdateBookDetails_WithUnchangedData_ShouldThrowException() throws Exception {
//        service.validateUpdateBookDetails(this.generatedId, "test", "test", BookCategory.FICTION, BookStatus.ACTIVE);
//    }
//
//    @Test
//    public void testValidateUpdateBookDetails_WithChanges_ShouldPass() throws Exception {
//        service.validateUpdateBookDetails(this.generatedId, "Ross", "Varma", BookCategory.MYSTERY, BookStatus.INACTIVE);
//    }
//    
//    @After
//    public void tearDown() throws Exception {
//    	BookDaoImplementation dao=new BookDaoImplementation();
//    	dao.deleteBook(new Book(this.generatedId, "test", "test", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE));
//    	DBConnection.closeStatement();
//        DBConnection.closeConn();
//    }
//}
