package http;

import java.net.URI;

public class URIexample {
	public static void main(String[] args) {

		URI site = URI.create("https://learnprogramming.academy/courses");
		print(site);
	}

	private static void print(URI uri) {
		System.out.printf("""
				--------------------------------------------------
				[Scheme :] scheme-specific-part[#fragment]
				--------------------------------------------------
				Scheme: %s
				Scheme-specific part : %s
				Authority: %s
				user info: %s
				host %s
				port: %s
				Path: %s
				Query : %s
				Fragment: %s
				""", uri.getScheme(), uri.getSchemeSpecificPart(), uri.getAuthority(), uri.getUserInfo(), uri.getHost(),
				uri.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment());
	}
}
