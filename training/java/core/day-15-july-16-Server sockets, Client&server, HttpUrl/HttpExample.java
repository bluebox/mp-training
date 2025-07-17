package day15;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpExample {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://localhost:8000");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", "Chrome");
			conn.setRequestProperty("Accept", "application/json, text/html");
			conn.setReadTimeout(5000);
			int responseCode = conn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				System.out.println("error in GET");
				return;
			}
			InputStream ist = conn.getInputStream(); 
			for (int ch; (ch = ist.read()) != -1;) {
				System.out.print((char) ch);
			}
			System.out.println("Response Code: " + responseCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
