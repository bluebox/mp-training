package library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import library.dao.interfaceimpl.BookDAOImpl;
import library.dao.interfaceimpl.IssueRecordDAOImpl;
import library.dao.interfaces.BookDAO;
import library.dao.interfaces.IssueRecordDAO;
import library.exception.LibraryException;
import library.model.Book;
import library.model.IssueRecord;
import library.model.Member;
import library.model.enums.BookAvailability;
import library.model.enums.BookStatus;
import library.model.enums.IssueStatus;
import library.service.interfaces.BookService;
import library.service.interfaces.IssueService;
import library.service.interfaces.MemberService;
import library.util.DBConnection;
import library.validation.BookValidator;

public class IssueServiceImpl implements IssueService {

	private BookDAO bookDAO;
	private IssueRecordDAO issueRecordDAO;
	private BookService bookService;
	private MemberService memberService;

	public IssueServiceImpl() {
		this.bookDAO = new BookDAOImpl();
		this.issueRecordDAO = new IssueRecordDAOImpl();

		this.bookService = new BookServiceImpl();
		this.memberService = new MemberServiceImpl();
	}

	@Override
	public void issueBook(int bookId, int memberId, LocalDateTime issueDate, String issuedBy) {
		BookValidator.validateNumericId(bookId, "Book ID");
		BookValidator.validateNumericId(memberId, "Member ID");
		BookValidator.validateIssueDate(issueDate, "Issue Date");
		BookValidator.validateUser(issuedBy, "Issued By User");

		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);

			BookValidator.validateNumericId(bookId, "Book ID");
            Book criteria = new Book();
            criteria.setBookId(bookId);
            List<Book> books = bookService.findBooks(criteria);
            Book book = books.isEmpty() ? null : books.get(0);
	        
			if (book == null) {
				throw new LibraryException("Book with ID " + bookId + " not found.");
			}
			if (book.getAvailability() == BookAvailability.ISSUED) {
				throw new LibraryException("Book '" + book.getTitle() + "' is already issued.");
			}
			if (book.getStatus() == BookStatus.INACTIVE) {
				throw new LibraryException(
						"Book '" + book.getTitle() + "' is inactive and cannot be issued.");
			}

			IssueRecord newIssue = new IssueRecord(bookId, memberId, IssueStatus.ISSUED, issueDate, issuedBy);
			issueRecordDAO.addIssueRecord(newIssue);

			bookDAO.updateBookAvailability(bookId, BookAvailability.ISSUED.getCode(), issuedBy);

			connection.commit();
			System.out.println("Transaction committed: Book ID " + bookId + " issued to Member ID " + memberId);

		} catch (LibraryException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackEx) {
					System.err.println("Error during rollback: " + rollbackEx.getMessage());
				}
			}
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackEx) {
					System.err.println("Error during rollback: " + rollbackEx.getMessage());
				}
			}
			System.err.println("IssueService: Database error during issue book: " + e.getMessage());
			throw new LibraryException("Database error during book issue: " + e.getMessage(), e);
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackEx) {
					System.err.println("Error during rollback: " + rollbackEx.getMessage());
				}
			}
			System.err.println("IssueService: An unexpected error occurred: " + e.getMessage());
			e.printStackTrace();
			throw new LibraryException("An unexpected error occurred during book issue.", e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					System.err.println("Error closing transaction connection: " + e.getMessage());
				}
			}
		}
	}

	@Override
	public void returnBook(int bookId, String returnedBy){
		BookValidator.validateNumericId(bookId, "Book ID");
		BookValidator.validateUser(returnedBy, "Returned By User");

		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);

			IssueRecord activeIssue = issueRecordDAO.getActiveIssueRecordByBookId(bookId);
			if (activeIssue == null) {
				throw new LibraryException("Book ID " + bookId + " is not currently issued.");
			}

			activeIssue.setStatus(IssueStatus.RETURNED);
			activeIssue.setReturnDate(LocalDateTime.now());
			activeIssue.setReturnedBy(returnedBy);

			issueRecordDAO.updateIssueRecord(activeIssue);

			bookDAO.updateBookAvailability(bookId, BookAvailability.AVAILABLE.getCode(), returnedBy); 
																								

			connection.commit();
			System.out.println("Transaction committed: Book ID " + bookId + " returned successfully.");

		} catch (LibraryException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackEx) {
					System.err.println("Error during rollback: " + rollbackEx.getMessage());
				}
			}
            throw new LibraryException("An error occurred during Issue Record data access: " + e.getMessage(), e); 
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackEx) {
					System.err.println("Error during rollback: " + rollbackEx.getMessage());
				}
			}
			System.err.println("IssueService: Database error during return book: " + e.getMessage());
			throw new LibraryException("Database error during book return: " + e.getMessage(), e);
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackEx) {
					System.err.println("Error during rollback: " + rollbackEx.getMessage());
				}
			}
			System.err.println("IssueService: An unexpected error occurred: " + e.getMessage());
			e.printStackTrace();
			throw new LibraryException("An unexpected error occurred during book return.", e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					System.err.println("Error closing transaction connection: " + e.getMessage());
				}
			}
		}
	}

	@Override
	public List<IssueRecord> getAllIssuedRecords(){
		try {
			return issueRecordDAO.getAllIssuedRecords();
		} catch (LibraryException e) {
			System.err.println("IssueService: Error getting all issued records: " + e.getMessage());
            throw new LibraryException("An error occurred during Issue Record data access: " + e.getMessage(), e); 
		}
	}

	@Override
	public List<IssueRecord> getOverdueBooks(int dueDays){
		List<IssueRecord> allIssued = getAllIssuedRecords();
		LocalDateTime now = LocalDateTime.now();

		return allIssued.stream()
				.filter(record -> record.getStatus() == IssueStatus.ISSUED && record.getReturnDate() == null)
				.filter(record -> record.getIssueDate().plusDays(dueDays).isBefore(now)).collect(Collectors.toList());
	}

	@Override
	public List<Member> getMembersWithActiveBooks(){
		List<IssueRecord> allIssued = getAllIssuedRecords();

		Set<Integer> memberIdsWithActiveBooks = allIssued.stream()
				.filter(record -> record.getStatus() == IssueStatus.ISSUED && record.getReturnDate() == null)
				.map(IssueRecord::getMemberId).collect(Collectors.toSet());

		return memberIdsWithActiveBooks.stream().map(memberId -> {
			try {
				return memberService.getMemberById(memberId);
			} catch (LibraryException e) {
				System.err.println(
						"Error fetching member ID " + memberId + " for active books report: " + e.getMessage());
				return null;
			} catch (Exception e) {
				System.err.println("Unexpected error fetching member ID " + memberId + " for active books report: "
						+ e.getMessage());
				return null;
			}
		}).filter(java.util.Objects::nonNull).collect(Collectors.toList());
	}
}