import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//String sql="select * from Student";
		String updatesql="update student set age=19 where age=20";
		String deletesql="delete from student where age=19";
		String url = "jdbc:mysql://localhost:3306/sreejadb1";
		String username="root";
		String password="Sreeja@03";
		Connection conn=DriverManager.getConnection(url,username,password);
		Statement st=conn.createStatement();
		//int rowsAffected = st.executeUpdate(updatesql);
		int rowsAffected=st.executeUpdate(deletesql);
		System.out.println(rowsAffected);
		//ResultSet rs=st.executeQuery(sql);
		ResultSet rs = st.executeQuery("SELECT * FROM student");
		while (rs.next()) {
		    System.out.println(rs.getString(1));
		}
		rs.close();
		st.close();
		conn.close();	
	}

}
