package Practice.july16_Networking;
import java.net.*;
import java.io.*;

class HttpServer{

    public static void main(String args[]){

        try{
            // Create a new server socket and listen on port 9000
            try (ServerSocket server = new ServerSocket(5050)){

                // Continue to listen for client connections
                while (true){

                    // Accept a client connection. accept() is a blocking method.
                    Socket client = server.accept();

                    Thread thread = new Thread(new ClientHandler(client));
                    thread.start();
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
