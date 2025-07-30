import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class IssueService {
    private IssueRecordDAO issueRecordDAO;
    private BookDAO bookDAO;
    private MemberDAO memberDAO;

    public IssueService(IssueRecordDAO issueRecordDAO, BookDAO bookDAO, MemberDAO memberDAO) {
        this.issueRecordDAO = issueRecordDAO;
        this.bookDAO = bookDAO;
        this.memberDAO = memberDAO;
    }

    public boolean issueBook(int bookId, int memberId, Date issueDate) throws Exception {
        if (bookId <= 0 || memberId <= 0 || issueDate == null) {
            throw new IllegalArgumentException("Invalid book ID, member ID, or issue date");
        }

        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            throw new Exception("Book not found");
        }
        if (book.getAvailability() != 'A') {
            throw new Exception("Book is not available for issue");
        }

        Member member = (Member) memberDAO.getMemberById(memberId);
        if (member == null) {
            throw new Exception("Member not found");
        }

        boolean issued = issueRecordDAO.issueBook(bookId, memberId, issueDate);
        if (issued) {
            return bookDAO.updateBookAvailability(bookId, 'I');
        }
        return false;
    }

    public boolean returnBook(int issueId, Date returnDate) throws Exception {
        if (issueId <= 0 || returnDate == null) {
            throw new IllegalArgumentException("Invalid issue ID or return date");
        }

        IssueRecord issueRecord = issueRecordDAO.getIssueRecordById(issueId);
        if (issueRecord == null || issueRecord.getStatus() == 'R') {
            throw new Exception("Invalid issue record or book already returned");
        }

        boolean returned = issueRecordDAO.returnBook(issueId, returnDate);
        if (returned) {
            return bookDAO.updateBookAvailability(issueRecord.getBookId(), 'A');
        }
        return false;
    }

    public List<IssueRecord> getAllIssueRecords() {
        return issueRecordDAO.getAllIssueRecords();
    }

    public List<IssueRecord> getActiveIssueRecords() {
        return issueRecordDAO.getActiveIssueRecords();
    }

    public List<IssueRecord> getOverdueIssueRecords(Date currentDate) {
        return issueRecordDAO.getActiveIssueRecords().stream()
                .filter(record -> record.getIssueDate().before(currentDate))
                .collect(Collectors.toList());
    }

    public List<IssueRecord> getIssueRecordsByMember(int memberId) {
        return issueRecordDAO.getIssueRecordsByMember(memberId);
    }

    public List<IssueRecord> getIssueRecordsByBook(int bookId) {
        return issueRecordDAO.getIssueRecordsByBook(bookId);
    }
}