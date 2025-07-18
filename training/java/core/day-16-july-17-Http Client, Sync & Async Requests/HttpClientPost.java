package day16;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientPost {

	public static void main(String[] args) {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					  .uri(new URI("https://kaushik1.free.beeceptor.com"))
					  .headers("Content-Type", "text/plain;charset=UTF-8")
					  .POST(HttpRequest.BodyPublishers.ofString("user=kaushik"))
					  .build();

			HttpResponse<String> response = client.send(request, 
					HttpResponse.BodyHandlers.ofString()); 
			System.out.println("response code->"+response.statusCode());
			System.out.println(response.body());
		}  catch (Exception e) {
			e.printStackTrace();
		} 

	}

}
