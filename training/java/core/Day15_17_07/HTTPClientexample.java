package Day15_17_07;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HTTPClientexample {
	public static void main(String[] args) {
		try {
			URL url=new URL("http://localhost:8080");

			HttpClient client=HttpClient.newHttpClient();
			HttpRequest request=HttpRequest.newBuilder()
					.GET()
					.uri(url.toURI())
					.header("user-Agent","Chrome")
					.headers("Accept","application/json","Accept","text/html")
					.timeout(Duration.ofSeconds(30))
					.build();
			
			HttpURLConnection conn =(HttpURLConnection) url.openConnection();
			
			HttpResponse<String> response=client.send(request,
					HttpResponse.BodyHandlers.ofString());
			
					
			if(response.statusCode()!=200) {
				System.out.println("Error in reading the page");
				return;
			}
			System.out.println(response.body());
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}
}
