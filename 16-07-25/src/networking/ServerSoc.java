package networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSoc {

	/**
	 * @param args
	 * @throws Exception
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("s : server is started..");
		ServerSocket ss = new ServerSocket(9999);
		System.out.println("s : server is waiting for client request..");
		Socket s = ss.accept();
		System.out.println("s: client connected successfully..");

		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String str = br.readLine();

		System.out.println("s: clientdata:" + str);
		
		String subStr=str.substring(0,3);
		OutputStreamWriter os=new OutputStreamWriter(s.getOutputStream());
		PrintWriter out=new PrintWriter(os);
		out.println(subStr);
		
		out.flush();
		System.out.println("s:data sent from server to client....");
	}

}
