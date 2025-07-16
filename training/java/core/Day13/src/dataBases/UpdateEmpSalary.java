package dataBases;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.jdbc.MysqlDataSource;

public class UpdateEmpSalary {
	public static void main(String[] args) {
		MysqlDataSource source=new MysqlDataSource();
		source.setUrl("jdbc:mysql://localhost:3306/example");
		source.setUser("root");
		source.setPassword("root");
		
		try {
			Connection conn=source.getConnection();
			CallableStatement cs=(CallableStatement) conn.prepareCall("{call updateEmpSalary(?,?)}");
			cs.setInt(1,101);
            cs.setDouble(2,0.2);
            cs.executeUpdate();
            conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
