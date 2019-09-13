package async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JavaFuture {

    public static void main(String[] args) throws Throwable, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        // Future代表了线程执行完以后的结果，可以通过future获得执行的结果
        // 但是jdk1.8之前的Future有点鸡肋，并不能实现真正的异步，需要阻塞的获取结果，或者不断的轮询
        // 通常我们希望当线程执行完一些耗时的任务后，能够自动的通知我们结果，很遗憾这在原生jdk1.8之前
        // 是不支持的
        Future<String> f = executor.submit(() -> {
            System.out.println("task started!");
            Thread.sleep(3000);
            System.out.println("task finished!");
            return "hello";
        });

        //此处阻塞main线程
        System.out.println(f.get());
        System.out.println("main thread is blocked");
    }

}
