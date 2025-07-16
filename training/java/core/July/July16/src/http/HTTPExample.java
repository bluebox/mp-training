package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPExample {

	public static void main(String[] args) throws IOException {
		try {
			URL url = new URL("http://example.com");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "aplication/json,text/html");
			connection.setReadTimeout(300000);

			int responseCode = connection.getResponseCode();
			System.out.printf("Response Code : %d%n", responseCode);

			if (responseCode != 200) {
				System.out.println("Error reading web page " + url);
				return;
			}
			printContents(connection.getInputStream());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	private static void printContents(InputStream url) {

		try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(url))) {
			String line;
			while ((line = inputStream.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
