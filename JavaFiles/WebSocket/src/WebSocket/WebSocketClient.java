package WebSocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.Scanner;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class WebSocketClient {

	public static void main(String[] args) throws URISyntaxException, InterruptedException, ExecutionException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name to join chat: ");
		String name = sc.nextLine();

		HttpClient client = HttpClient.newHttpClient();

		WebSocket webSocket = client.newWebSocketBuilder()
				.buildAsync(new URI("ws://localhost:8080?name=%s".formatted(name)), new WebSocket.Listener() {

					@Override
					public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
						// TODO Auto-generated method stub
						System.out.println(data);
						return Listener.super.onText(webSocket, data, last);
					}

				}).join();

		while (true) {
			String input = sc.nextLine();
			if ("bye".equalsIgnoreCase(input)) {
				webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "User left").get();
				break;
			} else {
				webSocket.sendText(input, true);
			}
		}

	}

}
