package httpIntro;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HTTPGet {

	public static void main(String[] args) throws IOException {

		try {

			URL url = new URL("http://localhost:8080");
			HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder().GET().uri(url.toURI()).header("User-Agent", "Chrome")
					.headers("Accept", "application/json", "Accept", "text/html").timeout(Duration.ofSeconds(30))
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("Error reading web page " + url);
				return;
			}
			System.out.println(response.body());

		} catch (URISyntaxException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
