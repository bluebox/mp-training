package httpClientConcurrentPostsChallenge;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Order {
	private static Path orderTracking = Path.of("PostOrders.json");

	public static void main(String[] args) {

		Map<String, Integer> orderMap = Map.of("phone", 20000, "laptop", 40000, "tablet", 30000, "tv", 15000);
		String urlParams = "product=%s&amount=%d";
		String urlBase = "http://localhost:8080";
		List<URI> sites = new ArrayList<>();

		orderMap.forEach((k, v) -> sites.add(URI.create(urlBase + "?" + urlParams.formatted(k, v))));

		HttpClient client = HttpClient.newHttpClient();

		sendGets(client, sites);
		if (!Files.exists(orderTracking)) {
			try {
				Files.createFile(orderTracking);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		sendPosts(client, urlBase, urlParams, orderMap);
	}

	private static void sendGets(HttpClient client, List<URI> uris) {
		var futures = uris.stream().map(uri -> HttpRequest.newBuilder(uri)).map(HttpRequest.Builder::build)
				.map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString())).toList();

		var allFutureRequests = CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();
		futures.forEach(f -> System.out.println(f.join().body()));
	}

	private static void sendPosts(HttpClient client, String baseUri, String paramString, Map<String, Integer> orders) {

		var futures = orders.entrySet().stream().map(e -> paramString.formatted(e.getKey(), e.getValue()))
				.map(s -> HttpRequest.newBuilder(URI.create(baseUri)).POST(HttpRequest.BodyPublishers.ofString(s)))
				.map(HttpRequest.Builder::build)
				.map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString())).toList();

		var allFutureRequests = CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]));

		allFutureRequests.join();
		List<String> lines = new ArrayList<>();
		lines.add("[");
		futures.forEach(f -> {
			lines.add(f.join().body());
			lines.add(",");
		});
		lines.remove(lines.size() - 1);
		lines.add("]");
		try {
			Files.write(orderTracking, lines, StandardOpenOption.APPEND);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
