package HttpProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebContent {

	public static void main(String[] args) {

		try {
			URL url = new URL("https://example.com/");

//			URL url = new URL("https://www.medplusmart.com/");

			URL url1 = new URL("https://jsonplaceholder.typicode.com/todos?id=5");
			try {
				print(url.openStream());
				URLConnection urlConn = url1.openConnection();
				System.out.println(urlConn.getContentType());
				urlConn.getHeaderFields().entrySet().forEach(System.out::println);

				System.out.println(urlConn.getHeaderField("Cache-Control"));

				urlConn.connect();
				print(urlConn.getInputStream());

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
