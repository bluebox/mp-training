package httpClientConcurrentPostsChallenge;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.sun.net.httpserver.HttpServer;

public class OrderFulfillmentServer {

	public static int visitorCounter = 0;

	private static AtomicLong lastID = new AtomicLong(1);

	public static long getNextID() {

		return lastID.getAndIncrement();
	}

	public static void main(String[] args) {

		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

			server.createContext("/", exchange -> {
				String reqParameters = exchange.getRequestURI().toString();

				String requestMethod = exchange.getRequestMethod();
				System.out.println("Request Method: " + requestMethod);

				String data = "";
				String response = "";
				int responseCode = HttpURLConnection.HTTP_OK;
				if (requestMethod.equals("GET")) {
					data = reqParameters.substring(reqParameters.indexOf("?") + 1);
				} else if (requestMethod.equals("POST")) {
					data = new String(exchange.getRequestBody().readAllBytes());
				}

				System.out.println("Body " + data);

				Map<String, String> parameters = parseParameters(data);
				System.out.println(parameters);

				if (parameters.size() == 2) {
					LocalDateTime now = LocalDateTime.now();
					LocalDateTime delivery = now.plusDays(3);
					response = """
							{"order":
								{
									"orderId": "%010d",
									"product" : "%s",
									"amount":"%s",
									"orderReceived":"%s",
									"orderDeliveryDate":"%s"
								}
							}
							""".formatted(getNextID(), parameters.get("product"), parameters.get("amount"),
							now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
							delivery.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).replace("\\s", "");
					System.out.println(response);
				} else {
					response = "{ \"result\": \"Bad Data sent\"}";
					responseCode = HttpURLConnection.HTTP_BAD_REQUEST;
				}

				var bytes = response.getBytes();
				exchange.sendResponseHeaders(responseCode, bytes.length);
				exchange.getResponseBody().write(bytes);
				exchange.close();

			});
			server.start();
			System.out.println("server is listening on port 8080....");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static Map<String, String> parseParameters(String data) {

		Map<String, String> parmeters = new HashMap<>();

		String[] values = data.split("&");
		for (String value : values) {
			String[] map = value.split("=");
			parmeters.put(map[0], map[1]);
		}
		return parmeters;
	}
}
