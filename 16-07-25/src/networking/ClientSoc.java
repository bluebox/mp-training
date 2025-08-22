package networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSoc {

	public static void main(String[] args) throws  Exception {
		
		String ip="localhost";
		int port=9999;
		
		
		
		Socket s=new Socket(ip,port);
		String str="charan";
		OutputStreamWriter os=new OutputStreamWriter(s.getOutputStream());
		PrintWriter out=new PrintWriter(os);
		out.println(str);
		out.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String Substr = br.readLine();
		
		System.out.println("c:data from server "+Substr);

	}

}
