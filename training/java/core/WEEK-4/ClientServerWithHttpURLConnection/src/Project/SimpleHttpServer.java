package Project;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.net.URLDecoder;

import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            System.out.println("Server created successfully at port " + server.getAddress().getPort());

            server.createContext("/", exchange -> {
                String requestMethod = exchange.getRequestMethod();
                System.out.println(requestMethod + " request received from client...");

                // Handle GET request
                if (requestMethod.equals("GET")) {
                    byte[] response = Files.readAllBytes(Path.of("getResponse.html"));
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
                    exchange.getResponseBody().write(response);
                    exchange.close();
                } 
                // Handle POST request
                else if (requestMethod.equals("POST")) {
                    byte[] requestParams = exchange.getRequestBody().readAllBytes();
                    String paramsString = new String(requestParams, "UTF-8");
                    System.out.println("Request Body: " + paramsString);

                    // URL Decode the parameters to get the correct values
                    String[] params = paramsString.split("&");
                    String firstName = URLDecoder.decode(params[0].split("=")[1], "UTF-8");
                    String lastName = URLDecoder.decode(params[1].split("=")[1], "UTF-8");

                    // Prepare a response string
                    String response = String.format("First Name is %s and Last Name is %s", firstName, lastName);
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
                    exchange.getResponseBody().write(response.getBytes("UTF-8"));
                    exchange.close();
                }
            });
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
