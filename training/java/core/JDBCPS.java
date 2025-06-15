package package_train;

import java.sql.*;

import com.mysql.cj.jdbc.MysqlDataSource;

public class JDBCPS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MysqlDataSource new_source=new MysqlDataSource();
		new_source.setServerName("localhost");
		new_source.setPassword("Kanishka123#");
		new_source.setPort(3306);
		new_source.setUser("Kanishka");
		new_source.setDatabaseName("Kanishka");
		try(Connection conn=new_source.getConnection()){
			String query="SELECT * FROM Pets WHERE Pet_ID=?";
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setString(1,"1");
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				System.out.printf("%s %s %s %s %d %d %n",res.getString(1),res.getString(2),
						res.getString(3),res.getString(4),res.getInt(5),res.getInt(6));
			}
			ps.setString(1, "2");
			ResultSet res_2=ps.executeQuery();
			while(res_2.next()) {
				System.out.printf("%s %s %s %s %d %d %n",res_2.getString(1),res_2.getString(2),
						res_2.getString(3),res_2.getString(4),res_2.getInt(5),res_2.getInt(6));
			}

			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
