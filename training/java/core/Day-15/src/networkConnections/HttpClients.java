package networkConnections;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;

public class HttpClients {
	public static void main(String[] args) {
		HttpClient con=java.net.http.HttpClient.newHttpClient();
		
		HttpRequest request=HttpRequest.newBuilder()
				.uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
				.GET()
				.build();
		try {
			HttpResponse<String> response=con.send(request, HttpResponse.BodyHandlers.ofString());
			String result=response.body();
			System.out.println(result);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}


