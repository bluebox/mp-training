import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimpleHttpPost {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        String requestBody = "{\"Name\": \"Prabhas\", \"Industry\": \"Telugu\", \"Rank\": 1}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Status Code: " + response.statusCode());
            System.out.println("Response Body:\n" + response.body());
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}