package Mysql_database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;

public class Httpchallenge {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		HttpClient client=HttpClient.newBuilder().version(Version.HTTP_1_1).build();
		Map<Integer,String> orders=new HashMap<>();
		orders.put(1, "blue jeans");
		orders.put(2, "red jeans");
		orders.put(3, "orange jeans");
		orders.put(4, "green jeans");
		
		
		sendPosts(client,URI.create("https://www.google.com"),"",orders);
		
		
	}
	public static void sendPosts(HttpClient client,URI u,String str,Map<Integer,String> orders) throws IOException, InterruptedException {
		try {
			FileWriter writer=new FileWriter("C:\\Users\\Santhosh\\eclipse-workspace\\DailyProblems\\src\\Weekproblems\\output.txt.txt");
			BufferedWriter bufferedwriter=new BufferedWriter(writer);
		
		for(Map.Entry<Integer, String> entry:orders.entrySet()) {
			str+=entry.getValue();
		HttpRequest request=HttpRequest.newBuilder()
				.uri(u)
				.header("Content-Type", "String")
				.POST(HttpRequest.BodyPublishers.ofString(entry.getValue()))
				.build();

        HttpResponse<String> futureResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(futureResponse.body());
        str+=futureResponse.body();
        
	}
		bufferedwriter.write(str);
		bufferedwriter.close();
		}
		catch(IOException e) {
			System.out.println("error"+e.getMessage());
		}

		
		
	}
		
	

}
