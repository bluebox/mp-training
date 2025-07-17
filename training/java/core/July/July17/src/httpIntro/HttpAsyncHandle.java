package httpIntro;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class HttpAsyncHandle {
	public static void main(String[] args) throws IOException {

		try {
			HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofMinutes(1))
					.version(HttpClient.Version.HTTP_1_1).build();

			HttpRequest request = HttpRequest.newBuilder()
					.POST(HttpRequest.BodyPublishers.ofString("fist=satheesh&last=sira"))
					.uri(URI.create("http://localhost:8080")).header("User-Agent", "Chrome")
					.headers("Accept", "application/json", "Accept", "text/html").build();
//			HttpResponse<Stream<String>> response;
			CompletableFuture<HttpResponse<Stream<String>>> futureResponse = client.sendAsync(request,
					HttpResponse.BodyHandlers.ofLines());
			futureResponse.thenAccept(HttpAsyncHandle::handleResponse);
			System.out.println("10 job thers ");
			int jobs = 0;

			while (jobs < 10) {
				System.out.print("job " + jobs++);
				TimeUnit.SECONDS.sleep(1);
			}
			System.out.println();

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
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
