import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

    // Simulate a long-running task (like fetching data from DB)
    public static String fetchData() {
        try {
            Thread.sleep(2000); // simulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Data";
    }

    // Simulate processing the data
    public static String processData(String input) {
        return input + " -> Processed";
    }

    // Simulate saving data to DB
    public static String saveData(String input) {
        return input + " -> Saved";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Main thread: " + Thread.currentThread().getName());

        // Step 1: Fetch data asynchronously
        CompletableFuture<String> fetchFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching on: " + Thread.currentThread().getName());
            return fetchData();
        });

        // Step 2: Process data when fetch is done
        CompletableFuture<String> processFuture = fetchFuture.thenApplyAsync(data -> {
            System.out.println("Processing on: " + Thread.currentThread().getName());
            return processData(data);
        });

        // Step 3: Save data when processing is done
        CompletableFuture<String> saveFuture = processFuture.thenApplyAsync(result -> {
            System.out.println("Saving on: " + Thread.currentThread().getName());
            return saveData(result);
        });

        // Get the final result
        String finalResult = saveFuture.get(); // blocks and waits
        System.out.println("Final Result: " + finalResult);
    }
}
