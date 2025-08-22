package com.prana;

import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.util.*;
import java.util.concurrent.*;

public class HttpPostSender {
    private static final String RECEIPTS_FILE_PATH = "receipts.txt";
    public static void sendPosts(HttpClient client, String baseURI, String parameterFormat, Map<String, String> orders) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (Map.Entry<String, String> entry : orders.entrySet()) {
            String formattedParams = String.format(parameterFormat, entry.getKey(), entry.getValue());
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURI))
                .POST(HttpRequest.BodyPublishers.ofString(formattedParams))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
            CompletableFuture<Void> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> writeToFile(RECEIPTS_FILE_PATH, response.body()));
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private static synchronized void writeToFile(String path, String content) {
        try (FileWriter fw = new FileWriter(path, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        String baseURI = "https://example.com/api/orders";
        String paramFormat = "orderId=%s&item=%s";
        Map<String, String> orders = Map.of(
            "101", "Widget A",
            "102", "Widget B",
            "103", "Widget C"
        );
        sendPosts(client, baseURI, paramFormat, orders);
        System.out.println("All order receipts submitted and written to file.");
    }
}