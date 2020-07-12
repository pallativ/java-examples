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


    // As the name suggests, whenComplete is a kind of finally. both in case of exception and also in exception.
    // It takes two parameters 1. Previous stage return value and exception if any if previous stages.
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
