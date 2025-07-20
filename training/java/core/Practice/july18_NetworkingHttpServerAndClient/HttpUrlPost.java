package Practice.july18_NetworkingHttpServerAndClient;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import static java.net.HttpURLConnection.HTTP_OK;
public class HttpUrlPost {

	public static void main(String[] args) {
		String url="http://localhost:8080";		
		try {
			URL myurl=new URL(url);
			HttpURLConnection connection = (HttpURLConnection) myurl.openConnection();			
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.addRequestProperty("Content-Type","application/json");
			connection.addRequestProperty("Accept", "html");
			connection.setReadTimeout(30000);
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			String request="firstName=Deepika&lastName=Talluri";
			int length=request.getBytes().length;
			connection.setRequestProperty("Content-Length", String.valueOf(length));
			DataOutputStream out=new DataOutputStream(connection.getOutputStream());
			out.write(request.getBytes());
			out.flush();
			out.close();
			int code=connection.getResponseCode();
			System.out.println("Response"+code);		
			if(code!=HTTP_OK) {
				System.out.println("Error while reading"+code);
			}
			printContents(connection.getInputStream());				
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void printContents(InputStream in) {
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(in))) {
			String line;
			while((line=reader.readLine())!=null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
