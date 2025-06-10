

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTaskExample {

    // Sample task representing a customer operation
    static class BankTask implements Runnable {
        private String customerName;
        private String operation;

        public BankTask(String customerName, String operation) {
            this.customerName = customerName;
            this.operation = operation;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started " + customerName + "'s " + operation + " task.");
            try {
                // Simulate some delay in operation
                Thread.sleep(2000); // 2 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " completed " + customerName + "'s " + operation + " task.");
        }
    }

    public static void main(String[] args) {
        // Create a thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit multiple customer tasks
        executor.submit(new BankTask("Alice", "Deposit"));
        executor.submit(new BankTask("Bob", "Withdraw"));
        executor.submit(new BankTask("Charlie", "Check Balance"));
        executor.submit(new BankTask("David", "Deposit"));
        executor.submit(new BankTask("Eve", "Withdraw"));

        // Initiates shutdown after all tasks are submitted
        executor.shutdown();

        try {
            // Wait for all tasks to finish or timeout
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Force shutdown if tasks take too long
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("All tasks finished.");
    }
}

