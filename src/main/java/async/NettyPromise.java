package async;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;


public class NettyPromise {
    static EventExecutorGroup group = new DefaultEventExecutorGroup(1);

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void main(String[] args) throws Throwable {

//        System.out.println("main thread starts");
//        Future<?> runTask = submitRunnable();
//        System.out.println("main thread is running");
//
//        runTask.await();

        System.out.println("main thread starts");
        Future<String> runTask = submitCallable();
        System.out.println("main thread is running");

        String res = runTask.awaitUninterruptibly().getNow();
        System.out.println("res: " + res);

        group.shutdownGracefully();
    }

    public static Future<?> submitRunnable() {

        //向线程池中提交任务，并返回Future，该Future是netty自己实现的future
        //位于io.netty.util.concurrent包下，此处运行时的类型为PromiseTask
        Future<?> f = group.submit(() -> {
            System.out.println("task1 starts");
            //模拟耗时操作，比如IO操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task1 finished!");
        });
        //增加监听
        return f.addListener((FutureListener) t -> {
            System.out.println("task2 starts");
            System.out.println("task2 finished!");
        });
    }

    public static Future<String> submitCallable() {
        Future<String> f2 = group.submit(() -> {
            System.out.println("task1 starts");
            //模拟耗时操作，比如IO操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "task1 finished!";
        });
        //增加监听
        return f2.addListener((FutureListener) t -> {
            System.out.println("t: " + t);
            System.out.println("task2 starts");
            System.out.println("task2 finished");
        });


    }

}
