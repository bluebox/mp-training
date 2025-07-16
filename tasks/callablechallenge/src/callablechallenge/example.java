package callablechallenge;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class example {
	public static void main(String[] args) throws SQLException {
	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
    Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/callableexample", "root", "9490");
    CallableStatement c=con1.prepareCall("{call myprocedure(?,?,?)}");
    c.setString(1, "niha");
    c.setInt(2, 1000);
    c.setString(3, "sklm");
    

//    c.setString(1, "pavan");
//    c.setInt(2, 2000);
//    c.setString(3, "vizag");
//
//    c.setString(1, "kavi");
//    c.setInt(2, 3000);
//    c.setString(3, "vijd");
    c.execute();
    System.out.println("executed");

}
}
