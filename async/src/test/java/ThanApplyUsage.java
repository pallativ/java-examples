import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class ThanApplyUsage {

    @Test
    public void BasicUsage(){
        // Creating the Completable future with input 2
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 2);

        // thenAccept is executed once the code block from supplyAync is executed.
        future.thenAccept(t -> {
            System.out.println(String.format("Received the input: %d", 2));
        });
    }

}
