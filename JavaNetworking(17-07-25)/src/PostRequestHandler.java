import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class PostRequestHandler {
	static void sendPost(HttpClient client) throws IOException, InterruptedException
	{
		HttpRequest request=(HttpRequest) HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
		.header("Content-Type","application/json")
		.POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"Sreeja\",\"role\":\"developer\"}")).build();
		HttpResponse<String>response=client.send(request,HttpResponse.BodyHandlers.ofString());
		System.out.println("POSTResponse:\n"+response.body());
	}
}