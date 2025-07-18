package day16;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientGet {

	public static void main(String[] args) {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					  .uri(new URI("https://kaushik1.free.beeceptor.com/"))
					  .timeout(Duration.ofSeconds(10)) 
					  .version(HttpClient.Version.HTTP_2)
					  .GET()
					  .build();
			
			HttpResponse<String> response = client.send(request, 
					HttpResponse.BodyHandlers.ofString());
			System.out.println("response code "+response.statusCode());
			System.out.println(response.body());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
