package dev.tulasidhar.july17.http_practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlPractice {
	public static void main(String[] args) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL("http://example.com").openConnection();
			conn.setRequestMethod("GET");
			//conn.setRequestProperty("User-Agent", "Chrome");
			conn.setRequestProperty("Accept", "application/json,text/html");
			System.out.println(conn.getResponseCode());
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String print;
			do {
				print= reader.readLine();
				System.out.println(reader.readLine());
				
				print = reader.readLine();
			}while(!(print == null));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
}
