package Day14_16_07_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebContent {
	public static void main(String[] args) {
		try {
			URL url=new URL("http://example.com");
			printer(url.openStream());
			
			////////////////////////////////////////
			URL url2=new URL("https://jsonplaceholder.typicode.com/todos?id=5");
			URLConnection conn=url2.openConnection();
			System.out.println(conn.getContentType());
			conn.getHeaderFields().entrySet().forEach(System.out::println);
			conn.connect();
			printer(conn.getInputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void printer(InputStream st) {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(st));){
			String line;
			while((line=br.readLine())!=null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
