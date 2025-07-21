package july17_challenges;

import java.net.URI;
import java.net.http.*;
import java.util.*;
import java.util.concurrent.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.stream.Collectors;

public class HttpClientCode {
    public static void sendPosts(
            HttpClient client,
            String baseUri,
            Map<String, String> orders
    ) {
        List<CompletableFuture<String>> futures = new ArrayList<>();

        for (Map.Entry<String, String> entry : orders.entrySet()) {
            String jsonBody = String.format(
                    "{\"orderId\": \"%s\", \"product\": \"%s\"}",
                    entry.getKey(), entry.getValue()
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUri))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            CompletableFuture<String> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body);

            futures.add(future);
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        allFutures.join();

        List<String> responses = futures.stream()
                .map(CompletableFuture::join)
                .toList();

        try {
            Path outputPath = Paths.get("order_responses.json");
            String jsonArray = responses.stream()
                    .collect(Collectors.joining(",\n", "[\n", "\n]"));

            Files.writeString(outputPath, jsonArray, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("Saved responses to order_responses.json");
        } catch (IOException e) {
            System.err.println("File write error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        String baseUri = "http://localhost:8080/api/orders";

        Map<String, String> orders = new HashMap<>();
        orders.put("101", "apple");
        orders.put("102", "banana");
        orders.put("103", "cherry");

        sendPosts(client, baseUri, orders);
    }
}
