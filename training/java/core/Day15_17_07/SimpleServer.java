package Day15_17_07;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class SimpleServer {
	public static int count=0;
	public static void main(String[] args) {
		try {
			HttpServer server=HttpServer.create(new InetSocketAddress(8080),0);
			server.createContext("/",exchange->{
				String requestMethod=exchange.getRequestMethod();
				System.out.println("request Method: "+requestMethod);
				String data=new String(exchange.getRequestBody().readAllBytes());
				System.out.println("Body data: "+data);
				exchange.getRequestHeaders().entrySet().forEach(System.out::println);
				if(requestMethod.equals("POST")) {
					count++;
				}
				String response="""
						<html>
							<body>
								<h1>Hello response</h1>
								<p>%d</p>
								<form method="post">
									<input type="submit" value="Submit">
								</form>
							</body>
						</html>
						""".formatted(count);
				byte[] bytes=response.getBytes();
				exchange.sendResponseHeaders(200, bytes.length);
				exchange.getResponseBody().write(bytes);
				exchange.close();
			});
			server.start();
			System.out.println("Server is listening on port 8080");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
