package day13;

import java.sql.*;

public class CrudDemo {

	public static void main(String[] args) {
		String url= "jdbc:mysql://localhost:3306/mydb";
		String username="devuser1";
		String password="Kaushik@8946";

		try {
			Connection conn=DriverManager.getConnection(url, username, password);
			Statement st=conn.createStatement();
//			create(st);
//			insert(st);
//			read(st);
//			update(st);
			read(st);
			delete(st);
			read(st);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void create(Statement st) {
		String query = "CREATE TABLE student ("
				+ "id INT PRIMARY KEY,"
				+ "name VARCHAR(30)"
				+ ");";
		try {
			st.execute(query);
			System.out.println("creating done");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void insert(Statement st) {
		String query = "INSERT INTO student (id,name) VALUES"
				+ "(101,'rohit'),"
				+ "(102,'virat'),"
				+ "(103,'dhoni');";
		try {
			int result=st.executeUpdate(query);
			System.out.println(result+" rows affected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void read(Statement st) {
		String query="SELECT * FROM student";
		try {
			ResultSet rs=st.executeQuery(query);
			System.out.printf("%-10s %-10s\n", "id","name");
			while(rs.next()) {
				String id=rs.getString("id");
				String name=rs.getString("name");
				System.out.printf("%-10s %-10s\n", id,name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void update(Statement st) {
		String query="UPDATE student "
				+ "SET name='kohli' "
				+ "WHERE name='virat';";
		try {
			int result=st.executeUpdate(query);
			System.out.println(result+" rows affected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void delete(Statement st) {
		String query="DELETE FROM student "
				+ "WHERE name='kohli';";
		try {
			int result=st.executeUpdate(query);
			System.out.println(result+" rows affected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
