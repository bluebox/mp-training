package Practice.july19_NetworkingHttpServerAndClientAsync;

import static java.net.HttpURLConnection.HTTP_OK;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.sun.net.httpserver.HttpServer;

public class HttpServerAsync {
	private static Integer visitorCount=0;
	private static String firstName="";
	private static String lastName="";
	public static void main(String[] args) {
		try {
			HttpServer server=HttpServer.create(new InetSocketAddress(8080),0);
			server.createContext("/",exchange->{
					String request=exchange.getRequestMethod();
					if(request.equals("POST")) {
						visitorCount++;
						String data=new String(exchange.getRequestBody().readAllBytes(),StandardCharsets.UTF_8);
						Map<String,String> fields=fieldMap(data);
						firstName=fields.getOrDefault("firstName","No Data");
						lastName=fields.getOrDefault("lastName","No Data");
					}
					String response="""
							<HTML>
							<head>
							<title>Welcome</title>
							</head>
							<body>
							<p>Welcome to my first lerning page</p>
							<h1>VISITERCOUNTER: %d</h1>
							<form method="post">
							<label for="name">FirstName</label>
							<input type="text" id="name" value="%s">
							<label for="sur">LastName</label>
							<input type="text" id="sur" value="%s">
							<input type="submit" value="visit">
							</form>
							</body>
							</HTML>
							""".formatted(visitorCount,firstName,lastName);
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					var bytes=response.getBytes();
					exchange.sendResponseHeaders(HTTP_OK, bytes.length);
					exchange.getResponseBody().write(bytes);
					exchange.close();
					System.out.println("Server responded");
					});
			server.start();
			System.out.println("Server is waiting for the request");
			
		}
		
		catch(IOException e) {
			System.out.println("hi"+e.getMessage());
		}
	}
	public static Map<String,String> fieldMap(String str){
		String[] list=str.trim().split(" ");
		Map<String,String> result=new LinkedHashMap<>();
		result.put("firstName", list[0]);
		result.put("lastName", list[1]);
		return result;
	}
}
