package Day15_17_07;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ConcurrentRequests {
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
        sendPostRequests(client,urlBase,urlParams, orderMap);
        sendGetRequests(client,sites);
    }

        public static void sendGetRequests(HttpClient client, List<URI> uriList) {
            List<CompletableFuture<HttpResponse<String>>> futures = new ArrayList<>();

            for (URI uri : uriList) {
                HttpRequest request = HttpRequest.newBuilder(uri).build();
                CompletableFuture<HttpResponse<String>> future =
                        client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
                futures.add(future);
            }

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            for (CompletableFuture<HttpResponse<String>> future : futures) {
                System.out.println(future.join().body());
            }
        }

        public static void sendPostRequests(HttpClient client, String url, String format, Map<String, Integer> data) {
            List<CompletableFuture<HttpResponse<String>>> futures = new ArrayList<>();

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                String requestBody = String.format(format, entry.getKey(), entry.getValue());
                HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build();

                CompletableFuture<HttpResponse<String>> future =
                        client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
                futures.add(future);
            }

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            for (CompletableFuture<HttpResponse<String>> future : futures) {
                System.out.println(future.join().body());
            }
        }
    }

