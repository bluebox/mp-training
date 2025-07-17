package HttpProject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class HttpServers {
	
	private static int counter=0;

	public static void main(String[] args) {

		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
			
			server.createContext("/", exchange->{
				String request = exchange.getRequestMethod();
				System.out.println("Requested Method: "+request);
				
				if(request.equals("POST")) {
					counter++;
				}
				
				String response = """
						<html>
							<body>
								<h1> Hello World</h1>
								<p> Number of Visitors = %d</p>
								<form method="post">
								<input type = "submit" value = "submit">
								</form>
							</body>
						</html>
						""".formatted(counter);
				
				
				var bytes= response.getBytes();
				
//				System.out.println(Arrays.toString(bytes));
				
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);
				exchange.getResponseBody().write(bytes);
				exchange.close();
;			});
			
			server.start();
			System.out.println("Server is listening on port 8080");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
