import org.junit.Test;

public class RunnableExamples {

    @Test
    public void BasicExample() throws InterruptedException {
        Runnable task = () ->{
            System.out.println("Example");
        };

        Thread t = new Thread(task);
        t.start();
    }

    /// This example just prints text "Starting..." only. this is because we are not waiting for task for completion
    @Test
    public void WaitForCompletion() throws InterruptedException {
        Runnable task = () ->{
            System.out.println("Starting...");
            Sleep(5000);
            System.out.println("Ending....");
        };

        Thread t= new Thread(task);
        t.start();
        // this wait for the thread to complete.
        t.join();
    }

    @Test
    public void UsingRunnableTask() throws InterruptedException {
        RunnableExampleTask task = new RunnableExampleTask();
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
    }

    private void Sleep(long milli){
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }


}
