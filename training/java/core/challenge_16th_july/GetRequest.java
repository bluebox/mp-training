package challenge_16th_july;

import java.io.*;
import java.net.*;

public class GetRequest {

    public static void main(String[] args) throws IOException {
    	StringBuilder s=new StringBuilder();
    	URL url=new URL("https://www.geeksforgeeks.org/java/how-to-set-up-a-basic-http-server-in-java/");
    	HttpURLConnection conn=(HttpURLConnection)url.openConnection();
    	conn.setRequestMethod("GET");
    	BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
    	
    	String line="";
    	while((line=br.readLine())!=null)
    	{
    		s.append(line);
    	}
    	System.out.println(s.toString());
        
    }
}
