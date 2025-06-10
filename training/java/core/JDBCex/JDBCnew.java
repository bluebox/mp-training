package JDBCex;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.*;
import java.util.Properties;
import java.io.*;
public class JDBCnew {
    public static void main(String[] args) {
    	Properties properties = new Properties(); 
    	String DB_SERVER_NAME = null;
        int DB_PORT = 0;
        String DB_NAME = null;
        String DB_USER = null;
        String DB_PASSWORD  = null;
    	try (InputStream input = new FileInputStream("/home/developer/eclipse-workspace/prog1/src/JDBCex/db.properties")) {
            properties.load(input);
            DB_SERVER_NAME = properties.getProperty("db.server_name");
        	DB_PORT = Integer.parseInt(properties.getProperty("db.port"));
        	DB_NAME = properties.getProperty("db.name");
        	DB_USER = properties.getProperty("db.user");
        	DB_PASSWORD = System.getenv("DB_PASSWORD");
    	} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(DB_SERVER_NAME);
        dataSource.setPortNumber(DB_PORT); 
        dataSource.setDatabaseName(DB_NAME);
        dataSource.setUser(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        String query="SELECT * FROM music_details";
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();) {
        	ResultSet rs = statement.executeQuery(query);
        	ResultSetMetaData metaData=rs.getMetaData();
   			int columnCount=metaData.getColumnCount();
   			for(int i=1;i<=columnCount;i++) {
   				System.out.printf("%-20s",metaData.getColumnName(i));
   			}
   			System.out.println();
   			System.out.println("-".repeat(20*4));
   			while(rs.next()) {
   				for(int i=1;i<=columnCount;i++) {
   					String columnValue=rs.getString(i);
   					System.out.printf("%-20s",columnValue);
   				}
   				System.out.println();
   			}

           } catch (SQLException e) {
               e.printStackTrace();
           }
    }
}