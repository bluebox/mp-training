package dao;

import domain.IssueRecord;
import domain.Member;
import exceptions.ManagementException;
import util.DBUtil;
import java.sql.*;
import java.util.*;

public class ReportDao implements ReportDaoInterface {

	public List<IssueRecord> getOverdueBooks() {
		List<IssueRecord> list = new ArrayList<>();
		String sql = "SELECT ir.BookId, b.Title, m.Name, ir.IssueDate, ir.ReturnDate " + "FROM issue_record ir "
				+ "JOIN books b ON ir.BookId = b.BookId " + "JOIN members m ON ir.MemberId = m.MemberId "
				+ "WHERE ir.Status = 'I' AND DATE_ADD(ir.IssueDate, INTERVAL 15 DAY) < NOW()";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				IssueRecord ir = new IssueRecord();
				ir.setBookId(rs.getInt("BookId"));
				ir.setBookTitle(rs.getString("Title"));
				ir.setMemberName(rs.getString("Name"));

				Timestamp returnTimestamp = rs.getTimestamp("ReturnDate");
				if (returnTimestamp != null) {
					ir.setReturnDate(returnTimestamp.toLocalDateTime());
				} else {
					Timestamp issueTimestamp = rs.getTimestamp("IssueDate");
					if (issueTimestamp != null) {
						ir.setReturnDate(issueTimestamp.toLocalDateTime().plusDays(15));
					}
				}
				list.add(ir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Map<String, Long> getBooksCountPerCategory() throws ManagementException {
		Map<String, Long> countMap = new LinkedHashMap<>();
		String sql = "SELECT Category, COUNT(*) AS Count FROM books GROUP BY Category";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				countMap.put(rs.getString("Category"), rs.getLong("Count"));
			}
		} catch (Exception e) {
			throw new ManagementException("Error fetching books per category", e);
		}

		return countMap;
	}

	public List<Member> getMembersWithActiveIssuedBooks() {
		List<Member> list = new ArrayList<>();
		String sql = "SELECT DISTINCT m.MemberId, m.Name, m.Email FROM members m "
				+ "JOIN issue_record ir ON m.MemberId = ir.MemberId " + "WHERE ir.Status = 'I'";
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
