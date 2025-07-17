package serversocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class servercl {
	public static void main(String[] args) {
		ServerSocket server=null;
		try {
			server=new ServerSocket(1234);
			server.setReuseAddress(true);
			while(true) {
				Socket client=server.accept();
				System.out.println("New client connected"+client.getInetAddress().getHostAddress());
				ClientHandler cl=new ClientHandler(client);
				new Thread(cl).start();
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(server!=null) {
				try {
					server.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//clienthandler class
	private static class ClientHandler implements Runnable{
		private final Socket ClientSocket;
		// Constructor
        public ClientHandler(Socket socket)
        {
            this.ClientSocket = socket;
        }

        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                  
                  // get the outputstream of client
                out = new PrintWriter(
                    ClientSocket.getOutputStream(), true);

                  // get the inputstream of client
                in = new BufferedReader(
                    new InputStreamReader(
                        ClientSocket.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {

                    // writing the received message from
                    // client
                    System.out.printf(
                        " Sent from the client: %s\n",
                        line);
                    out.println(line);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        ClientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

	}

