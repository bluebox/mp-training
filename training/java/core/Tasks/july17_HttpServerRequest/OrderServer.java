package corejava.july17_HttpServerRequest;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class OrderServer {

    public static void main(String[] args) throws IOException {
    	 HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
    	 server.setExecutor(Executors.newCachedThreadPool());
         server.createContext("/order", exchange->{
        	 if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
        		 byte[] requestBody = exchange.getRequestBody().readAllBytes();
        		 String response = "Received order: " + new String(requestBody);
        		 exchange.sendResponseHeaders(200, response.length());
        		 try (OutputStream os = exchange.getResponseBody()) {
        			 os.write(response.getBytes());
        		 }
        	 } 
        	 else {
        		 String response = "Only POST requests are allowed";
        		 exchange.sendResponseHeaders(405, response.length());
        		 try (OutputStream os = exchange.getResponseBody()) {
        			 os.write(response.getBytes()); 
        		 }
        	 }
         });
		 server.start();
		 System.out.println("Server started and waiting for a client");
     }
}
