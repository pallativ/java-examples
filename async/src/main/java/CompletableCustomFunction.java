import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


// The following is not executed asyncronously.

public class CompletableCustomFunction {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Start of Method");

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()-> 2);
        future.thenAccept(t -> {
            process();
        }).whenComplete((aVoid, throwable) -> {
            System.out.println("When Complete");
        });

        System.out.println("End of Method");
    }

    public static CompletableFuture<Integer> process() {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        future.thenAccept(t -> {
            System.out.println("Executing Task");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // This function complete
        future.complete(10);
        return future;
    }
}


