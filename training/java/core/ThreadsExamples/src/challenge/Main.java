package challenge;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int size = 5;
        Resource resource = new Resource(size);
        ExecutorService executor = Executors.newFixedThreadPool(4); 
        executor.execute(new Producer(resource.getBuffer()));
        executor.execute(new Producer(resource.getBuffer()));
        executor.execute(new Consumer(resource.getBuffer()));
        executor.execute(new Consumer(resource.getBuffer()));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executor.shutdownNow();
            System.out.println("Executor service shut down.");
        }));
    }
}
