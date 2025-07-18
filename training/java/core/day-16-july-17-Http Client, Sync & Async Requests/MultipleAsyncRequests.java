package day16;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class MultipleAsyncRequests {

	public static void main(String[] args) {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request1 = HttpRequest.newBuilder()
					  .uri(new URI("https://kaushik1.free.beeceptor.com"))
					  .headers("Content-Type", "text/plain;charset=UTF-8")
					  .POST(BodyPublishers.noBody())
					  .build();
			HttpRequest request2 = HttpRequest.newBuilder()
					  .uri(new URI("https://kaushik1.free.beeceptor.com/"))
					  .timeout(Duration.ofSeconds(10)) 
					  .version(HttpClient.Version.HTTP_2)
					  .GET()
					  .build();
			Thread dotsPrinter = new Thread(() ->{
				while (true) {
					System.out.print(". ");
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
//			dotsPrinter.setDaemon(true);
			dotsPrinter.start();
			List<CompletableFuture<HttpResponse<String>>> futures = new ArrayList<>();
			var future1 = client.sendAsync(request1, BodyHandlers.ofString());
			var future2 = client.sendAsync(request2, BodyHandlers.ofString());
			CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2);
			combinedFuture.join();
			System.out.println("Response-1");
			System.out.println(future1.get().body());
			System.out.println("Response-2");
			System.out.println(future2.get().body());
			System.out.println("Both requests completed");
		} catch(Exception e) {
			e.printStackTrace(); 
		}
	}
}
