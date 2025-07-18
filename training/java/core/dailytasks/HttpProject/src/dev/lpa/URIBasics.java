package dev.lpa;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URIBasics {

    public static void main(String[] args) {

        URI baseSite = URI.create("https://learnprogramming.academy");
        URI timsSite = URI.create(
                "courses/complete-java-masterclass");
        print(timsSite);

        try {
            URI uri = new URI(
                    "http://user:pw@store.com:5000/products/phones?os=android#samsung");
            print(uri);

            URI masterClass = baseSite.resolve(timsSite);
            URL url = masterClass.toURL();
            System.out.println(url);
            print(url);
        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void print(URI uri) {

        System.out.printf("""            
                ----------------------------------------------
                [scheme:]scheme-specific-part[#fragment]
                ----------------------------------------------
                Scheme: %s
                Scheme-specific part: %s
                  Authority: %s
                    User info: %s
                    Host: %s
                    Port: %d
                    Path: %s
                    Query: %s
                Fragment: %s
                """,
                uri.getScheme(),
                uri.getSchemeSpecificPart(),
                uri.getAuthority(),
                uri.getUserInfo(),
                uri.getHost(),
                uri.getPort(),
                uri.getPath(),
                uri.getQuery(),
                uri.getFragment()
        );
    }

    private static void print(URL url) {

        System.out.printf("""            
                ----------------------------------------------
                  Authority: %s
                    User info: %s
                    Host: %s
                    Port: %d
                    Path: %s
                    Query: %s
                """,
                url.getAuthority(),
                url.getUserInfo(),
                url.getHost(),
                url.getPort(),
                url.getPath(),
                url.getQuery()
        );
    }
}
