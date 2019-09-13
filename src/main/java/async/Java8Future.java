package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Java8Future {

    static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Throwable, ExecutionException {


//        System.out.println("main thread starts");
//        CompletableFuture<String> supplyTask = supply();
//        System.out.println("main thread is running");
//
//        String res = supplyTask.join();
//        System.out.println(res);

        System.out.println("main thread starts");
        CompletableFuture<Void> runTask = run();
        System.out.println("main thread is running");

        runTask.join();

        executor.shutdown();
    }

    public static CompletableFuture<String> supply() {

        // Supplier 有返回值
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 starts");
            try {
                //模拟耗时操作
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1 finished!";
        }, executor);

        // Apply 有返回值
        return future.thenApply(t -> {
            System.out.println("task2 starts");
            return t + " task2 finished!";
        });
    }

    public static CompletableFuture<Void> run() {
        // Runnable 无返回值
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("task1 starts!");
            try {
                //模拟耗时操作
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task1 finished!");
        }, executor);

        // Accept 无返回值
        return future.thenAccept(e -> {
            System.out.println("task2 finished!");
        });
    }
}
