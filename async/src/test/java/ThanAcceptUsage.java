import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class ThanAcceptUsage {

    @Test
    public void BasicUsage() {
        // Creating the Completable future with input 2
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 2);

        // thenAccept is executed once the code block from supplyAsync is executed.
        future.thenAccept(t -> {
            System.out.println(String.format("Received the input: %d", 2));
        });
    }

    @Test
    public void ExceptionUseCase() throws RuntimeException {
        // Creating the Completable future with exception in supply sync.
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Exception in Async");
        });

        // here thenAccept won't be executed because of exception in supplyAsync.
        // To handle exception we need to add exceptionally block
        future.thenAccept(t -> {
            System.out.println(String.format("Received the input: %d", 2));
        }).exceptionally(t -> {
            System.out.println(String.format("Exception received: " + t.getMessage()));
            return null;
        });
    }

    @Test
    public void ContinueExecutionChainInCaseOfException() throws RuntimeException {

        // Creating the Completable future with exception in supply sync.
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Exception in Async");
        });

        // Here we handling the exception and returning the value to continue.
        future.exceptionally(t -> {
            System.out.println("Exception received");
            return 10;
        }).thenAccept(n -> {
            System.out.println(String.format("Received the input : %d", n));
        });
    }


    @Test
    public void StopNextStagesInCaseOfException() throws RuntimeException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 2);
        future.thenApply(n -> {
            System.out.println(String.format("Received the input : %d", n));
            return n*n;
        }).thenAccept(n -> {
            throw new RuntimeException("Exception");
        }).thenAccept(n ->{ /*This method won't be executed because of the exception in previous stage.*/
            System.out.println("Received:"+ n);
        }).exceptionally(t -> { // This will be executed because of the unhandled exception in previous stage.
            System.out.println("Exception Occurred");
            return null;
        });
    }
}
