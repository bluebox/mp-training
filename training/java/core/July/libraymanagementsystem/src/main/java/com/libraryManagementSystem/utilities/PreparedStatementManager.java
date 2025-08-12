package com.libraryManagementSystem.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.StatementPreparationException;

public class PreparedStatementManager {

	private PreparedStatementManager() {
	}

	private static final Map<String, PreparedStatement> preparedStatementCache = new HashMap<>();

	public static PreparedStatement getPreparedStatement(String query)
			throws DatabaseConnectionException, StatementPreparationException, SQLException {

		if (preparedStatementCache.get(query) != null && preparedStatementCache.get(query).isClosed()) {
			preparedStatementCache.remove(query);
		}

		if (!preparedStatementCache.containsKey(query)) {
			Connection conn;
			try {
				conn = DBConnection.getConnection();
			} catch (Exception e) {
				throw new DatabaseConnectionException(
						"Database connection failed. Please check credentials or server status.", e);
			}

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				preparedStatementCache.put(query, stmt);
			} catch (SQLException e) {
				throw new StatementPreparationException("Failed to prepare statement for query: " + query, e);
			}
		}

		return preparedStatementCache.get(query);
	}

	public static void closeAllStatements() {
		for (PreparedStatement stmt : preparedStatementCache.values()) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error closing statement: " + e.getMessage());
			}
		}
		preparedStatementCache.clear();
	}
}
