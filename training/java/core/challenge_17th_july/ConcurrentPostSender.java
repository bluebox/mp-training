package challenge_17th_july;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ConcurrentPostSender {

    private static final String OUTPUT_FILE = "responses.txt";

    public void sendPosts(HttpClient client, String baseUri, String paramTemplate, Map<String, String> orders) throws IOException {
        List<CompletableFuture<String>> futures = new ArrayList<>();

        for (Map.Entry<String, String> entry : orders.entrySet()) {
            String formattedParams = String.format(paramTemplate, entry.getKey(), entry.getValue());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUri))
                    .timeout(Duration.ofSeconds(10))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(formattedParams))
                    .build();


            CompletableFuture<String> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body);

            futures.add(future);
        }

        CompletableFuture<Void> allDone = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allDone.join();

        // Write all responses to a file
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(OUTPUT_FILE))) {
            for (CompletableFuture<String> future : futures) {
                writer.write(future.join());
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String baseUri = "https://httpbin.org/post"; 
        String paramTemplate = "orderId=%s&item=%s";

        Map<String, String> orders = Map.of(
                "101", "Burger",
                "102", "Fries",
                "103", "Pizza"
        );

        ConcurrentPostSender sender = new ConcurrentPostSender();
        sender.sendPosts(client, baseUri, paramTemplate, orders);

        System.out.println("All POST responses written to " + OUTPUT_FILE);
    }
}

