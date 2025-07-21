package july17_challenges;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class LocalServer {

    public static void main(String[] args) throws IOException {
      
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

       
        server.createContext("/api/orders", new OrderHandler());

        server.setExecutor(null);

        server.start();
        System.out.println("Server running at http://localhost:8080/api/orders");
    }

    static class OrderHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream input = exchange.getRequestBody();
                String body = new String(input.readAllBytes(), StandardCharsets.UTF_8);

                System.out.println("Received JSON: " + body);

                JSONObject receivedJson = new JSONObject(body);

                JSONObject responseJson = new JSONObject();
                responseJson.put("message", "Order received");
                responseJson.put("received", receivedJson);

                String jsonResponse = responseJson.toString(2); 
                byte[] responseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);

                exchange.getResponseHeaders().add("Content-Type", "application/json");

                exchange.sendResponseHeaders(200, responseBytes.length);

                OutputStream output = exchange.getResponseBody();
                output.write(responseBytes);
                output.close();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }
}
