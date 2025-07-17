package HttpProject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URIBasic {

	public static void main(String[] args) {
		URI site = URI.create("https://mail.google.com/mail/u/0/#inbox");
		print(site);

		try {
			URI uri = new URI("https://www.google.com/search?q=java");
			print(uri);

			try {
				URL url = uri.toURL();
				System.out.println(url);
				print(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void print(URI uri) {
		System.out.printf("""
				Scheme : %s
				Scheme-specific part : %s
				Authority : %s
				User info : %s
				Host : %s
				Port : %d
				Path : %s
				Query : %s
				Fragment : %s%n
				""", uri.getScheme(), uri.getSchemeSpecificPart(), uri.getAuthority(), uri.getUserInfo(), uri.getHost(),
				uri.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment());
	}

	public static void print(URL uri) {
		System.out.printf("""
				Authority : %s
				User info : %s
				Host : %s
				Port : %d
				Path : %s
				Query : %s%n
				""", uri.getAuthority(), uri.getUserInfo(), uri.getHost(), uri.getPort(), uri.getPath(),
				uri.getQuery());
	}
}
