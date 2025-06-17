package dao;

import model.IssueRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class OverdueDAO {

    public List<IssueRecord> getOverdueRecords() throws Exception {
        String query = """
            SELECT issueId, bookId, memberId, status, issueDate, returnDate
            FROM issue_records
            WHERE status = 'I' AND returnDate IS NULL
              AND issueDate < CURDATE() - INTERVAL 14 DAY
            ORDER BY issueDate ASC
        """;

        List<IssueRecord> overdueList = new ArrayList<>();

        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	IssueRecord record = new IssueRecord(
                        rs.getInt("issueId"),
                        rs.getInt("bookId"),
                        rs.getInt("memberId"),
                        rs.getString("status").charAt(0),
                        rs.getDate("issueDate").toLocalDate(),
                        null // ReturnDate is null for overdue
                    );
                overdueList.add(record);
            }
        }

        return overdueList;
    }
}
