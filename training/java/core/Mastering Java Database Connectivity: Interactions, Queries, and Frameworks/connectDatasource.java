import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class connectDatasource {
    public static void main(String[] args) {
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setURL("jdbc:mysql://localhost:3306/surya_medplus");
        ((MysqlDataSource) dataSource).setUser("Surya");
        ((MysqlDataSource) dataSource).setPassword("Surya525");

        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to the database using DataSource");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
