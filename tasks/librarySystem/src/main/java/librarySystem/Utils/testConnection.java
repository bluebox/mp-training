package librarySystem.Utils;

public class testConnection {
	public static void main(String[] args) {
		DbConnection conn = new DbConnection();
		if(conn == null)
		{
			System.out.println("Connection variable is null");
		}else
		{
			System.out.println("Connection variable is not null");
		}
	}
}
