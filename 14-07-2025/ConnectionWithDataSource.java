package JdbcConn;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectionWithDataSource {
	
    public static void main(String[] args) {
    	
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        dataSource.setDatabaseName("mydatabase");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        try (Connection conn = dataSource.getConnection()) {
        	
        	Statement state=conn.createStatement();
        	
        	String query="create table student(id int,name varchar(20))";
        	
        	state.execute(query);
        	
        	
            System.out.println("Connection successful!");
            state.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
