package Utilities;

import java.sql.*;

public class DBUtil {
	private static Connection conn;
	private static Statement statement;

	public static Connection getConnection() throws SQLException {
		if (conn == null||conn.isClosed()) {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "devuser", "M@Neesh123");
				conn.setAutoCommit(false);
				statement = conn.createStatement();
				final String bookTableCreation = "CREATE TABLE IF NOT EXISTS books ("
						+ "    BookId INT PRIMARY KEY AUTO_INCREMENT," + "    Title VARCHAR(255) NOT NULL UNIQUE,"
						+ "    Author VARCHAR(255) NOT NULL," + "    Category VARCHAR(100) NOT NULL,"
						+ "    Status CHAR(1) NOT NULL CHECK (Status IN ('A', 'I')),"
						+ "    Availability CHAR(1) NOT NULL CHECK (Availability IN ('A', 'I'))" + ");";
				final String memberTableCreation = "CREATE TABLE IF NOT EXISTS members ("
						+ "    MemberId INT PRIMARY KEY AUTO_INCREMENT," + "    Name VARCHAR(255) NOT NULL,"
						+ "    Email VARCHAR(255) NOT NULL UNIQUE," + "    Mobile BIGINT NOT NULL UNIQUE,"
						+ "    Gender CHAR(1) NOT NULL CHECK (Gender IN ('M', 'F')),"
						+ "    Address VARCHAR(255) NOT NULL" + ");";
				final String issueRecordTable = "CREATE TABLE IF NOT EXISTS issue_records ("
						+ "    IssueId INT PRIMARY KEY AUTO_INCREMENT," + "    BookId INT NOT NULL,"
						+ "    MemberId INT NOT NULL," + "    Status CHAR(1) NOT NULL CHECK (Status IN ('I', 'R')),"
						+ "    IssueDate DATE NOT NULL," + "    ReturnDate DATE,"
						+ "    FOREIGN KEY (BookId) REFERENCES books(BookId),"
						+ "    FOREIGN KEY (MemberId) REFERENCES members(MemberId)" + ");";
				final String bookLogCreation = "CREATE TABLE IF NOT EXISTS book_log (" + "    BookId INT,"
						+ "    Title VARCHAR(255)," + "    Author VARCHAR(255)," + "    Category VARCHAR(100),"
						+ "    Status CHAR(1)," + "    Availability CHAR(1)," + "    OperationType VARCHAR(50),"
						+ "    OperationTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ");";

				final String memberLogCreation = "CREATE TABLE  IF NOT EXISTS members_log (" + "    MemberId INT,"
						+ "    Name VARCHAR(255)," + "    Email VARCHAR(255)," + "    Mobile BIGINT,"
						+ "    Gender CHAR(1)," + "    Address VARCHAR(255)," + "    OperationType VARCHAR(50),"
						+ "    OperationTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ");";
				final String issueLogTable = "CREATE TABLE  IF NOT EXISTS issue_records_log (" + "    IssueId INT,"
						+ "    BookId INT," + "    MemberId INT," + "    Status CHAR(1)," + "    IssueDate DATE,"
						+ "    ReturnDate DATE,"
						+ "    OperationTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ");";

				statement.addBatch(bookTableCreation);
				statement.addBatch(memberTableCreation);
				statement.addBatch(issueRecordTable);
				statement.addBatch(bookLogCreation);
				statement.addBatch(memberLogCreation);
				statement.addBatch(issueLogTable);
				statement.executeBatch();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				conn.rollback();
			}
			if (statement != null) {
				statement.close();
			}
		}
		return conn;
	}
}