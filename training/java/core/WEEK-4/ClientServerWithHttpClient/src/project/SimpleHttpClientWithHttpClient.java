package project;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;
import java.util.stream.Stream;

public class SimpleHttpClientWithHttpClient {
	public static void main(String [] args){
		URI uri=URI.create("http://localhost:8080");
		HttpClient httpClient=HttpClient.newHttpClient();
		HttpRequest request=HttpRequest.newBuilder()
				.GET()
				.uri(uri)
				.header("User-Agent","Chrome")
				.headers("Accept","application/json","Accept","text/html")
				.timeout(Duration.ofSeconds(30))
				.build();
		try {
			HttpResponse<Path> response=httpClient.send(request, HttpResponse.BodyHandlers.ofFile(Path.of("test.html")));
			System.out.println(response);
			if(response.statusCode()!=HttpURLConnection.HTTP_OK) {
				System.out.println("Error while sending requets...");
				return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String requestBody="Hello";
		request=HttpRequest.newBuilder()
				.uri(uri)
				.header("Content-Type", "text/html")
				.headers("Accept","application/json","Accept","text/html")
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();
		try {
			HttpResponse<String> response=httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response);
			System.out.println(response.body());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
