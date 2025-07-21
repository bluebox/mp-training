package Project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleHttpClient {
	public static void main(String [] args) throws IOException {
		Path path=Path.of("testHtmlFile.html");
		if(!Files.exists(path)) {
			Files.createFile(path);
		}
		BufferedWriter writer=Files.newBufferedWriter(path);
		URL url=URI.create("https://example.com").toURL();
		System.out.println(url.getAuthority());
		try {
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent","Chrome");
			conn.setRequestProperty("Accept", "application/json, text/html");
			conn.setReadTimeout(20000);
			
			int responseCode=conn.getResponseCode();
			if(responseCode!=conn.HTTP_OK) {
				System.out.println("Connection failed...");
				return;
			}
			BufferedReader input=new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line=input.readLine())!=null) {
				writer.write(line);
				writer.newLine();
				System.out.println(line);
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
