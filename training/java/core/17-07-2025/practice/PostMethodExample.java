package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostMethodExample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		URL url=new URL("https://postman-echo.com/post");
		HttpURLConnection con=(HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		String postData="name=tarun&age=21";
		OutputStream os=con.getOutputStream();
		os.write(postData.getBytes());
        os.flush();
        os.close();
        StringBuffer s=new StringBuffer();
        BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        while(( line=br.readLine())!=null)
        {
        	s.append(line);
        }
        System.out.println(s.toString());
	}

}
