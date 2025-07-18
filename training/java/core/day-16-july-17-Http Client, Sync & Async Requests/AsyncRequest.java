package day16;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class AsyncRequest {

	public static void main(String[] args) {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					  .uri(new URI("https://kaushik1.free.beeceptor.com"))
					  .headers("Content-Type", "text/plain;charset=UTF-8")
					  .POST(HttpRequest.BodyPublishers.ofString("user=kaushik"))
					  .build();
//			int result=-1;
			CompletableFuture<HttpResponse<String>> response = client
					  .sendAsync(request, HttpResponse.BodyHandlers.ofString()); 
			
			response.thenAccept(r-> System.out.println(r.body()));
			
			System.out.println("async work started");
			while(!response.isDone()) {
				System.out.print(". ");
				Thread.sleep(1000);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
