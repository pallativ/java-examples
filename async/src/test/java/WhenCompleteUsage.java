import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class WhenCompleteUsage {

    // this example shows usage of whenComplete.
    @Test
    public void BasicUsage() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 2);
        future.thenAccept(n -> {
            System.out.println("Received :" + n);
        }).whenComplete((aVoid, throwable) -> {
            System.out.println("When Complete Method");
        });
    }

    @Test
    public void ReturnFromPreviousStage() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 2);
        future.thenApply(n -> {
            System.out.println("Received :" + n);
            return n * n;
        }).whenComplete((r, throwable) -> {
            System.out.println("When Complete Method with received input from previous stage: "+ r);
        });
    }
}
