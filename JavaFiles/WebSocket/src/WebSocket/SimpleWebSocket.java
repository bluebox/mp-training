package WebSocket;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class SimpleWebSocket extends WebSocketServer {

	public static final int SERVER_PORT = 8080;

	private static Map<String, String> map = new HashMap<>();

	@Override
	public void onClose(WebSocket webSocket, int arg1, String arg2, boolean arg3) {
		// TODO Auto-generated method stub
		System.out.println("Connection closed: " + webSocket.getRemoteSocketAddress());

	}

	@Override
	public void onError(WebSocket webSocket, Exception arg1) {
		// TODO Auto-generated method stub
		System.out.println("Error for: " + webSocket.getRemoteSocketAddress());

	}

	@Override
	public void onMessage(WebSocket webSocket, String arg1) {
		// TODO Auto-generated method stub

		String chatName = map.get(webSocket.getRemoteSocketAddress().toString());
		broadcast(webSocket, "%s : %s".formatted(chatName, arg1));
		System.out.println("Message Received: " + webSocket.getRemoteSocketAddress());

	}

	private void broadcast(WebSocket webSocket, String message) {
		var conn = new ArrayList<>(getConnections());
		conn.remove(webSocket);
		broadcast(message, conn);
	}

	@Override
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
		// TODO Auto-generated method stub
		var resource = webSocket.getResourceDescriptor();
		String name = resource.split("=")[1];
		map.put(webSocket.getRemoteSocketAddress().toString(), name);
		System.out.println(map.values());
		System.out.println("Connection opened: " + webSocket.getRemoteSocketAddress());
		broadcast(webSocket, "%s joined".formatted(name));
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		System.out.println("Server listening on port: " + getPort());

	}

	public SimpleWebSocket() {
		super(new InetSocketAddress(SERVER_PORT));
	}

	public static void main(String[] args) {

		var server = new SimpleWebSocket();
		server.start();
	}

}
