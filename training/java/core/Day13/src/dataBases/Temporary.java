package dataBases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Temporary {
	public static void main(String[] args) {
		MysqlDataSource ds=new MysqlDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/example");
		ds.setUser("root");
		ds.setPassword("root");
		
		try(Connection conn=ds.getConnection()){
			
			PreparedStatement insertion=conn.prepareStatement("insert into temp1 (name ,gender,age) values(?,?,?)");
			Statement add=conn.createStatement();
			ResultSet result= add.executeQuery("select temp1.id, temp1.name ,temp1.gender , temp1.age , temp2.name,temp2.duration,"
					+ "temp2.cost from temp1  join  temp2 on temp1.plan_id=temp2.plan_id");
			while(result.next()) {
				System.out.println(result.getInt("id"));
				System.out.println(result.getString("name"));
				System.out.println(result.getString("gender"));		
				System.out.println(result.getString("temp2.name"));
				System.out.println(result.getString("duration"));
				System.out.println(result.getDouble("cost"));
				System.out.println(result.getInt("age"));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
