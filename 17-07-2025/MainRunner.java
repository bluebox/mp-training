package com.prana;

import java.net.http.HttpClient;

public class MainRunner {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        try {
            PostRequestHandler.sendPost(client);
            PutRequestHandler.sendPut(client);
            GetRequestHandler.sendGet(client);
            DeleteRequestHandler.sendDelete(client);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}