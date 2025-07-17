package httpIntro;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Order {
	public static void main(String[] args) {

		Map<String, Integer> orderMap = Map.of("phone", 20000, "laptop", 40000, "tablet", 30000, "tv", 15000);
		String urlParams = "product=%s&amount=%d";
		String urlBase = "http://localhost:8080";
		List<URI> sites = new ArrayList<>();

		orderMap.forEach((k, v) -> sites.add(URI.create(urlBase + "?" + urlParams.formatted(k, v))));

		HttpClient client = HttpClient.newHttpClient();

		sendGets(client, sites);
	}

	private static void sendGets(HttpClient client, List<URI> uris) {
		var futures = uris.stream().map(uri -> HttpRequest.newBuilder(uri)).map(HttpRequest.Builder::build)
				.map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString())).toList();

		var allFutureRequests = CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();
		futures.forEach(f -> System.out.println(f.join().body()));
	}
}
