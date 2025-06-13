package Jdbc.JDBCConnection.productsChallenge;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChallengeClassToCreateTable {
      public static void createTable(Connection conn,String q) throws SQLException {
    	  Statement st=conn.createStatement();
    	  st.executeUpdate(q);
    	  System.out.println(q+" added");
      }
}
