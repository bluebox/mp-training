package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc1 {

	private final String userName = "root";
	private final String password = "root";
	private final String serverName = "localhost";
	private final int portNumber = 3306;
	private final String dbName = "storefront";
	private final String tableName = "Order_Details";
	
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		connection = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return connection;
	}

	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement statement = null;
	    try {
	        statement = conn.createStatement();
	        statement.executeUpdate(command); 
	        return true;
	    } finally {

	        if (statement != null) { statement.close(); }
	    }
	}
	public void run() {

		Connection connection1 = null;
		try {
			connection1 = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
//		 Drop the table if already exists
			try {
			    String dropCommand = "DROP TABLE " + this.tableName;
				this.executeUpdate(connection1, dropCommand);
				System.out.println("Dropped the table "+this.tableName);
		    } catch (SQLException e) {
				System.out.println("Need to create the table");
				//e.printStackTrace();
				//return;
			}
		
		//create a table
		try {
		    String createCommand =
			        "CREATE TABLE " + this.tableName + " ( " +
			        "ORDER_ID INTEGER NOT NULL, " +
			        "CUSTOMER_NAME varchar(40) NOT NULL, " +
			        "STREET varchar(40) NOT NULL, " +
			        "CITY varchar(30) NOT NULL, " +
			        "STATE varchar(30) NOT NULL, " +
			        "ZIP char(6), " +
			        "ORDER_DATE Date NOT NULL,"+
			        "PRIMARY KEY (ORDER_ID))";
			this.executeUpdate(connection1, createCommand);
			System.out.println("Created a table "+this.tableName);
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
		//insert values into a table
		try {
			//order1
		    String insertCommand1 ="INSERT INTO "+this.tableName+ " VALUES (1,'Ravi','4th street','Hyderabad','Telangana','500001','2025-03-01')";
			this.executeUpdate(connection1, insertCommand1);
			System.out.println("Inserted order1 details into a table");
			
			//order2
			String insertCommand2 ="INSERT INTO "+this.tableName+ " VALUES (2,'Ravi2','5th street','Warangal','Telangana','502001','2025-04-09')";
			this.executeUpdate(connection1, insertCommand2);
			System.out.println("Inserted order2 details into a table");
			
			//order3
			String insertCommand3 ="INSERT INTO "+this.tableName+ " VALUES (3,'Ravi3','9th street','Chennai','Tamilnadu','600001','2025-02-06')";
			this.executeUpdate(connection1, insertCommand3);
			System.out.println("Inserted order3 details into a table");
			
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
		
		try {
			String deleteCommand ="DELETE FROM "+this.tableName+" WHERE ORDER_ID=3";
			this.executeUpdate(connection1, deleteCommand);
			System.out.println("Deleted order3 details from the table");
			
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
		
	//	 Drop the table
//		try {
//		    String dropCommand = "DROP TABLE " + this.tableName;
//			this.executeUpdate(connection1, dropCommand);
//			System.out.println("Dropped the table");
//	    } catch (SQLException e) {
//			System.out.println("ERROR: Could not drop the table");
//			e.printStackTrace();
//			return;
//		}
	}
	
	
	public static void main(String[] args) {
		Jdbc1 challenge1 = new Jdbc1();
		challenge1.run();
	}
}