package serverClientUsingSockets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class SimpleServerChennel {
	public static void main(String[] args) {

		try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
			serverChannel.socket().bind(new InetSocketAddress(5000));
			System.out.println("Server is listening on port" + serverChannel.socket().getLocalPort());

			List<SocketChannel> clientChannels = new ArrayList<>();

			while (true) {
				SocketChannel clientChannel = serverChannel.accept();

				if (clientChannel != null) {
					clientChannels.add(clientChannel);
					System.out.printf("Client %s connected %n", clientChannel.socket().getRemoteSocketAddress());
				}
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				for (SocketChannel channel : clientChannels) {

					int readBytes = channel.read(buffer);

					if (readBytes > 0) {
						buffer.flip();

						channel.write(ByteBuffer.wrap("Echo from server: ".getBytes()));
						while (buffer.hasRemaining()) {
							channel.write(buffer);
						}
						buffer.clear();
					} else if (readBytes == -1) {
						System.out.printf("Connection to %s lost%n", channel.socket().getRemoteSocketAddress());
						channel.close();
						clientChannels.remove(channel);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Server execption " + e.getMessage());
		}
	}
}
