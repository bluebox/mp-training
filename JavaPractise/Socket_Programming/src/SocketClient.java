import java.io.DataOutputStream;
import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) throws Exception {
		Socket s=new Socket("localhost",6666);
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		dout.writeUTF("Hi Sending from Client");
		dout.flush();
		dout.close();
		s.close();

	}

}
