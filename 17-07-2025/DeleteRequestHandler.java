package com.prana;

import java.net.URI;
import java.net.http.*;

public class DeleteRequestHandler {
    public static void sendDelete(HttpClient client) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
            .DELETE()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("DELETE Response:\n" + response.body());
    }
}