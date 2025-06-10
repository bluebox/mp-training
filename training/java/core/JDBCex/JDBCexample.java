package JDBCex;
import javax.swing.*;
import java.sql.*;
public class JDBCexample {

	public static void main(String[] args) {
		String username=JOptionPane.showInputDialog(null, "Enter DB Username");
		JPasswordField pf = new JPasswordField();
		int okCxl=JOptionPane.showConfirmDialog(null, pf,"Enter DB Pasword",JOptionPane.OK_CANCEL_OPTION);
		final char[] password=(okCxl==JOptionPane.OK_OPTION)?pf.getPassword(): null;
		String url="jdbc:mysql://localhost:3306/music";
		String query="SELECT * FROM music_details";
		try(Connection conn=DriverManager.getConnection(url,username,new String(password));
				Statement stmt = conn.createStatement()){
			ResultSet rs = stmt.executeQuery(query);
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
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
