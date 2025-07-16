package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLDemo {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		try {
			URL url = new URL("http://example.com");
			printContents(url.openStream());
			URLConnection urlConnection = url.openConnection();
			System.out.println(urlConnection.getContentType());
			urlConnection.getHeaderFields().entrySet().forEach(System.out::println);
			System.out.println(urlConnection.getHeaderField("Chache-Control"));
			urlConnection.connect();
			printContents(urlConnection.getInputStream());
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
