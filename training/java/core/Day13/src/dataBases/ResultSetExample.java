package dataBases;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ResultSetExample {
	static final String  url="jdbc:mysql://localhost:3306/example"; 
	static final String user="root";
	static final String pass="root";

	public static void main(String[] args) {
		
		MysqlDataSource source=new MysqlDataSource();
		source.setURL(url);
		source.setUser(user);
		source.setPassword(pass);
		try (Connection conn=source.getConnection();){
			String str="Select * from orders";
			Statement statement=conn.createStatement();
			java.sql.ResultSet result=statement.executeQuery(str);
			while(result.next()) {
				int id=result.getInt("orderId");
				String date=result.getString("orderdate");
				int quantity =result.getInt("quant");
				System.out.println("order id : "+id+" date : "+" quantity : "+quantity);
		}
	}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
