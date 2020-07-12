import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureExample {

    @Test
    public void BasicExample() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        future.thenAccept(t -> {
            System.out.println(t + " World");
        });
    }

    @Test
    public void BasicExampleWithSleep() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                return SampleWebCall.getData();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        });
        future.thenAccept(t -> {
            System.out.println(t + "\n World");
        }).thenRun(() -> {
            System.out.println("Then Run....");
        });
        future.get();
    }

    @Test
    public void UsingCompletableFuture() throws ExecutionException, InterruptedException {
        System.out.println(SampleWebCall.getDataFuture().get());
    }


    @Test
    public void UsingGet() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture
                .thenAccept(s -> System.out.println("Computation returned: " + s));

        // this block the thread until it completes.
        future.get();
    }
}
