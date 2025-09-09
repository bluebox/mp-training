package MultithreadedServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
    	ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
        	
        	while (true) {
            Socket socket = serverSocket.accept(); 
                System.out.println("connection estabilished with client");
                socket.setSoTimeout(100000);
                executorService.submit(() -> handelClientRequest(socket));
        	}
            }catch (IOException e) {
					System.out.println("Client Resoures closed ");
				}
            }
    private static void handelClientRequest (Socket socket) {
    	try (socket;
    			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	        PrintWriter output =new PrintWriter(socket.getOutputStream(), true);
    			){
    		while (true) {
                String echoString = input.readLine();
                System.out.println("Client request : " + echoString);
                if (echoString.equals("exit")) {
                    break;
                }
                output.println("Reply from server: " + echoString);
    	}
    	}catch (Exception e) {
			System.out.println(e);
        }
    }	
	
}