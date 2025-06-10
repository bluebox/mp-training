package JDBC_Challenges;

import java.sql.*;

public class Parameters {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/training";

		final String USER = "root";
		final String PASS = "Training@1";

		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connection Established successfully");

		System.out.println("Select statement with param");
		PreparedStatement statement = conn.prepareStatement("select* from users where place=?");
		statement.setString(1, "goa");
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			String name = rs.getString("name");
			String place = rs.getString("place");
			System.out.println(name + " " + place);
		}
		PreparedStatement all = conn.prepareStatement("select* from users");
		System.out.println();

		System.out.println("Insert statement excution");
		PreparedStatement insert = conn.prepareStatement("insert into users values(?,?)");
		insert.setString(1, "krishna");
		insert.setString(2, "chennei");
		int rs2 = insert.executeUpdate();
		System.out.println("I just inserted " + insert + " users");
		ResultSet allRecords = all.executeQuery();

		while (allRecords.next()) {
			String name = allRecords.getString("name");
			String place = allRecords.getString("place");
			System.out.println(name + " " + place);

		}
		System.out.println();

		System.out.println("Update statement excution");

		PreparedStatement update = conn.prepareStatement("update users set name='bheem' where place=?");
		update.setString(1, "delhi");
		int updated = update.executeUpdate();
		System.out.println("I just updated " + updated + " users");
		ResultSet allRecords2 = all.executeQuery();

		while (allRecords2.next()) {
			String name = allRecords2.getString("name");
			String place = allRecords2.getString("place");
			System.out.println(name + " " + place);

		}
		System.out.println();

		//delete
		PreparedStatement delete=conn.prepareStatement("delete from users where place=?");
		delete.setString(1, "chennei");
		delete.executeUpdate();
		ResultSet allRecords3 = all.executeQuery();

		while (allRecords3.next()) {
			String name = allRecords3.getString("name");
			String place = allRecords3.getString("place");
			System.out.println(name + " " + place);

		}
		conn.close();
		System.out.println("Connection close");
	}
}
