package daoimpl;

import model.Member;
import model.MemberIssueDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Exception.DatabaseException;
import dao.MemberDao;

public class MemberDAOImpl implements MemberDao{

	public void addMember(Member member) throws DatabaseException {
		String insert = "INSERT INTO members (name, Email, mobile, gender, address) VALUES (?, ?, ?, ?, ?)";

		Connection conn = null;
		try {
			conn = JDBCConnection.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setLong(3, member.getMobile());
			stmt.setString(4, String.valueOf(member.getGender()));
			stmt.setString(5, member.getAddress());
			stmt.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			throw new DatabaseException("Error adding member", e);
		} finally {
			if (conn != null)
				try {
					conn.close();
					throw new Exception("Error in closing connection");
				} catch (Exception ignore) {
					System.err.println(ignore.getMessage());
				}
		}
	}

	public void updateMember(Member member) throws Exception {
		String update = "UPDATE members SET name=?, email=?, mobile=?, gender=?, address=? WHERE memberId=?";
		String insertLog = "INSERT INTO members_log (memberId, name, email, mobile, gender, address) VALUES (?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement logStmt = null;
		PreparedStatement stmt = null;
		Member existingMember = null;

		try {
			conn = JDBCConnection.getConnection();
			conn.setAutoCommit(false);

			existingMember = getMemberById(member.getMemberId());
			if (existingMember == null) {
				throw new Exception("Member not found");
			}

			if (isEqual(member, existingMember)) {
				throw new Exception("No change in values");
			}

			logStmt = conn.prepareStatement(insertLog);
			logStmt.setInt(1, existingMember.getMemberId());
			logStmt.setString(2, existingMember.getName());
			logStmt.setString(3, existingMember.getEmail());
			logStmt.setLong(4, existingMember.getMobile());
			logStmt.setString(5, String.valueOf(existingMember.getGender()));
			logStmt.setString(6, existingMember.getAddress());
			logStmt.executeUpdate();

			stmt = conn.prepareStatement(update);
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setLong(3, member.getMobile());
			stmt.setString(4, String.valueOf(member.getGender()));
			stmt.setString(5, member.getAddress());
			stmt.setInt(6, member.getMemberId());
			stmt.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (Exception rollbackEx) {
					throw new Exception("Error during rollback: " + rollbackEx.getMessage(), rollbackEx);
				}
			}
			throw new Exception("Error updating member: " + e.getMessage(), e);
		} finally {
			try {
				if (logStmt != null)
					logStmt.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				throw new Exception("Error closing resources: " + e.getMessage(), e);
			}
		}
	}

	private boolean isEqual(Member member, Member newMember) {
		return (member.getName().equals(newMember.getName()) || member.getEmail().equals(newMember.getEmail()));
	}

	public List<Member> getAllMembers() throws Exception {
		List<Member> members = new ArrayList<>();
		String query = "SELECT memberId, name, email, mobile, gender, address FROM members";
		try (Connection conn = JDBCConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				members.add(new Member(rs.getInt("memberId"), rs.getString("name"), rs.getString("email"),
						rs.getLong("mobile"), rs.getString("gender").charAt(0), rs.getString("address")));
			}
		}
		return members;
	}

	public Member getMemberById(int memberId) throws Exception {
		String query = "SELECT memberId, name, email, mobile, gender, address FROM members where memberId=?";
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, Integer.toString(memberId));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Member(rs.getInt("memberId"), rs.getString("name"), rs.getString("email"),
						rs.getLong("mobile"), rs.getString("gender").charAt(0), rs.getString("address"));
			}
		}
		return null;
	}

//	public List<MemberIssueDTO> getMembersWithActiveIssueBooks() throws SQLException {
//	    String query = """
//	        SELECT m.MemberId, m.Name AS MemberName, m.Mobile, m.Address,
//	               b.BookId, b.Title AS BookName, ir.IssueDate
//	        FROM members m
//	        JOIN issue_records ir ON m.MemberId = ir.MemberId
//	        JOIN books b ON b.BookId = ir.BookId
//	        WHERE ir.Status = 'I'
//	        """;
//	    Connection conn = null;
//	    try {
//	    	conn = JDBCConnection.getConnection();
//	    	Statement stmt = conn.createStatement(); 
//	    	ResultSet rs = stmt.executeQuery(query);
//	    	final ResultSet finalRs = rs;
//
//	        Iterable<ResultSet> iterable = () -> new Iterator<>() {
//	            private boolean hasNext = advance();
//
//	            private boolean advance() {
//	                try {
//	                    return finalRs.next();
//	                } catch (SQLException e) {
//	                    throw new RuntimeException("Error advancing ResultSet", e);
//	                }
//	            }
//
//	            @Override
//	            public boolean hasNext() {
//	                return hasNext;
//	            }
//
//	            @Override
//	            public ResultSet next() {
//	                ResultSet current = finalRs;
//	                hasNext = advance();
//	                return current;
//	            }
//	        };
//
//	        return StreamSupport.stream(iterable.spliterator(), false)
//	            .map(r -> {
//	                try {
//	                    return new MemberIssueDTO(
//	                        r.getInt("MemberId"),
//	                        r.getString("MemberName"),
//	                        r.getInt("BookId"),
//	                        r.getString("BookName"),
//	                        r.getLong("Mobile"),
//	                        r.getString("Address"),
//	                        r.getDate("IssueDate").toLocalDate()
//	                    );
//	                } catch (SQLException e) {
//	                    throw new RuntimeException("Error mapping ResultSet to DTO", e);
//	                }
//	            })
//	            .collect(Collectors.toList());
//
//	    } catch (SQLException e) {
//	        System.out.println("Error in fetching data: " + e.getMessage());
//	        return Collections.emptyList();
//	    }
//	}
//
//}

	public List<MemberIssueDTO> getMembersWithActiveIssueBooks() {
		String query = """
				SELECT m.MemberId, m.Name AS MemberName, m.Mobile, m.Address,
				       b.BookId, b.Title AS BookName, ir.IssueDate
				FROM members m
				JOIN issue_records ir ON m.MemberId = ir.MemberId
				JOIN books b ON b.BookId = ir.BookId
				WHERE ir.Status = 'I'
				""";

		try {
			Connection conn = JDBCConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			Iterable<ResultSet> iterable = () -> new Iterator<>() {
				boolean advanced = false;
				boolean hasNext;

				@Override
				public boolean hasNext() {
					if (!advanced) {
						try {
							hasNext = rs.next();
						} catch (SQLException e) {
							throw new RuntimeException(e);
						}
						advanced = true;
					}
					return hasNext;
				}

				@Override
				public ResultSet next() {
					if (!advanced) {
						hasNext();
					}
					if (!hasNext) {
						throw new NoSuchElementException();
					}
					advanced = false;
					return rs;
				}
			};

			return StreamSupport.stream(iterable.spliterator(), false).map(r -> {
				try {
					return new MemberIssueDTO(r.getInt("MemberId"), r.getString("MemberName"), r.getInt("BookId"),
							r.getString("BookName"), r.getLong("Mobile"), r.getString("Address"),
							r.getDate("IssueDate").toLocalDate());
				} catch (SQLException e) {
					throw new RuntimeException("Error mapping ResultSet to DTO", e);
				}
			}).collect(Collectors.toList());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
}
