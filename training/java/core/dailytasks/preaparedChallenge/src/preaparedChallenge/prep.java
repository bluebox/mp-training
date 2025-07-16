package preaparedChallenge;
import java.lang.String;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException; 

import static java.lang.Integer.parseInt;

public class prep {
	public static void main(String[] args) throws SQLException {
	
		    String DB_URL = "jdbc:mysql://localhost:3306/orderorder";
		    String DB_USER = "root";
		    String DB_PASSWORD = "kavi@2";
		    String FILE_PATH = "C:\\Users\\ASUS\\OneDrive\\Desktop\\data.csv";
			
		    int batchsize = 20;
		    
		  try {
		    	Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD );
		    	conn.setAutoCommit(false);
		    	String sql = "insert into orders (order_id,date) values(?,?)";
		    	PreparedStatement Statement = conn.prepareStatement(sql);
		    	BufferedReader lineReader = new BufferedReader(new FileReader(FILE_PATH));
		    	String lineText = null;
		    	int count = 0;
		    	lineReader.readLine(); 
		    	while((lineText = lineReader.readLine())!= null) {
		    		String[] data = lineText.split(",");
		    		String order_id = data[0];
		    		String dateString = data[1]; 

                  
                    LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    Date sqlDate = Date.valueOf(localDate);
                    
		    		Statement.setInt(1,parseInt(order_id));
		    		Statement.setDate(2, sqlDate); 
		    		
		    		Statement.addBatch();
                    count++;
		    		if(count % batchsize == 0) {
		    			Statement.executeBatch();
		    		}
		    	}
		    	lineReader.close();
				Statement.executeBatch(); 
				conn.commit();
				conn.close();
				System.out.println("data has sucessfully created");
		  }
		  
		  catch (DateTimeParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            e.printStackTrace();
		  }
		  catch (Exception e) {
		    System.err.println("An unexpected error occurred: " + e.getMessage());
		    e.printStackTrace();
		  }
	}
}

