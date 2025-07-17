package Project;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SimpleServerChannel {
	public static void main(String [] args) {
		try(ServerSocketChannel serverChannel=ServerSocketChannel.open()){
			serverChannel.socket().bind(new InetSocketAddress(5000));
			System.out.println("Server waiting o connect at port : %s".formatted(serverChannel.socket().getInetAddress()));
			while(true) {
				SocketChannel clientChannel=serverChannel.accept();
				System.out.println("Server connected to client "+clientChannel.socket().getRemoteSocketAddress());
				ByteBuffer buffer=ByteBuffer.allocate(1024);
				int readBytes=clientChannel.read(buffer);
				System.out.println("From cient "+buffer.toString());
				if(readBytes>0) {
					buffer.flip();
					clientChannel.write(ByteBuffer.wrap("This is server Response...".getBytes()));
					while(buffer.hasRemaining()) {
						clientChannel.write(buffer);
					}
				}
				buffer.clear();
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
