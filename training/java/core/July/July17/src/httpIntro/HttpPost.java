package httpIntro;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class HttpPost {

	public static void main(String[] args) throws IOException {

		try {
			HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofMinutes(1))
					.version(HttpClient.Version.HTTP_1_1).build();

			HttpRequest request = HttpRequest.newBuilder()
					.POST(HttpRequest.BodyPublishers.ofString("fist=satheesh&last=sira"))
					.uri(URI.create("http://localhost:8080")).header("User-Agent", "Chrome")
					.headers("Accept", "application/json", "Accept", "text/html").build();
			HttpResponse<Stream<String>> response;
			CompletableFuture<HttpResponse<Stream<String>>> futureResponse = client.sendAsync(request,
					HttpResponse.BodyHandlers.ofLines());

			while (!futureResponse.isDone()) {
				System.out.print(". ");
				TimeUnit.SECONDS.sleep(1);
			}
			System.out.println();

			response = futureResponse.get();
			handleResponse(response);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {

			e.printStackTrace();
		}

	}

	private static void handleResponse(HttpResponse<Stream<String>> response) {

		if (response.statusCode() == HttpURLConnection.HTTP_OK) {
			response.body().forEach(System.out::println);
		} else {
			System.out.println("Erro reading response " + response.uri());
		}
	}

}
