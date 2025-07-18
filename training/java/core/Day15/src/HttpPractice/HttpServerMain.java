package HttpPractice;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import static java.net.HttpURLConnection.HTTP_OK;


public class HttpServerMain {
	
	public static void main(String[]args) {
		
	
	
	try {
		HttpServer server = HttpServer.create(new InetSocketAddress(8080),0);
		server.createContext("/",exchange ->{
			String requestMethod = exchange.getRequestMethod();
			System.out.println("REquest Method: " + requestMethod);
			String response = """
					<html>
						<head>
						<style>
						h1 {text-align: center;
						}
						h2 {text-align: center;
						}
						</style>
						</head>
						<body>
							<h2> Hello </h2>
							<h1>VARDHAN RANGINENI</h1>
						</body>
					</html>
					""";
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
}


