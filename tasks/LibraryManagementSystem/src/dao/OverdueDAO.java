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
            SELECT IssueId, BookId, MemberId, Status, IssueDate, ReturnDate
            FROM issue_records
            WHERE Status = 'I' AND ReturnDate IS NULL
              AND IssueDate < CURDATE() - INTERVAL 14 DAY
            ORDER BY IssueDate ASC
        """;

        List<IssueRecord> overdueList = new ArrayList<>();

        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	IssueRecord record = new IssueRecord(
                        rs.getInt("IssueId"),
                        rs.getInt("BookId"),
                        rs.getInt("MemberId"),
                        rs.getString("Status").charAt(0),
                        rs.getDate("IssueDate").toLocalDate(),
                        null // ReturnDate is null for overdue
                    );
                overdueList.add(record);
            }
        }

        return overdueList;
    }
}
