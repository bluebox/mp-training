package networks;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Map;

public class HttpWorkingClient {
	
    public static void main(String[] args) {
        String url1 = "https://resources.docs.salesforce.com/latest/latest/en-us/sfdc/pdf/salesforce_useful_validation_formulas.pdf";
        String url2 = "https://fastly.picsum.photos/id/112/600/600.jpg?hmac=hakYJ0LrbvOQ1fnbvBGE1ThGxufcyCWKNAfXrctqyWQ";
        String url3 = "https://picsum.photos/200/300";

        System.out.println("First URL Information");
        printHeaders(url1);

        System.out.println();
        System.out.println("Second URL Information");
        printHeaders(url2);

        System.out.println();
        System.out.println("Third URL Information");
        printHeaders(url3);
    }

    public static void printHeaders(String fileUrl) {
    	
        try {
            URL url = new URI(fileUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Map<String, List<String>> headers = connection.getHeaderFields();

            for (Map.Entry<String, java.util.List<String>> entry : headers.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            connection.disconnect();
            
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}