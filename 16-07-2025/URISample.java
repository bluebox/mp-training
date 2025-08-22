package networks;

import java.net.URI;
import java.net.URISyntaxException;

public class URISample {
    public static void main(String[] args) {
        try {
            URI uri = new URI("https://www.example.com:8080/path/to/resource?param1=value1#section");

            System.out.println("Scheme: " + uri.getScheme());
            System.out.println("Host: " + uri.getHost());
            System.out.println("Port: " + uri.getPort());
            System.out.println("Authority: " + uri.getAuthority());
            System.out.println("Path: " + uri.getPath());
            System.out.println("Query: " + uri.getQuery());
            System.out.println("Fragment: " + uri.getFragment());
            System.out.println("RawPath: " + uri.getRawPath());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}