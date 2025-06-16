package Library.src.main.java.com.LibraryManagement.dao;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.time.LocalDate;
import Library.src.main.java.com.LibraryManagement.model.IssueRecord;


public class IssueRecordDAOImpl implements IssueRecordDAO {

    private final Connection connection;

    public IssueRecordDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addIssueRecord(IssueRecord issueRecord) throws SQLException {
        String sql = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, issueRecord.getBookId());
            ps.setInt(2, issueRecord.getMemberId());
            ps.setString(3, String.valueOf(issueRecord.getStatus()));
            ps.setDate(4, Date.valueOf(issueRecord.getIssueDate()));
            if (issueRecord.getReturnDate() != null) {
                ps.setDate(5, Date.valueOf(issueRecord.getReturnDate()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            ps.executeUpdate();
        }
    }

    @Override
    public void updateIssueRecord(IssueRecord issueRecord) throws SQLException {
        String sql = "UPDATE issue_records SET Status=?, ReturnDate=? WHERE IssueId=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(issueRecord.getStatus()));
            if (issueRecord.getReturnDate() != null) {
                ps.setDate(2, Date.valueOf(issueRecord.getReturnDate()));
            } else {
                ps.setNull(2, Types.DATE);
            }
            ps.setInt(3, issueRecord.getIssueId());
            ps.executeUpdate();
        }
    }

    @Override
    public IssueRecord getIssueRecordById(int issueId) throws SQLException {
        String sql = "SELECT * FROM issue_records WHERE IssueId=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, issueId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    IssueRecord ir = new IssueRecord();
                    ir.setIssueId(rs.getInt("IssueId"));
                    ir.setBookId(rs.getInt("BookId"));
                    ir.setMemberId(rs.getInt("MemberId"));
                    ir.setStatus(rs.getString("Status").charAt(0));
                    ir.setIssueDate(rs.getDate("IssueDate").toLocalDate());
                    Date retDate = rs.getDate("ReturnDate");
                    if (retDate != null) {
                        ir.setReturnDate(retDate.toLocalDate());
                    }
                    return ir;
                }
            }
        }
        return null;
    }

    @Override
    public List<IssueRecord> getAllIssueRecords() throws SQLException {
        List<IssueRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM issue_records";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                IssueRecord ir = new IssueRecord();
                ir.setIssueId(rs.getInt("IssueId"));
                ir.setBookId(rs.getInt("BookId"));
                ir.setMemberId(rs.getInt("MemberId"));
                ir.setStatus(rs.getString("Status").charAt(0));
                ir.setIssueDate(rs.getDate("IssueDate").toLocalDate());
                Date retDate = rs.getDate("ReturnDate");
                if (retDate != null) {
                    ir.setReturnDate(retDate.toLocalDate());
                }
                records.add(ir);
            }
        }
        return records;
    }

    @Override
    public List<IssueRecord> getActiveIssuesByMember(int memberId) throws SQLException {
        List<IssueRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM issue_records WHERE MemberId=? AND Status='I'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    IssueRecord ir = new IssueRecord();
                    ir.setIssueId(rs.getInt("IssueId"));
                    ir.setBookId(rs.getInt("BookId"));
                    ir.setMemberId(rs.getInt("MemberId"));
                    ir.setStatus(rs.getString("Status").charAt(0));
                    ir.setIssueDate(rs.getDate("IssueDate").toLocalDate());
                    Date retDate = rs.getDate("ReturnDate");
                    if (retDate != null) {
                        ir.setReturnDate(retDate.toLocalDate());
                    }
                    records.add(ir);
                }
            }
        }
        return records;
    }

    @Override
    public List<IssueRecord> getOverdueBooks() throws SQLException {
        // Assuming overdue means status='I' and issueDate older than some due period (e.g., 14 days)
        List<IssueRecord> overdue = new ArrayList<>();
        String sql = "SELECT * FROM issue_records WHERE Status='I' AND IssueDate < ?";
        LocalDate cutoffDate = LocalDate.now().minusDays(14);
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(cutoffDate));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    IssueRecord ir = new IssueRecord();
                    ir.setIssueId(rs.getInt("IssueId"));
                    ir.setBookId(rs.getInt("BookId"));
                    ir.setMemberId(rs.getInt("MemberId"));
                    ir.setStatus(rs.getString("Status").charAt(0));
                    ir.setIssueDate(rs.getDate("IssueDate").toLocalDate());
                    Date retDate = rs.getDate("ReturnDate");
                    if (retDate != null) {
                        ir.setReturnDate(retDate.toLocalDate());
                    }
                    overdue.add(ir);
                }
            }
        }
        return overdue;
    }
}