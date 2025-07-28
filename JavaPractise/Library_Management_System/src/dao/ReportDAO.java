package dao;

import domain.IssueRecord;
import domain.Member;
import util.DBUtil;
import javafx.util.Pair;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ReportDAO {

	public List<IssueRecord> getOverdueBooks() {
	    List<IssueRecord> list = new ArrayList<>();
	    String sql = "SELECT ir.BookId, b.Title, m.Name, ir.IssueDate, ir.ReturnDate " +
	                 "FROM issue_records ir " +
	                 "JOIN books b ON ir.BookId = b.BookId " +
	                 "JOIN members m ON ir.MemberId = m.MemberId " +
	                 "WHERE ir.Status = 'I' AND DATE_ADD(ir.IssueDate, INTERVAL 15 DAY) < CURDATE()";

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        while (rs.next()) {
	            IssueRecord ir = new IssueRecord();
	            ir.setBookId(rs.getInt("BookId"));
	            ir.setBookTitle(rs.getString("Title"));
	            ir.setMemberName(rs.getString("Name"));

	            Date returnDate = rs.getDate("ReturnDate");
	            if (returnDate != null) {
	                ir.setReturnDate(returnDate.toLocalDate());
	            } else {
	                LocalDate issueDate = rs.getDate("IssueDate").toLocalDate();
	                ir.setReturnDate(issueDate.plusDays(15)); // Estimated due date
	            }

	            list.add(ir);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}


    public List<Pair<String, Long>> getBooksCountPerCategory() {
        Map<String, Long> countMap = new LinkedHashMap<>();
        String sql = "SELECT Category, COUNT(*) AS Count FROM books GROUP BY Category";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                countMap.put(rs.getString("Category"), rs.getLong("Count"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Pair<String, Long>> list = new ArrayList<>();
        countMap.forEach((k, v) -> list.add(new Pair<>(k, v)));
        return list;
    }

    public List<Member> getMembersWithActiveIssuedBooks() {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT DISTINCT m.MemberId, m.Name, m.Email FROM members m " +
                     "JOIN issue_records ir ON m.MemberId = ir.MemberId " +
                     "WHERE ir.Status = 'I'";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("MemberId"));
                member.setName(rs.getString("Name"));
                member.setEmail(rs.getString("Email"));
                list.add(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

