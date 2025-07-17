package Day15_17_07;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPExamplePost {
	public static void main(String[] args) {
		try {
			URL url=new URL("http://localhost:8080");
//			URL url=new URL("http://example.com/extra");
			HttpURLConnection conn =(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", "Chrome");
			conn.setRequestProperty("Accept","application/json,text/html");
			conn.setReadTimeout(300000);
			
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			String parameters="first=saketh&last=Ravikanti";
			int length=parameters.getBytes().length;
			conn.setRequestProperty("content-length",String.valueOf(length));
			DataOutputStream out=new DataOutputStream(conn.getOutputStream());
			out.writeBytes(parameters);
			out.flush();
			out.close();
			
			
			int responseCode=conn.getResponseCode();
			System.out.println("Respose code:"+responseCode);
			
			if(responseCode!=200) {
				System.out.println("Error in reading the page");
				return;
			}
			printer(conn.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void printer(InputStream st) {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(st));){
			String line;
			while((line=br.readLine())!=null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
