package servers;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class CreatingHttpServer {
	public static void main(String args[]) throws IOException
	{
		HttpServer server=HttpServer.create(new InetSocketAddress(7000),0);
		server.createContext("/",new Print());
		server.setExecutor(null);
		server.start();
		System.out.println("Server started listending on 8000");
		
	}
	
	static class Print implements HttpHandler
	{
		public void handle(HttpExchange exchange) throws IOException 
		{
			String res="Hello! my dear brother";
			exchange.sendResponseHeaders(200, res.length());
			OutputStream os=exchange.getResponseBody();
			os.write(res.getBytes());
		}
	}

}
