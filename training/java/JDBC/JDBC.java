package JDBC;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.swing.*;
import javax.xml.transform.Result;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.Properties;

public class JDBC {

    private final static String file = "jdbc:mysql://localhost:3306/music";

    public static void main(String[] args) {

//        String userName= JOptionPane.showInputDialog(null,"enter the user name");
//        JPasswordField passwordField= new JPasswordField();
//        int a=JOptionPane.showConfirmDialog(null,passwordField,"enterPassword",JOptionPane.OK_CANCEL_OPTION);
//        final char[] password=(a==(JOptionPane.OK_OPTION)?passwordField.getPassword():null);

        Properties properties = new Properties();
        String insert="INSERT INTO  music.artists(artist_id,artist_name) VALUES(202,'PRASAD')";
        String query="SELECT * FROM music.artists ";

        try {
            properties.load(Files.newInputStream(Path.of("music.properties")));

            var dataSource = new MysqlDataSource();
            dataSource.setServerName(properties.getProperty("serverName"));
            dataSource.setPort(Integer.parseInt(properties.getProperty("port")));
            dataSource.setDatabaseName(properties.getProperty("databaseName"));
//            dataSource.setUser(properties.getProperty("userName"));
//            dataSource.setPassword(properties.getProperty("password"));


            try (Connection connection = dataSource.getConnection(properties.getProperty("username"),properties.getProperty("password"))) {
                System.out.println("yes its working!!!");

                Statement statement=connection.createStatement();
               boolean  resultSet1=statement.execute(insert);
                ResultSet resultSet=statement.executeQuery(query);
                ResultSetMetaData metadata= resultSet.getMetaData();
                for(int i=1;i<=metadata.getColumnCount();i++){
                    System.out.printf("%-15s %n",metadata.getColumnName(i).toUpperCase());
                }
                while(resultSet.next()){
                    System.out.printf("%-6d %s %n",resultSet.getInt(1),resultSet.getString("artist_name"));
                }
            } catch (SQLException e) {
                System.out.println("something happened in ");
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            System.out.println("something happened OUT");
            throw new RuntimeException(e);

        }
    }
}
