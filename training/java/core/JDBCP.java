package package_train;

import java.sql.*;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.Scanner;
public class JDBCP {
	public static void Rollback(Connection conn) throws SQLException {
		conn.rollback();
		
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		MysqlDataSource new_s=new MysqlDataSource();

		
		new_s.setUser(System.getenv("User_Name"));
		new_s.setPassword(System.getenv("Password"));
		new_s.setServerName("localhost");
		new_s.setPort(Integer.parseInt(System.getenv("Port")));
		new_s.setDatabaseName(System.getenv("Database"));
		
		
		try(Connection conn=new_s.getConnection()){
			conn.setAutoCommit(false);
			String sql_query=String.format("Insert into Pets("
					+ "Pet_ID,Name,Kind,Gender,Age,Owner_ID)"
					+ " values ('%s','%s','%s','%s',%d,%d)","7","Uday","pamerian","M",22,22);
			Statement new_statement=conn.createStatement();
			
			try {
			boolean res=new_statement.execute(sql_query,Statement.RETURN_GENERATED_KEYS);
			System.out.println(res);

			ResultSet ans=new_statement.executeQuery("SELECT * from Pets");
			while(ans.next()) {
				System.out.printf("%s %s %s %s %d %d",ans.getString(1)
						, ans.getString(2), ans.getString(3), ans.getString(4), ans.getInt(5),ans.getInt(6));

			}

			boolean res_2=new_statement.execute(sql_query,Statement.RETURN_GENERATED_KEYS);
			}
			catch (SQLException e) {
				try {
				Rollback(conn);
				ResultSet ans_2=new_statement.executeQuery("SELECT * from Pets");
				while(ans_2.next()) {
					System.out.printf("%s %s %s %s %d %d",ans_2.getString(1)
							, ans_2.getString(2), ans_2.getString(3), ans_2.getString(4), ans_2.getInt(5),ans_2.getInt(6));

				}
				e.printStackTrace();}
				catch(Exception e_2) {
					e.printStackTrace();
				}
			


			}

	}

}}
