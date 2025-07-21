package networkConnections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnection {
	public static void main(String[] args) {
		
		try {
			URL url=new URL("https://web.whatsapp.com/");
			URLConnection urlConnect=url.openConnection();
			BufferedReader reader=new BufferedReader(new InputStreamReader((InputStream) urlConnect.getContent()));
			while(reader.readLine()!=null) {
				System.out.println(reader.readLine());
			}
			System.out.println(urlConnect.getAllowUserInteraction());
			System.out.println(urlConnect.getDoInput());
			System.out.println(urlConnect.getDoOutput());
			System.out.println(urlConnect.toString());
			System.out.println(url.getHost());
			System.out.println(urlConnect.getHeaderFields());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
