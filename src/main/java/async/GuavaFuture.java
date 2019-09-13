package async;

import com.google.common.util.concurrent.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaFuture {

    static ExecutorService executor = Executors.newFixedThreadPool(1);
    //     使用guava提供的MoreExecutors工具类包装原始的线程池
    static ListeningExecutorService listeningExecutor = MoreExecutors.listeningDecorator(executor);

    public static void main(String[] args) {

        System.out.println("main thread starts");


//        ListenableFuture<String> lf = submitCallable();
//        System.out.println("main thread is running");
//
//        try {
//            String result = lf.get();
//            System.out.println("main thread gets result: "+result);
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        ListenableFuture lf = submitRunnable();
        System.out.println("main thread is running");

        try {
            Object object = lf.get();
            System.out.println("main thread gets result: "+ object);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("System is shutting down ...");
        listeningExecutor.shutdown();
        executor.shutdown();
    }


    public static ListenableFuture submitRunnable() {

        ListenableFuture future = listeningExecutor.submit(() -> {
        //或者加上Object type
//      ListenableFuture<Object> future = listeningExecutor.submit(() -> {
            System.out.println("task1 starts");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Futures.addCallback(future,
                new FutureCallback<Object>() {

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println("failure");
                    }

                    @Override
                    public void onSuccess(Object result) {

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("I am informed");
                    }
                },
                executor);


        return future;

    }

    public static ListenableFuture<String> submitCallable() {
        //向线程池中提交一个任务后，将会返回一个可监听的Future，该Future由Guava框架提供
        ListenableFuture<String> lf = listeningExecutor.submit(() -> {
            System.out.println("task1 starts");
            //模拟耗时操作
            Thread.sleep(3000);
            return "task1 finished!";
        });

        //添加回调，回调由executor中的线程触发，但也可以指定一个新的线程
        Futures.addCallback(lf,
                new FutureCallback<String>() {

                    //耗时任务执行失败后回调该方法
                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println("failure");
                    }

                    //耗时任务执行成功后回调该方法
                    @Override
                    public void onSuccess(String s) {

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("I am informed: " + s);
                    }
                },
                executor);


        return lf;
    }

}
