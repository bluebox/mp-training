package CrudOperations;

import java.sql.*;

public class ReadData {

	public static void main(String[] args) throws Exception {

		String url = "jdbc:mysql://localhost:3306/student";
		String uname = "root";
		String passWord = "root";
		// String query="SELECT EMPNAME FROM EMPLOYE WHERE EMPID=2;";
		String query1 = "select * from employe";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, uname, passWord);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query1);
		
		String userData=" ";
		while(rs.next()) {
			userData=rs.getInt(1)+":"+rs.getString(2);
			System.out.println(userData);
		}

		st.close();
		con.close();
	}

}
