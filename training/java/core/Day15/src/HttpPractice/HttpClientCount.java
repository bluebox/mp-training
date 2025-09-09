package HttpPractice;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpServer;

import static java.net.HttpURLConnection.HTTP_OK;


public class HttpClientCount {
	private static long click=0;
	public static void main(String[]args) {
		
	
	
	try {
		HttpServer server = HttpServer.create(new InetSocketAddress(8080),0);
		server.createContext("/",exchange ->{
			String requestMethod = exchange.getRequestMethod();
			System.out.println("REquest Method: " + requestMethod);
			
			String data = new String(exchange.getRequestBody().readAllBytes());
			System.out.println("Body data: " + data);
			
			Map<String, String> parameters = parseparameters(data);
			System.out.println(parameters);
			
			if (requestMethod.equals("POST")) {
				click++;
			}
			String response = """
					<html> 
						<body>
							<p> Number of POST'S count = %d</p>
							<form method = "post">
								<input type="Submit" value="Click">
							</form>
						</body>
					</html>
					""".formatted(click);
			var bytes = response.getBytes();
			exchange.sendResponseHeaders(HTTP_OK, bytes.length);
			exchange.getResponseBody().write(bytes);
		});
		server.start();
		System.out.println("Server Started on 8080");
	} catch (IOException e){
		throw new RuntimeException(e);
	}
}
	private static Map<String, String> parseparameters(String requestBody){
		
		Map<String, String> parameter = new HashMap<>();
		String[] pairs = requestBody.split("&");
		for (String pair : pairs) {
			String[] KeyValue = pair.split("=");
			if (KeyValue.length == 2) {
				parameter.put(KeyValue[0], KeyValue[1]);
			}
		}
		return parameter;
		
	}
}


