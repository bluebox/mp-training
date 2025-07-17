package HttpProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpExample {

	public static void main(String[] args) {

		try {
//			URL url = new URL("https://www.medplusmart.com/");
			URL url = new URL("http://localhost:8080");

			try {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("User-Agent", "Chrome");
				conn.setRequestProperty("Accept", "application/json, text/html");
				conn.setReadTimeout(30000);
				
				

				int responseCode = conn.getResponseCode();
				System.out.printf("Response code: %d%n", responseCode);

				if (responseCode != HttpURLConnection.HTTP_OK) {
					System.out.println("Error reading web page " + url);
					return;
				}
				print(conn.getInputStream());

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	private static void print(InputStream is) {

		try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(is))) {

			String line;
			while ((line = inputStream.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
