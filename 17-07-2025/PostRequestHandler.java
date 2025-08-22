package com.prana;

import java.net.URI;
import java.net.http.*;

public class PostRequestHandler {
    public static void sendPost(HttpClient client) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"Lakshmi\",\"role\":\"developer\"}"))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST Response:\n" + response.body());
    }
}