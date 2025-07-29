package Service;

import DAO.Issue_RecordDAO;
import domain.Issue_records;

import java.sql.SQLException;
import java.util.List;

public class IssueRecordService implements IssueRecordServiceInterface {

    Issue_RecordDAO dao = new Issue_RecordDAO();

    @Override
    public boolean issueBook(int bookId, int memberId) throws SQLException {
        return dao.issueBook(bookId, memberId);
    }

    @Override
    public boolean returnBook(int bookId, int memberId) throws SQLException {
        return dao.returnBook(bookId, memberId);
    }

    @Override
    public List<Issue_records> getAllIssuedRecords() throws SQLException {
        return dao.printAllIssueRecords();
    }
}
