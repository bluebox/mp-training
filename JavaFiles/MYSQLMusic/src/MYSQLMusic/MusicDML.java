package MYSQLMusic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class MusicDML {

	private final static String CONN_STRING ="jdbc:mysql://localhost:3306/music";

	
	public static void main(String[] args) {
		

		try(Connection connection = DriverManager.getConnection(
				CONN_STRING,"devuser","211Fa@4223"
				);
				
				Statement statement = connection.createStatement();
				){
			
			System.out.println("DB Connected");
			
			String tableName = "music.artists";
            String columnName = "artist_name";
            String columnValue = "Bob Dylan";
            if (!executeSelect(statement, tableName, columnName, columnValue)) {
//                insertArtistAlbum(statement, columnValue, columnValue);
            } else {
//                deleteRecord(statement, tableName, columnName, columnValue);
//                updateRecord(statement, tableName, columnName,
//                        columnValue, columnName,
//                        columnValue.toUpperCase());
            	try {
            		deleteArtistAlbum(connection,statement,columnValue, columnValue);
            	}catch(SQLException e) {
            		e.printStackTrace();
            	}
            	executeSelect(statement,"music.albumview","album_name",columnValue);
            	executeSelect(statement,"music.albums","album_name",columnValue);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean printRecords(ResultSet resultSet) throws SQLException {

        boolean foundData = false;
        var meta = resultSet.getMetaData();

        System.out.println("===================");

        for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
        }
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", resultSet.getString(i));
            }
            System.out.println();
            foundData = true;
        }
        return foundData;
    }

    private static boolean executeSelect(Statement statement, String table,
                                         String columnName, String columnValue)
            throws SQLException {

        String query = "SELECT * FROM %s WHERE %s='%s'"
                .formatted(table, columnName, columnValue);
        var rs = statement.executeQuery(query);
        if (rs != null) {
            return printRecords(rs);
        }
        return false;
    }

    private static boolean insertRecord(Statement statement, String table,
                                        String[] columnNames, String[] columnValues)
            throws SQLException {

        String colNames = String.join(",", columnNames);
        String colValues = String.join("','", columnValues);
        String query = "INSERT INTO %s (%s) VALUES ('%s')"
                .formatted(table, colNames, colValues);
        System.out.println(query);
        boolean insertResult = statement.execute(query);
        int recordsInserted = statement.getUpdateCount();
        if (recordsInserted > 0) {
            executeSelect(statement, table,
                    columnNames[0], columnValues[0]);
        }
        return recordsInserted > 0;
    }

    private static boolean deleteRecord(Statement statement, String table,
                                        String columnName, String columnValue)
            throws SQLException {

        String query = "DELETE FROM %s WHERE %s='%s'"
                .formatted(table, columnName, columnValue);
        System.out.println(query);
        statement.execute(query);
        int recordsDeleted = statement.getUpdateCount();
        if (recordsDeleted > 0) {
            executeSelect(statement, table,
                    columnName, columnValue);
        }
        return recordsDeleted > 0;
    }

    private static boolean updateRecord(Statement statement, String table,
                                        String matchedColumn, String matchedValue,
                                        String updatedColumn, String updatedValue)
            throws SQLException {

        String query = "UPDATE %s SET %s = '%s' WHERE %s='%s'"
                .formatted(table, updatedColumn, updatedValue, matchedColumn,
                        matchedValue);
        System.out.println(query);
        statement.execute(query);
        int recordsUpdated = statement.getUpdateCount();
        if (recordsUpdated > 0) {
            executeSelect(statement, table,
                    updatedColumn, updatedValue);
        }
        return recordsUpdated > 0;
    }

        
    private static void deleteArtistAlbum(Connection conn, Statement statement,
    		String artistName, String albumName) throws SQLException{
    	
    	System.out.println("AUTOCOMMIT = "+ conn.getAutoCommit());
    }

}
