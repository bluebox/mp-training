package dev.lpa;

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

        Map<String,Integer> orderMap =
                Map.of( "apples", 500,
                        "oranges", 1000,
                        "bananas", 750,
                        "carrots", 2000,
                        "cantaloupes", 100 );

        String urlParams = "product=%s&amount=%d";

        String urlBase = "http://localhost:8080";

        List<URI> sites = new ArrayList<>();
        orderMap.forEach( (k,v) -> sites.add(URI.create(
                urlBase + "?" + urlParams.formatted(k, v)
        )));

        HttpClient client = HttpClient.newHttpClient();
        sendGets(client, sites);

        if (!Files.exists(orderTracking)) {
            try {
                Files.createFile(orderTracking);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        sendPostsWithFileResponse(client, urlBase, urlParams, orderMap);
    }

    private static void sendGets(HttpClient client, List<URI> uris) {

        var futures = uris.stream()
                .map(uri -> HttpRequest.newBuilder(uri))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(
                        request, HttpResponse.BodyHandlers.ofString()))
                .toList();

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );

        allFutureRequests.join();

        futures.forEach(f -> {
            System.out.println(f.join().body());
        });
    }

    private static void sendPosts(HttpClient client, String baseURI,
                                  String paramString, Map<String,Integer> orders) {

        var futures = orders.entrySet().stream()
                .map(e -> paramString.formatted(
                        e.getKey(), e.getValue()))
                .map(s -> HttpRequest.newBuilder(URI.create(baseURI))
                        .POST(HttpRequest.BodyPublishers.ofString(s)))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(
                        request, HttpResponse.BodyHandlers.ofString()))
                .toList();

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );

        allFutureRequests.join();
        List<String> lines = new ArrayList<>();

        futures.forEach(f -> {
            lines.add(f.join().body());
        });

        try {
            Files.write(orderTracking, lines, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void sendPostsWithFileResponse(HttpClient client, String baseURI,
                                  String paramString, Map<String,Integer> orders) {

        var futures = orders.entrySet().stream()
                .map(e -> paramString.formatted(
                        e.getKey(), e.getValue()))
                .map(s -> HttpRequest.newBuilder(URI.create(baseURI))
                        .POST(HttpRequest.BodyPublishers.ofString(s)))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(
                        request, HttpResponse.BodyHandlers.ofFile(orderTracking,
                                StandardOpenOption.APPEND)))
                .toList();

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );

        allFutureRequests.join();
    }
}
