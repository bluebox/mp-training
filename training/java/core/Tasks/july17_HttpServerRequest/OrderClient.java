package corejava.july17_HttpServerRequest;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class OrderClient {

    private static final String RECEIPT_FILE_PATH = "receipts.txt";

    public static void sendPosts(HttpClient client, String baseUri, String uriParamFormat, Map<String, String> orders) throws IOException {
        Files.write(Path.of(RECEIPT_FILE_PATH), new byte[0]); 
        List< CompletableFuture<HttpResponse<String>>> futures = new ArrayList<>();
        for (Map.Entry<String, String> entry : orders.entrySet()) {
            String orderId = entry.getKey();
            String orderBody = entry.getValue();
            String fullUri = baseUri + String.format(uriParamFormat, orderId);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullUri))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(orderBody))
                    .build();
            CompletableFuture<HttpResponse<String>> responsefuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            responsefuture.thenAccept(response -> {
                        try {
                            Files.writeString(
                                    Path.of(RECEIPT_FILE_PATH),
                                    response.body() + System.lineSeparator(),
                                    StandardOpenOption.APPEND,
                                    StandardOpenOption.CREATE
                            );
                        } catch (IOException e) {
                            System.err.println("Error writing to file: " + e.getMessage());
                        }
                    });

            futures.add(responsefuture);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String baseUri = "http://localhost:8081/order/";
        String uriParameter = "%s";

        Map<String, String> orders = Map.of(
                "101", "{\"item\": \"laptop\", \"qty\": 1}",
                "102", "{\"item\": \"mouse\", \"qty\": 2}",
                "103", "{\"item\": \"keyboard\", \"qty\": 1}"
        );

        sendPosts(client, baseUri, uriParameter, orders);
        System.out.println("POST requests complete. Receipts saved to " + RECEIPT_FILE_PATH);
    }
}
