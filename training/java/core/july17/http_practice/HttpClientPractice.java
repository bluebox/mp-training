package dev.tulasidhar.july17.http_practice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientPractice {
	public static void main(String[] args) {
		HttpClient client = HttpClient.newHttpClient();
		
		try {
			URL url = new URI("http://localhost:8008/").toURL();
			
			HttpRequest request = HttpRequest.newBuilder()
									.uri(url.toURI())
									.POST(HttpRequest.BodyPublishers.ofString("hello from the client "))
									.setHeader("User-Agent", "chrome")
									.build();
			
			HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
