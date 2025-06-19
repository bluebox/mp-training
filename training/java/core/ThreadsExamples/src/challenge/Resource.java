package challenge;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Resource {
    private final BlockingQueue<String> buffer;

    public Resource(int capacity) {
        buffer = new ArrayBlockingQueue<>(capacity);
    }

    public BlockingQueue<String> getBuffer() {
        return buffer;
    }
}
