package threads12;

import java.util.concurrent.*;

public class ExecuticeService {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        
        executorService.submit(() -> {
            System.out.println("Task 1 executing");
        });
        
        executorService.submit(() -> {
            System.out.println("Task 2 executing");
        });
        
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                return 42;
            }
        });
        
        System.out.println(future.isDone());
        Integer result;
		try {
			result = future.get(3,TimeUnit.SECONDS);
			System.out.println(result);
			System.out.println(future.isDone());
		} catch (InterruptedException | ExecutionException | TimeoutException  e) {
			e.printStackTrace();
		}
        
        
		
        executorService.shutdown();
    }
}
