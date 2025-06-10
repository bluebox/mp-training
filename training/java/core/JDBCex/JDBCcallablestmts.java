package JDBCex;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
import java.sql.CallableStatement;
import com.mysql.cj.jdbc.MysqlDataSource;
public class JDBCcallablestmts {

	public static void main(String[] args) {
		MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306); 
        dataSource.setDatabaseName("orders");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        Scanner scanner = new Scanner(System.in);
        try (Connection conn = dataSource.getConnection();) {
        	getOrderDateViaStoredProcedure(conn, scanner);
        } catch (SQLException e) {
            System.err.println("A database error happened: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    private static void getOrderDateViaStoredProcedure(Connection conn, Scanner scanner) {
        System.out.println("\n--- Fetching Order Date using Stored Procedure ---");
        System.out.print("Enter an Order ID to find its date: ");
        int orderId= -1;
        orderId = scanner.nextInt();
        String sql = "{CALL get_order_date_by_id(?, ?)}";

        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, orderId);
            cstmt.registerOutParameter(2, Types.TIMESTAMP);
            System.out.println("Executing stored procedure...");
            cstmt.execute();
            Timestamp orderDate = cstmt.getTimestamp(2);
            System.out.println("\n--- Result from Stored Procedure ---");
            if (orderDate != null) {
                System.out.println("Order ID: " + orderId + ", Date: " + orderDate);
            } else {
                System.out.println("No date found for Order ID " + orderId+ " (it might not exist).");
            }

        } catch (SQLException e) {
            System.err.println("Error calling stored procedure: " + e.getMessage());
            e.printStackTrace();
        }
    }
}