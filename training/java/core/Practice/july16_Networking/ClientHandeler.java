package Practice.july16_Networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

class ClientHandler implements Runnable{

	    private Socket client = null;

	    public ClientHandler(Socket client){
	        this.client = client;
	    }

	    public void run(){

	        try{
	            // Get A BufferedReader/BufferedWriter to handle reading and writing to the stream.

	            BufferedReader requestReader = 
	                      new BufferedReader(new InputStreamReader(this.client.getInputStream()));
	            BufferedWriter responseWriter = 
	                      new BufferedWriter(new OutputStreamWriter(this.client.getOutputStream()));

	            // Important, we need to read all the data sent from 
	            // the client before we can send a response.

	            while (true){
	                String headerLine = requestReader.readLine();

	                if (headerLine.length() == 0){
	                    break;
	                }
	            }

	            // How original is this?
	            responseWriter.write("Hello World\n");
	            responseWriter.flush();

	            // Closing the client connection will close, both the input and output streams.
	            this.client.close();

	        }catch(IOException e){
	            try{
	                if(this.client != null){
	                    this.client.close();
	                }
	            }catch(IOException e2){

	            }
	        }
	    }
}
