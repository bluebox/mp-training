package PustakaLokam.library.service;

import PustakaLokam.library.dao.BookDAO;
import PustakaLokam.library.dao.IssueRecordDAO;
//import PustakaLokam.library.dao.MemberDAO;
import PustakaLokam.library.exceptionhandler.*;
import PustakaLokam.library.model.Book;
import PustakaLokam.library.model.IssueRecord;
//import PustakaLokam.library.model.Member;
import PustakaLokam.library.enums.AvailabilityStatus;
import PustakaLokam.library.utilities.DBConnectivityUtility;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class IssueRecordService {
	private final IssueRecordDAO issueDao = new IssueRecordDAO();
	private final BookDAO bookDao = new BookDAO();
	private final MemberDAO memberDao = new MemberDAO();

	public IssueRecord issueBook(int bookID, int memberId) {
		Connection conn = null;
		try {
			conn = DBConnectivityUtility.getConnection();
			conn.setAutoCommit(false);

			Book book = bookDao.getBookByID(bookID);
			if (book == null)
				throw new BookNotFoundException("Book " + bookID + " not found.");
			if (book.getAvailability() != AvailabilityStatus.AVAILABLE)
				throw new BookNotFoundException("Book " + bookID + " is not available.");

			Member member = memberDao.getMemberByID(memberId);
			if (member == null)
				throw new MemberNotFoundException("Member " + memberId + " not found.");

			IssueRecord rec = new IssueRecord(bookID, memberId);
			issueDao.insertIssue(rec, conn);

			bookDao.updateBookAvailability(bookID, AvailabilityStatus.ISSUED, conn);

			conn.commit();
			return rec;

		} catch (SQLException | RuntimeException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			throw new IssueOperationException("Failed to issue book.", e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void returnBook(int issueId) {
		try (Connection conn = DBConnectivityUtility.getConnection()) {
			conn.setAutoCommit(false);

			IssueRecord rec = issueDao.findByID(issueId, conn);
			if (rec == null)
				throw new IssueNotFoundException("Issue record " + issueId + " not found.");

			issueDao.markReturnedBook(issueId, LocalDate.now(), conn);

			bookDao.updateBookAvailability(rec.getBookID(), AvailabilityStatus.AVAILABLE, conn);

			conn.commit();
		} catch (SQLException | RuntimeException e) {
			throw new IssueOperationException("Failed to return book.", e);
		}
	}

	public List<IssueRecord> activeIssuesForMember(int memberID) throws SQLException {
		return issueDao.findActiveIssuesByMember(memberID);
	}
}
