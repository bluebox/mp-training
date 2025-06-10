import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
    private static final String URL = "jdbc:mysql://localhost:3306/surya_medplus";
    private static final String USERNAME = "Surya";
    private static final String PASSWORD = "Surya525";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            createStudentsTable(connection);
            insertStudent(connection, "John Doe", 20);
            insertStudent(connection, "Jane Smith", 22);
            updateStudent(connection, 1, 21);
            deleteStudent(connection, 2);
            queryStudents(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createStudentsTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Students (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "age INT)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("Students table created successfully.");
        }
    }

    private static void insertStudent(Connection connection, String name, int age) throws SQLException {
        String insertDataSQL = "INSERT INTO Students (name, age) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
            System.out.println("Student inserted successfully.");
        }
    }

    private static void updateStudent(Connection connection, int id, int newAge) throws SQLException {
        String updateDataSQL = "UPDATE Students SET age = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateDataSQL)) {
            preparedStatement.setInt(1, newAge);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Student updated successfully.");
        }
    }

    private static void deleteStudent(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "DELETE FROM Students WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDataSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Student deleted successfully.");
        }
    }

    private static void queryStudents(Connection connection) throws SQLException {
        String queryDataSQL = "SELECT * FROM Students";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(queryDataSQL)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.printf("ID: %d, Name: %s, Age: %d%n", id, name, age);
            }
        }
    }
}
