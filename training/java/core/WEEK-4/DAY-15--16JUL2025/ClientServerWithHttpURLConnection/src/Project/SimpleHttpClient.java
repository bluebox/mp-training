package Project;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class SimpleHttpClient {
	
	public static void readResponse(BufferedReader reader) throws IOException {
		String line;
		while((line=reader.readLine())!=null){
			System.out.println(line);
		}
	}
	
	public static void main(String [] args) throws IOException {
		URL url=URI.create("http://localhost:8080").toURL();
		
//		Get Request
		HttpURLConnection get=(HttpURLConnection) url.openConnection();
		get.setRequestMethod("GET");
		get.setRequestProperty("User-Agent", "Chrome");
		get.setRequestProperty("Accept", "text/html");
		get.setReadTimeout(20000);
		
		int responseCode=get.getResponseCode();
		if(responseCode!=HttpURLConnection.HTTP_OK) {
			System.out.println("Connection not succesful...");
			return;
		}
		BufferedReader getReader=new BufferedReader(new InputStreamReader(get.getInputStream()));
		readResponse(getReader);
		getReader.close();
		
//		Post Request
		HttpURLConnection post=(HttpURLConnection)url.openConnection();
		post.setRequestMethod("POST");
		post.setRequestProperty("User-Agent", "Chrome");
		post.setRequestProperty("Accept", "text/html");
		post.setReadTimeout(20000);
		
		post.setDoOutput(true);
		
		String parameters="firstName=rohit&lastName=datla";
		post.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		byte[] byteParams=parameters.getBytes("UTF-8");
		post.setRequestProperty("Content-Length",String.valueOf(byteParams.length));
		
		DataOutputStream output=new DataOutputStream(post.getOutputStream());
		output.write(byteParams);
		output.flush();
		output.close();
		
		responseCode=post.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
		    System.out.println("Request was successful!");
		} else {
		    System.out.println("Error: " + responseCode);
		}

		
		BufferedReader postReader=new BufferedReader(new InputStreamReader(post.getInputStream()));
		readResponse(postReader);
		postReader.close();
		
	}
}
