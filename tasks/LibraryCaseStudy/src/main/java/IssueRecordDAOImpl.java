import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IssueRecordDAOImpl implements IssueRecordDAO {
    @Override
    public boolean issueBook(int bookId, int memberId, Date issueDate) {
        String sql = "INSERT INTO issue_records (book_id, member_id, status, issue_date) VALUES (?, ?, 'I', ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, memberId);
            pstmt.setTimestamp(3, new Timestamp(issueDate.getTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean returnBook(int issueId, Date returnDate) {
        String sql = "UPDATE issue_records SET status = 'R', return_date = ? WHERE issue_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, new Timestamp(returnDate.getTime()));
            pstmt.setInt(2, issueId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public IssueRecord getIssueRecordById(int issueId) {
        String sql = "SELECT * FROM issue_records WHERE issue_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, issueId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new IssueRecord(
                            rs.getInt("issue_id"),
                            rs.getInt("book_id"),
                            rs.getInt("member_id"),
                            rs.getString("status").charAt(0),
                            rs.getTimestamp("issue_date"),
                            rs.getTimestamp("return_date")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IssueRecord> getAllIssueRecords() {
        List<IssueRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM issue_records";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                records.add(new IssueRecord(
                        rs.getInt("issue_id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getString("status").charAt(0),
                        rs.getTimestamp("issue_date"),
                        rs.getTimestamp("return_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public List<IssueRecord> getActiveIssueRecords() {
        List<IssueRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM issue_records WHERE status = 'I'";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                records.add(new IssueRecord(
                        rs.getInt("issue_id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getString("status").charAt(0),
                        rs.getTimestamp("issue_date"),
                        null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public List<IssueRecord> getIssueRecordsByMember(int memberId) {
        List<IssueRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM issue_records WHERE member_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    records.add(new IssueRecord(
                            rs.getInt("issue_id"),
                            rs.getInt("book_id"),
                            rs.getInt("member_id"),
                            rs.getString("status").charAt(0),
                            rs.getTimestamp("issue_date"),
                            rs.getTimestamp("return_date")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public List<IssueRecord> getIssueRecordsByBook(int bookId) {
        List<IssueRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM issue_records WHERE book_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    records.add(new IssueRecord(
                            rs.getInt("issue_id"),
                            rs.getInt("book_id"),
                            rs.getInt("member_id"),
                            rs.getString("status").charAt(0),
                            rs.getTimestamp("issue_date"),
                            rs.getTimestamp("return_date")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}