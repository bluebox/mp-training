package uri;
import java.net.*;

public class uriclass {
	public static void main(String[] args) throws URISyntaxException {
		String website="https://www.javaprogramto.com/2020/04/java-8-14-stream-terminal-operations.html";
		URI weburi=new URI("https","en.wikipedia.org","/wiki/URI_normalization","Normalization_Process");
		getURI(weburi);
		//https://en.wikipedia.org/wiki/URI_normalization
	}
	static void getURI(URI myUri) {
		System.out.println(myUri.getAuthority());
		System.out.println(myUri.getPath());
		System.out.println(myUri.getHost());
		System.out.println(myUri.getPort());
		System.out.println(myUri.getScheme());
		System.out.println(myUri.getAuthority());



		
	}

}
