package jpachallenges.httpchallenges;

import java.net.URI;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.io.IOException;

public class HttpClientChallenge {

    // Class variable for the output file path
    private static final String OUTPUT_FILE_PATH = "order_receipts.txt";

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String baseUri = "https://api.example.com/orders";
        String paramString = "orderId=%d&customer=%s&amount=%.2f";
        
        // Map of orders to submit
        Map<Integer, Order> orders = new HashMap<>();
        orders.put(1, new Order(1, "John Doe", 125.99));
        orders.put(2, new Order(2, "Jane Smith", 89.50));
        orders.put(3, new Order(3, "Bob Johnson", 210.25));
        
        // Execute the POST requests
        sendPosts(client, baseUri, paramString, orders);
    }

    public static void sendPosts(HttpClient client, String baseUri, 
                               String formattableParamString, 
                               Map<Integer, Order> orders) throws IOException {
        
        // Clear the output file at start
        Files.deleteIfExists(Paths.get(OUTPUT_FILE_PATH));
        Files.createFile(Paths.get(OUTPUT_FILE_PATH));
        
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        
        for (Order order : orders.values()) {
            // Format the parameter string with order values
            String params = String.format(formattableParamString, 
                                        order.getId(), 
                                        order.getCustomer(), 
                                        order.getAmount());
            
            // Build the POST request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUri))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(BodyPublishers.ofString(params))
                .build();
                
            // Send the request asynchronously
            CompletableFuture<Void> future = client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(response -> {
                    try {
                        // Write response to file
                        String receipt = String.format("Order %d response: %s%n", 
                                                     order.getId(), response.body());
                        Files.write(Paths.get(OUTPUT_FILE_PATH), 
                                  receipt.getBytes(), 
                                  StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
            
            futures.add(future);
        }
        
        // Wait for all requests to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        
        System.out.println("All POST requests completed. Responses written to " + OUTPUT_FILE_PATH);
    }
    
    static class Order {
        private int id;
        private String customer;
        private double amount;
        
        public Order(int id, String customer, double amount) {
            this.id = id;
            this.customer = customer;
            this.amount = amount;
        }
        
        public int getId() { return id; }
        public String getCustomer() { return customer; }
        public double getAmount() { return amount; }
    }
}