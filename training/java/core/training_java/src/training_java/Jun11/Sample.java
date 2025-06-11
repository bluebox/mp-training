package javaDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

class Sample {
	public static void main(String[] args) throws Exception {
		String url = "jdbc:mysql://localhost:3306/music";
		String username = "devuser";
		String password = "Medplus@123";
		String query = "select * from song";// Read using statement
		String query1 = "Insert into song values(?,?,?,?)";// Create using Prepared statement
		String query2 = "Update song set id=? where name=?";// Update using Prepared statement
		String query3 = "Delete from song where name=?";// Delete using Prepared statement
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established successfully");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData meta = rs.getMetaData();
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				System.out.printf("%-15s ", meta.getColumnName(i));
			}
			System.out.println();
			PreparedStatement ps = con.prepareStatement(query1);
			ps.setInt(1, 38);
			ps.setInt(2, 1);
			ps.setString(3, "hahah");
			ps.setDouble(4, 3.58);
			int count = ps.executeUpdate();
			System.out.println(count);
			PreparedStatement ps2 = con.prepareStatement(query2);
			ps2.setInt(1, 39);
			ps2.setString(2, "hahah");
			ps2.executeUpdate();
			PreparedStatement ps3 = con.prepareStatement(query3);
			ps3.setString(1, "hahah");
//	 ps3.executeUpdate();
			while (rs.next()) {
				System.out.println(rs.getInt("id") + " " + rs.getInt("album_id") + " " + rs.getString("name") + " "
						+ rs.getDouble("duration"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Connection Closed....");
	}
}