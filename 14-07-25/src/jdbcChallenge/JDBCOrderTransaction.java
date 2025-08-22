package jdbcChallenge;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JDBCOrderTransaction {

	public static void main(String[] args) {

		String url = "jdbc:mysql//localhost:3306/orders";
		String uname = "root";
		String pwd = "root";

		Connection con = null;
		Statement st = null;
		try {
			con = DriverManager.getConnection(url, uname, pwd);
			con.setAutoCommit(false);
			st = con.createStatement();

			String customer_Name = "gnana";
			String date = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(new Date());
			String insertOrder = "insert into student.orders (customer_name,orderdate) " + "values (" + customer_Name
					+ ",'" + date + "')";

			st.executeUpdate(insertOrder, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			int orderId = -1;
			if (rs.next()) {
				orderId = rs.getInt(1);
				System.out.println("inserted order" + orderId);
			} else {
				throw new SQLException("failed to retrieve order Id");
			}

		} catch (SQLException e) {
			try {
				if (con != null) {
					con.rollback();
					System.out.println("transcaction rollback due to error");
				}
			} catch (SQLException rollBackEx) {
				rollBackEx.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

}
