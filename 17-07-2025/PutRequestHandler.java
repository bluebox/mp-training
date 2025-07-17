package com.prana;

import java.net.URI;
import java.net.http.*;

public class PutRequestHandler {
    public static void sendPut(HttpClient client) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString("{\"name\":\"Lakshmi\",\"role\":\"senior developer\"}"))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("PUT Response:\n" + response.body());
    }
}

