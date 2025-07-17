package day_16_7_25;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class HttpSendposts {

  
    public static void sendPostsAndWriteResponses(HttpClient client, String url, String formatString, Map<String, String> orders, String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Map.Entry<String, String> entry : orders.entrySet()) {
                String orderId = entry.getKey();
                String orderData = entry.getValue();
                String requestBody = formatString.replace("{orderId}", orderId);
                requestBody = requestBody.replace("{orderData}", orderData);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json") // Or other content type as needed
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build();

                try {
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    String responseBody = response.body();
                    writer.write("Response for order " + orderId + ": " + responseBody + "\n");
                } catch (IOException | InterruptedException e) {
                    writer.write("Error sending request for order " + orderId + ": " + e.getMessage() + "\n");
                    e.printStackTrace(); // Or handle the exception as needed
                }
            }
        } catch (IOException e) {
            System.err.println("Error opening/creating output file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage:
        HttpClient client = HttpSendposts.newHttpClient();
        String url = "https://example.com/api/orders";
        String formatString = "{\"order_id\": \"{orderId}\", \"data\": \"{orderData}\"}";
        Map<String, String> orders = Map.of(
                "123", "some_data_1",
                "456", "some_data_2",
                "789", "some_data_3"
        );
        String outputFile = "order_responses.txt";

        sendPostsAndWriteResponses(client, url, formatString, orders, outputFile);
        System.out.println("Finished processing orders. Responses written to " + outputFile);
    }

	
}
