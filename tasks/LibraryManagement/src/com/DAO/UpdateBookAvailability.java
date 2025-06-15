package com.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateBookAvailability {

	public boolean updateBookAvailabiility(Connection connection, int bookId, String availability) throws SQLException {
		boolean flag = false;

		String queryString = " update books set Availablity = ? where BookId = ? ";
		try (PreparedStatement statement = connection.prepareStatement(queryString);) {

			statement.setString(1, availability);
			statement.setInt(2, bookId);
			flag = statement.execute();

		}

		return false;
	}

	public boolean updateAvailability(Connection connection, int bookId, int memberId) throws SQLException {

		ResultSet flag = null;
		int flagint;
		String queryString = "select * from issue_records where BookId= ? and MemberId=?";

		try (PreparedStatement statement = connection.prepareStatement(queryString)) {
			statement.setInt(1, bookId);
			statement.setInt(2, memberId);
			flag = statement.executeQuery();
			if (flag.next()) {

				PreparedStatement insertStatement = connection
						.prepareStatement("inset into issue_records_log values(?,?,?,?,?,?)");
				insertStatement.setInt(1, flag.getInt(1));
				insertStatement.setInt(2, flag.getInt(2));
				insertStatement.setInt(3, flag.getInt(3));
				insertStatement.setString(4, queryString);
				insertStatement.setDate(5, flag.getDate(5));
				insertStatement.setDate(6, flag.getDate(6));
				insertStatement.execute();

				PreparedStatement updateStatement = connection
						.prepareStatement("update issue_records where BookId= ? and MemberId=? set ReturnDate=?");

				updateStatement.setInt(1, bookId);
				updateStatement.setInt(2, memberId);
				updateStatement.setDate(3, Date.valueOf(LocalDate.now()));
				flagint = updateStatement.executeUpdate();

			} else {
				PreparedStatement insertStatement = connection
						.prepareStatement("insert issue_records values(?,?,?,?,?)");

				insertStatement.setInt(1, bookId);
				insertStatement.setInt(2, flag.getInt(2));
				insertStatement.setInt(3, flag.getInt(3));
				insertStatement.setString(4, queryString);
				insertStatement.setDate(5, flag.getDate(5));
				insertStatement.setDate(6, flag.getDate(6));
				insertStatement.execute();
			}
		}

	}
}
