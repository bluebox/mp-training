package Practice.july18_NetworkingHttpServerAndClient;

//import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.net.HttpURLConnection.HTTP_OK;

import java.io.IOException;
import java.net.URISyntaxException;
public class HttpClient1 {
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
			 HttpResponse<Stream<String>> response= client.send(request,
					 HttpResponse.BodyHandlers.ofLines());
				
			if(response.statusCode()!=HTTP_OK) {
			  System.out.println("An error encountered while reading the url: "+myurl);
			  return;
			}
			//response.body().forEach(System.out::println); 
			response.body().filter(val->val.contains("type=\"text\"")).forEach(HttpClient1::getValues); 
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
}
