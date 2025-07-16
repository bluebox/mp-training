package serverClientUsingSockets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ChannelSelectorServer {
	public static void main(String[] args) {

		try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {

			serverChannel.socket().bind(new InetSocketAddress(5000));
			serverChannel.configureBlocking(false);
			Selector selector = Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {

				selector.select();
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectedKeys.iterator();

				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					iterator.remove();
					if (key.isAcceptable()) {
						SocketChannel clientChannel = serverChannel.accept();

						System.out.println("Client Connected " + clientChannel.getLocalAddress());
						clientChannel.configureBlocking(false);
						clientChannel.register(selector, SelectionKey.OP_READ);
					} else if (key.isReadable()) {
						echoData(key);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void echoData(SelectionKey key) throws IOException {
		SocketChannel clientChannel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		int bytesRead = clientChannel.read(buffer);
		if (bytesRead > 0) {

			buffer.flip();
			byte[] data = new byte[buffer.remaining()];
			buffer.get(data);
			String message = "Echo: " + new String(data);
			clientChannel.write(ByteBuffer.wrap(message.getBytes()));

		} else if (bytesRead == -1) {
			System.out.println(" Client disconnected " + clientChannel.getRemoteAddress());
			clientChannel.close();
		}

	}
}
