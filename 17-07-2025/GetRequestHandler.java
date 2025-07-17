package com.prana;

import java.net.URI;
import java.net.http.*;

public class GetRequestHandler {
    public static void sendGet(HttpClient client) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET Response:\n" + response.body());
    }
}