package Practice.july17_Networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
	
	private final Socket client;

	public ClientHandler(Socket client) {
		this.client = client;
	}
	
	public void run() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			BufferedWriter output=new BufferedWriter(new OutputStreamWriter(this.client.getOutputStream()));
		
			String line=input.readLine();
			System.out.println(line);
			String greet="Hello Server.\n";
			output.write(greet);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				this.client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
