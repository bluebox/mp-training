package Jdbc.JDBCConnection.productsChallenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.List;

public class InsertingIntoProductsUsingCSVFile {
	public  static List<String[]>ans;
	
    public static void addProducts() {
    	ans=new ArrayList<>();
    	String url="jdbc:mysql://localhost:3306/test";
		String user="Anand";
		String password="1925112816@Aa";
		MysqlDataSource md=new MysqlDataSource();
		md.setUrl(url);
		md.setUser(user);
		md.setPassword(password);
	    try(BufferedReader br=new BufferedReader(new FileReader("Products.csv"))){
	    	String ch="";
	    	boolean temp=true;
	    	while((ch=br.readLine())!=null) {
	    		if(temp) {
	    			temp=false;
	    			continue;
	    		}
	    		String[]a=ch.split(",");
	    		ans.add(a);
	    	}
	    }
	    catch(IOException e) {
	    	System.out.println(e.getMessage());
	    }
//		for(String []ele:ans) {
//			for(int i=0;i<ele.length;i++) {
//				System.out.print(ele[i]+" ");
//			}
//			System.out.println();
//		}
        try(Connection conn=md.getConnection()){
        	for(String []ele:ans) {
        		String q = "insert into products  values (?,?, ?)";
        		PreparedStatement st=conn.prepareStatement(q);
        		for(int i=0;i<ele.length;i++) {
        			st.setString(i+1,ele[i]);
        		}
        		st.executeUpdate();
        	}
        	System.out.println("Data is added to table");
        }
        catch(SQLException e) {
        	System.out.println(e.getMessage());
        }
    }
}
