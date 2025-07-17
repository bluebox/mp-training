package dev.tulasidhar.july17.http_practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class HttpServerMock {
	public static void main(String[] args) {
		try {
			HttpServer server  = HttpServer.create(new InetSocketAddress("0.0.0.0",8008), 0);
			server.createContext("/",(request)->{
				System.out.println(request.getRequestMethod());
								
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getRequestBody()));
				System.out.println(reader.readLine());
				String response = """
					    <!DOCTYPE html>
					    <html>
					        <head>
					            <style>
					                body { font-family: Arial; margin: 20px; }
					                .card { 
					                    background: white;
					                    padding: 15px;
					                    border-radius: 5px;
					                    box-shadow: 0 2px 4px #0002;
					                }
					                button {
					                    background: #4CAF50;
					                    color: white;
					                    border: none;
					                    padding: 8px 16px;
					                    border-radius: 4px;
					                }
					                button:hover{
						                background: #2980b9;
					                }
					            </style>
					        </head>
					        <body>
					            <div class="card">
					                <h2>Hello from Mock Server! This is Tulasidhar</h2>
					                <p>This is a simple response page.</p>
					                <button>Click me</button>
					            </div>
					        </body>
					    </html>
					""";

				request.sendResponseHeaders(200,  response.length());
				request.getResponseBody().write(response.getBytes());
			});
			
			server.start();
			System.out.println("server started and listening on port 8008");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
