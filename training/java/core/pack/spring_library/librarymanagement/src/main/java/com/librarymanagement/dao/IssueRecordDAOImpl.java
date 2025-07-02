package com.librarymanagement.dao;

import com.librarymanagement.model.IssueRecord;
import com.librarymanagement.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class IssueRecordDAOImpl implements IssueRecordDAO {

    private final DataSource dataSource;

    @Autowired
    public IssueRecordDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addIssueRecord(IssueRecord issueRecord) throws SQLException {
        String sql = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
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
        String fetchOld = "SELECT BookId, MemberId, IssueDate FROM issue_records WHERE IssueId = ?";
        String insertLog = "INSERT INTO issueUpdate_logs (IssueId, BookId, MemberId, IssueDate, Returndate, Time) VALUES (?, ?, ?, ?, ?, ?)";
        String updateIssue = "UPDATE issue_records SET Status = ?, ReturnDate = ? WHERE IssueId = ?";

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement psFetch = connection.prepareStatement(fetchOld)) {
                psFetch.setInt(1, issueRecord.getIssueId());
                try (ResultSet rs = psFetch.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Issue record with ID " + issueRecord.getIssueId() + " not found.");
                    }

                    int oldBookId = rs.getInt("BookId");
                    int oldMemberId = rs.getInt("MemberId");
                    LocalDate oldIssueDate = rs.getDate("IssueDate").toLocalDate();

                    try (PreparedStatement psLog = connection.prepareStatement(insertLog)) {
                        psLog.setInt(1, issueRecord.getIssueId());
                        psLog.setInt(2, oldBookId);
                        psLog.setInt(3, oldMemberId);
                        psLog.setDate(4, Date.valueOf(oldIssueDate));
                        if (issueRecord.getReturnDate() != null) {
                            psLog.setDate(5, Date.valueOf(issueRecord.getReturnDate()));
                        } else {
                            psLog.setNull(5, Types.DATE);
                        }
                        psLog.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                        psLog.executeUpdate();
                    }

                    try (PreparedStatement psUpdate = connection.prepareStatement(updateIssue)) {
                        psUpdate.setString(1, String.valueOf(issueRecord.getStatus()));
                        if (issueRecord.getReturnDate() != null) {
                            psUpdate.setDate(2, Date.valueOf(issueRecord.getReturnDate()));
                        } else {
                            psUpdate.setNull(2, Types.DATE);
                        }
                        psUpdate.setInt(3, issueRecord.getIssueId());
                        psUpdate.executeUpdate();
                    }

                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    @Override
    public IssueRecord getIssueRecordById(int issueId) throws SQLException {
        String sql = "SELECT IssueId, MemberId, BookId, Status, IssueDate, ReturnDate FROM issue_records";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, issueId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToIssueRecord(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<IssueRecord> getAllIssueRecords() throws SQLException {
        List<IssueRecord> records = new ArrayList<>();
        String sql = "SELECT IssueId, MemberId, BookId, Status, IssueDate, ReturnDate FROM issue_records";
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                records.add(mapRowToIssueRecord(rs));
            }
        }
        return records;
    }

    @Override
    public List<IssueRecord> getActiveIssuesByMember(int memberId) throws SQLException {
        List<IssueRecord> records = new ArrayList<>();
        String sql = "SELECT IssueId, MemberId, BookId, Status, IssueDate, ReturnDate FROM issue_records WHERE MemberId = ? AND Status = 'I'";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    records.add(mapRowToIssueRecord(rs));
                }
            }
        }
        return records;
    }

    @Override
    public List<IssueRecord> getOverdueBooks() throws SQLException {
        List<IssueRecord> overdue = new ArrayList<>();
        String sql = "SELECT IssueId, MemberId, BookId, Status, IssueDate, ReturnDate FROM issue_records WHERE Status = 'I' AND IssueDate < ?";
        LocalDate cutoffDate = LocalDate.now().minusDays(14);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(cutoffDate));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    overdue.add(mapRowToIssueRecord(rs));
                }
            }
        }
        return overdue;
    }

    //@Override
    public List<Member> getMembersWithActiveIssuedBooks(List<IssueRecord> issueRecords, MemberDAO memberDAO) throws SQLException {
        List<Integer> memberIds = issueRecords.stream()
                .filter(r -> r.getStatus() == 'I' && r.getReturnDate() == null)
                .map(IssueRecord::getMemberId)
                .distinct()
                .collect(Collectors.toList());

        List<Member> members = new ArrayList<>();
        for (int memberId : memberIds) {
            members.add(memberDAO.getMemberById(memberId));
        }
        return members;
    }

    private IssueRecord mapRowToIssueRecord(ResultSet rs) throws SQLException {
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
