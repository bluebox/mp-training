package day15.networks;

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

public class ConcurrentRequests {

    private static final Path orderTracking = Path.of("orderTracking.json");

    public static void main(String[] args) {

        Map<String, Integer> orderMap = Map.of(
                "apples", 500,
                "oranges", 1000,
                "bananas", 750,
                "carrots", 2000,
                "cantaloupes", 100
        );

        String urlParams = "product=%s&amount=%d";
        String urlBase = "http://localhost:8080";

        List<URI> sites = new ArrayList<>();
        orderMap.forEach((k, v) -> sites.add(URI.create(
                urlBase + "?" + urlParams.formatted(k, v)
        )));

        HttpClient client = HttpClient.newHttpClient();

        // Send GET requests
        sendGets(client, sites);

        // Ensure file exists and is empty before writing
        try {
            Files.write(orderTracking, List.of(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to prepare tracking file", e);
        }

        // Send POST requests and collect responses to file
        sendPostsAndWriteToFile(client, urlBase, urlParams, orderMap);
    }

    private static void sendGets(HttpClient client, List<URI> uris) {
        var futures = uris.stream()
                .map(uri -> HttpRequest.newBuilder(uri).GET().build())
                .map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0])).join();

        System.out.println("GET request responses:");
        futures.forEach(f -> {
            HttpResponse<String> response = f.join();
            System.out.printf("Status: %d | Body: %s%n", response.statusCode(), response.body());
        });
    }

    private static void sendPostsAndWriteToFile(HttpClient client, String baseURI,
                                                String paramString, Map<String, Integer> orders) {

        var futures = orders.entrySet().stream()
                .map(e -> paramString.formatted(e.getKey(), e.getValue()))
                .map(body -> HttpRequest.newBuilder(URI.create(baseURI))
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .build())
                .map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0])).join();

        List<String> responses = new ArrayList<>();

        futures.forEach(future -> {
            HttpResponse<String> response = future.join();
            System.out.printf("POST status: %d | Body: %s%n", response.statusCode(), response.body());
            responses.add(response.body());
        });

        try {
            Files.write(orderTracking, responses, StandardOpenOption.APPEND);
            System.out.println("POST responses written to: " + orderTracking.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write POST responses to file", e);
        }
    }
}
