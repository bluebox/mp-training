package MYSQLMusic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Statement;

public class QueryMusic {

	public static void main(String[] args) {
		
		Properties props =new Properties();
		
		try {
			props.load(Files.newInputStream(Path.of("music.properties"),
					StandardOpenOption.READ));
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		Scanner sc =new Scanner(System.in);
		
		
		
		String albumName ;//= "Tapestry";
//		System.out.println("Enter an Album Name: ");
//		albumName = sc.nextLine();
		System.out.println("Enter an Track Number: ");
		String trackNum =sc.nextLine();
		String query = "SELECT * FROM music.artists";
		String albumquery = "SELECT * FROM music.albumview WHERE track_number=%s".formatted(trackNum);

		
		var dataSource = new MysqlDataSource();		
		dataSource.setServerName(props.getProperty("serverName"));
		dataSource.setPort(3306);
		dataSource.setDatabaseName("music");
		
		
		try(Connection connection = dataSource.
				getConnection(props.getProperty("databaseName"),
						System.getenv("MYSQL_PASS"));
				Statement statement = connection.createStatement();){
			
			System.out.println("Music DB Connected");
			ResultSet resultSet = statement.executeQuery(albumquery);
			
			var meta = resultSet.getMetaData();
			
			for(int i=1;i<=meta.getColumnCount();i++) {
//				System.out.printf("%d %s %s %n".formatted(i,meta.getColumnName(i),meta.getColumnTypeName(i)));
				System.out.printf("%-15s".formatted(meta.getColumnName(i).toUpperCase()));

			}
			
			System.out.println();

			System.out.println("----------------------------------------------------------");
			
			while(resultSet.next()) {
				System.out.printf("%-15s %-15s %-15d %-15s %n",resultSet.getString("album_name"),resultSet.getString("artist_name"),resultSet.getInt("track_number"),
						resultSet.getString("song_title"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		

		
	
	}

}
