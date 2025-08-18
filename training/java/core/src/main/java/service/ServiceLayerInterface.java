package service;


	import model.Book;
	import model.BookAvailability;
	import model.Issuerecords;
	import model.IssueStatus;
	import model.Member;

	import java.time.LocalDate;
	import java.util.List;
	import java.util.Map;

	public interface ServiceLayerInterface {

	    
	    List<Book> getAllBooks();
	    Book getBookById(int bookId);
	    int addBook(Book book);
	    int updateBook(Book book);
	    int deleteBook(int bookId);
	    int updateBookAvailability(int bookId, BookAvailability availability);

	    
	    int addMember(Member member);
	    Member updateMember(Member member) throws Exception;
	    List<Member> getAllMembers();
	    Member getMemberById(int memberId);

	
	    int issueBook(int bookId, int memberId, IssueStatus status, LocalDate issueDate, LocalDate returnDate);
	    int returnBook(int bookId, int memberId);
	    boolean checkBookIssued(int bookId, int memberId);
	    List<Issuerecords> getAllIssueRecords();
	    Issuerecords getIssueRecord(int bookId, int memberId);

	    
	    List<Book> getOverdueBooks();
	    Map<String, Long> getBookCountByCategory();
	    List<Member> getMembersWithActiveIssuedBooks();
	}

	


