package Practice.july19_NetworkingHttpServerAndClientAsync;

import static java.net.HttpURLConnection.HTTP_OK;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import Practice.july18_NetworkingHttpServerAndClient.HttpClient1;

public class HttpClientAsync {
	private static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
			try {
				System.out.println("Enter your firstName");
				String firstName=sc.nextLine();
				System.out.println("Enter your lastName");
				String lastName=sc.nextLine();
				String myData=firstName+" "+lastName;
				URL myurl=new URL("http://localhost:8080");
		        HttpClient client = HttpClient.newHttpClient();
		        HttpRequest request = HttpRequest.newBuilder()
		        		.POST(BodyPublishers.ofString(myData))
		        		.uri(myurl.toURI())
		        		.header("Content-Type","application/x-www-form-urlencoded")
						.headers("Accept","text/html")
						.timeout(Duration.ofSeconds(30))
						.build();
				// HttpURLConnection connection = (HttpURLConnection) myurl.openConnection();
				 CompletableFuture<HttpResponse<Stream<String>>> responseFuture= client.sendAsync(request,
						 HttpResponse.BodyHandlers.ofLines());
				 responseFuture.thenAccept(r->handelResponse(r));
				 responseFuture.thenApply(r->filterResponse(r)).thenAccept(s->printResponse(s));
				System.out.println("TEN JOBS TO DO BESIDES HANDLING THE RESPONSE.");
				int job=0;
				while(job++<10) {
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Current job is"+job);
				}
				
				
			}
			catch (IOException |InterruptedException | URISyntaxException e) {
				System.out.println(e.getMessage());
			}
		}
		public static void getValues(String line) {
			String[] value=line.split("=");
			String myResult=value[value.length-1].replaceAll("\"", "").replaceAll(">", "").trim();
			System.out.println(myResult);
		}
		private static void handelResponse( HttpResponse<Stream<String>> response) {
			if(response.statusCode()==HTTP_OK) {
				response.body().filter(val->val.contains("type=\"text\""))
				.forEach(HttpClient1::getValues); 
			}
			else {
				System.out.println("Error in reading the given url");
			}
		}
		private static Stream<String> filterResponse( HttpResponse<Stream<String>> response) {
			System.out.println("iltering the response");
			if(response.statusCode()==HTTP_OK) {
				return response.body().filter(s->s.contains("type=\"text\""));
			}
			else {
				System.out.println("Error in reading the given url");
				return Stream.empty();
			}
		}
		private static void printResponse(Stream<String> response) {
			System.out.println("Printing Response");
			response.forEach(System.out::println);
		}

}
