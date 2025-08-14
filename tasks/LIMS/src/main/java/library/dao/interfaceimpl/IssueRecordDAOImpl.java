package library.dao.interfaceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import library.dao.interfaces.IssueRecordDAO;
import library.exception.LibraryException;
import library.model.IssueRecord;
import library.model.enums.IssueStatus;
import library.util.DBConnection;

public class IssueRecordDAOImpl implements IssueRecordDAO {

	@Override
	public void addIssueRecord(IssueRecord issueRecord) throws LibraryException {
		String sql = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate, issued_by, ReturnDate, returned_by) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			int paramIndex = 1;
			preparedStatement.setInt(paramIndex++, issueRecord.getBookId());
			preparedStatement.setInt(paramIndex++, issueRecord.getMemberId());
			preparedStatement.setString(paramIndex++, issueRecord.getStatus().getCode());
			preparedStatement.setTimestamp(paramIndex++, Timestamp.valueOf(issueRecord.getIssueDate()));
			preparedStatement.setString(paramIndex++, issueRecord.getIssuedBy());

			if (issueRecord.getReturnDate() != null) {
				preparedStatement.setTimestamp(paramIndex++, Timestamp.valueOf(issueRecord.getReturnDate()));
			} else {
				preparedStatement.setNull(paramIndex++, Types.TIMESTAMP);
			}
			if (issueRecord.getReturnedBy() != null) {
				preparedStatement.setString(paramIndex++, issueRecord.getReturnedBy());
			} else {
				preparedStatement.setNull(paramIndex++, Types.VARCHAR);
			}

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new LibraryException("Failed to add issue record: " + e.getMessage(), e);
		}
	}

	@Override
    public boolean updateIssueRecord(IssueRecord issueRecord) throws LibraryException {
        IssueRecord existingIssueRecord = getIssuedRecordById(issueRecord.getIssueId());
        if (existingIssueRecord == null) {
            return false;
        }

        String sql = "UPDATE issue_records SET BookId = ?, MemberId = ?, Status = ?, IssueDate = ?, "
        		+ "issued_by = ?, ReturnDate = ?, returned_by = ? WHERE IssueId = ?";
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            logIssueRecordChange(existingIssueRecord, connection);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                int paramIndex = 1;

                preparedStatement.setInt(paramIndex++, issueRecord.getBookId());
                preparedStatement.setInt(paramIndex++, issueRecord.getMemberId());
                preparedStatement.setString(paramIndex++, issueRecord.getStatus().getCode());
                preparedStatement.setTimestamp(paramIndex++, Timestamp.valueOf(issueRecord.getIssueDate()));
                preparedStatement.setString(paramIndex++, issueRecord.getIssuedBy());

                if (issueRecord.getReturnDate() != null) {
                    preparedStatement.setTimestamp(paramIndex++, Timestamp.valueOf(issueRecord.getReturnDate()));
                } else {
                    preparedStatement.setNull(paramIndex++, Types.TIMESTAMP);
                }
                if (issueRecord.getReturnedBy() != null) {
                    preparedStatement.setString(paramIndex++, issueRecord.getReturnedBy());
                } else {
                    preparedStatement.setNull(paramIndex++, Types.VARCHAR);
                }
                preparedStatement.setInt(paramIndex++, issueRecord.getIssueId());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }

            }
        }catch (Exception e) {
                if (connection != null) {
                    try { 
                    	connection.rollback();
                    } catch (SQLException rolle) {
                    	rolle.printStackTrace();
                    }
                }
                throw new LibraryException("Failed to update issue record: " + e.getMessage(), e);
            } finally {
                if (connection != null) {
                    try { 
                        connection.setAutoCommit(true);
                        connection.close();
                    } catch (SQLException e) { 
                    	e.printStackTrace();
                    }
                }
            }
        }

	@Override
	public List<IssueRecord> getAllIssuedRecords() throws LibraryException {
		List<IssueRecord> issueRecords = new ArrayList<>();
		String sql = "SELECT IssueId, BookId, MemberId, Status, IssueDate, issued_by, ReturnDate, returned_by FROM issue_records";
		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				IssueRecord record = new IssueRecord(resultSet.getInt("IssueId"), resultSet.getInt("BookId"),
						resultSet.getInt("MemberId"), IssueStatus.fromCode(resultSet.getString("Status")),
						resultSet.getTimestamp("IssueDate").toLocalDateTime(), resultSet.getString("issued_by"),
						resultSet.getTimestamp("ReturnDate") != null
								? resultSet.getTimestamp("ReturnDate").toLocalDateTime()
								: null,
						resultSet.getString("returned_by"));
				issueRecords.add(record);
			}
		} catch (Exception e) {
			throw new LibraryException("Failed to retrieve all issue records: " + e.getMessage(), e);
		}
		return issueRecords;
	}

	@Override
	public IssueRecord getIssuedRecordByIds(int bookId, int memberId) throws LibraryException {
		String sql = "SELECT IssueId, BookId, MemberId, Status, IssueDate, issued_by, ReturnDate, returned_by FROM issue_records"
				+ " WHERE BookId = ? AND MemberId = ? AND Status = 'I'";
		IssueRecord issueRecord = null;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			int paramIndex = 1;
			preparedStatement.setInt(paramIndex++, bookId);
			preparedStatement.setInt(paramIndex++, memberId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					issueRecord = new IssueRecord(resultSet.getInt("IssueId"), resultSet.getInt("BookId"),
							resultSet.getInt("MemberId"), IssueStatus.fromCode(resultSet.getString("Status")),
							resultSet.getTimestamp("IssueDate").toLocalDateTime(), resultSet.getString("issued_by"),
							resultSet.getTimestamp("ReturnDate") != null
									? resultSet.getTimestamp("ReturnDate").toLocalDateTime()
									: null,
							resultSet.getString("returned_by"));
				}
			}
		} catch (Exception e) {
			throw new LibraryException("Failed to retrieve issue record by IDs: " + e.getMessage(), e);
		}
		return issueRecord;
	}

	@Override
	public IssueRecord getActiveIssueRecordByBookId(int bookId) throws LibraryException {
		String sql = "SELECT IssueId, BookId, MemberId, Status, IssueDate, issued_by, ReturnDate, returned_by FROM issue_records "
				+ "WHERE BookId = ? AND Status = 'I' AND ReturnDate IS NULL";
		IssueRecord issueRecord = null;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			int paramIndex = 1;
			preparedStatement.setInt(paramIndex++, bookId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					issueRecord = new IssueRecord(resultSet.getInt("IssueId"), resultSet.getInt("BookId"),
							resultSet.getInt("MemberId"), IssueStatus.fromCode(resultSet.getString("Status")),
							resultSet.getTimestamp("IssueDate").toLocalDateTime(), resultSet.getString("issued_by"),
							null, null);
				}
			}
		} catch (Exception e) {
			throw new LibraryException("Failed to retrieve active issue record by Book ID: " + e.getMessage(),e);
		}
		return issueRecord;
	}

	private IssueRecord getIssuedRecordById(int issueId) throws LibraryException {
		String sql = "SELECT IssueId, BookId, MemberId, Status, IssueDate, issued_by, ReturnDate, returned_by FROM issue_records "
				+ "WHERE IssueId = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			int paramIndex = 1;
			preparedStatement.setInt(paramIndex++, issueId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return new IssueRecord(resultSet.getInt("IssueId"), resultSet.getInt("BookId"),
							resultSet.getInt("MemberId"), IssueStatus.fromCode(resultSet.getString("Status")),
							resultSet.getTimestamp("IssueDate").toLocalDateTime(), resultSet.getString("issued_by"),
							resultSet.getTimestamp("ReturnDate") != null
									? resultSet.getTimestamp("ReturnDate").toLocalDateTime()
									: null,
							resultSet.getString("returned_by"));
				}
			}
		} catch (Exception e) {
			throw new LibraryException("Failed to retrieve issue record for internal use: " + e.getMessage(),e);
		}
		return null;
	}

	private void logIssueRecordChange(IssueRecord issueRecord, Connection connection) throws SQLException {
		String sql = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, issued_by, ReturnDate, returned_by, LogDate) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			int paramIndex = 1;
			preparedStatement.setInt(paramIndex++, issueRecord.getIssueId());
			preparedStatement.setInt(paramIndex++, issueRecord.getBookId());
			preparedStatement.setInt(paramIndex++, issueRecord.getMemberId());
			preparedStatement.setString(paramIndex++, issueRecord.getStatus().getCode());
			preparedStatement.setTimestamp(paramIndex++, Timestamp.valueOf(issueRecord.getIssueDate()));
			preparedStatement.setString(paramIndex++, issueRecord.getIssuedBy());
			if (issueRecord.getReturnDate() != null) {
				preparedStatement.setTimestamp(paramIndex++, Timestamp.valueOf(issueRecord.getReturnDate()));
			} else {
				preparedStatement.setNull(paramIndex++, Types.TIMESTAMP);
			}
			if (issueRecord.getReturnedBy() != null) {
				preparedStatement.setString(paramIndex++, issueRecord.getReturnedBy());
			} else {
				preparedStatement.setNull(paramIndex++, Types.VARCHAR);
			}
			preparedStatement.setTimestamp(paramIndex++, Timestamp.valueOf(LocalDateTime.now())); 
			preparedStatement.executeUpdate();
		}
	}
}