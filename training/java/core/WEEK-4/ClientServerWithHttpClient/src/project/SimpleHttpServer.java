package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {
	public static void main(String [] args) {
		try {
			HttpServer server=HttpServer.create(new InetSocketAddress(8080),0);
			System.out.println("Server created succesfully at "+server.getAddress());
			
			server.createContext("/",exchange -> {
				String requestMethod=exchange.getRequestMethod();
				System.out.println(requestMethod+" Request recieced from Client...");
				if("GET".equals(requestMethod)) {
					byte [] response=Files.readAllBytes(Path.of("getResponse.html"));
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
					exchange.getResponseBody().write(response);
					exchange.close();
				}
				else if("POST".equals(requestMethod)) {
					byte [] requestBody=exchange.getRequestBody().readAllBytes();
					String response=new String(requestBody,"UTF-8");
					exchange.getResponseBody().write(response.getBytes());
					exchange.close();
				}
			});
			server.start();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
