package dev.lpa.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.net.HttpURLConnection.HTTP_OK;

public class SimpleHttpServer {

    private static long visitorCounter = 0;

    public static void main(String[] args) {

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080),
                    0);

            server.createContext("/", exchange -> {
                String requestMethod = exchange.getRequestMethod();
                System.out.println("Request Method: " + requestMethod);

                String data = new String(exchange.getRequestBody().readAllBytes());
                System.out.println("Body data: " + data);

                Map<String,String> parameters = parseParameters(data);
                System.out.println(parameters);

                exchange.getRequestHeaders().entrySet().forEach(System.out::println);
                if (requestMethod.equals("POST")) {
                    visitorCounter++;
                }

                String firstName = parameters.get("first");
                String lastName = parameters.get("last");
                String response = """
                <html>
                    <body>
                        <h1>Hello World from My Http Server</h1>
                        <p>Number of Visitors who signed up = %d<p>
                        <form method="post">
                            <label for="first">First name:</label>
                            <input type="text" id="first" name="first" value="%s">
                            <br>
                            <label for="last">Last name:</label>
                            <input type="text" id="last" name="last" value="%s">
                            <br>
                            <input type="submit" value="Submit">
                        </form>
                    </body>
                </html>
                """.formatted(visitorCounter,
                    firstName == null ? "" : firstName,
                    lastName == null ? "" : lastName);

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                var bytes = response.getBytes();
                exchange.sendResponseHeaders(HTTP_OK, bytes.length);
                exchange.getResponseBody().write(bytes);
                exchange.close();
            });

            server.start();
            System.out.println("Server is listening on port 8080...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, String> parseParameters(String requestBody) {

        Map<String, String> parameters = new HashMap<>();
        String[] pairs = requestBody.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                parameters.put(keyValue[0], keyValue[1]);
            }
        }
        return parameters;
    }
}
