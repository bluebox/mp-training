package httpIntro;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import com.sun.net.httpserver.HttpServer;

public class HttpServerExample {
	public static int visitorCounter = 0;

	public static void main(String[] args) {

		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
			server.createContext("/", exchange -> {
				String requestMethod = exchange.getRequestMethod();
				System.out.println("Request Method: " + requestMethod);

				String data = new String(exchange.getRequestBody().readAllBytes());
				System.out.println("Body data : " + data);
				if (requestMethod.equals("POST")) {
					visitorCounter++;
				}
				// For Get
//				String response = """
//						<html>
//							<body>
//								<h1> hello world from http server </h1>
//								<p> number of Visitors who signed up = %d</p>
//								<form method= "post">
//									<button>submit</button>
//								</form>
//							</body>
//						</html>
//						""".formatted(visitorCounter);

				String firstName = data.split("&")[0].split("=")[1];
				String lastName = data.split("&")[1].split("=")[1];

				String response = """
						<html>
							<body>
								<h1> hello world from http server </h1>
								<p> number of Visitors who signed up = %d</p>
								<p> First Name : %s</p>
								<p>  last Name : %s</p>
								<form method= "post">
									<button>submit</button>
								</form>
							</body>
						</html>
						""".formatted(visitorCounter, firstName == null ? "" : firstName,
						lastName == null ? "" : lastName);

				try {

					TimeUnit.SECONDS.sleep(5);

					var bytes = response.getBytes();
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);
					exchange.getResponseBody().write(bytes);
					exchange.close();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			});
			server.start();
			System.out.println("server is listening on port 8080....");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
