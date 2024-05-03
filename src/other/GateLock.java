package other;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class GateLock {
    /**
     * 闭锁：
     * 一种同步工具类，可以延迟线程的进度直到去到达终止状态
     */
    public static long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1); //开门
        final CountDownLatch endGate = new CountDownLatch(nThreads); //闭门
        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run(){
                    try {
                        startGate.await(); //等待开门开启
                        try {
                            task.run(); //运行任务
                        }finally {
                            endGate.countDown(); //运行完成
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown(); //开启开门
        endGate.await();      //等待闭门打开
        long end = System.nanoTime();
        return end-start;
    }

    /**
     * 通过future task 来提前加载任务，并在需要的时候获取结果
     * 提前计算1 到 100的值
     */
    static class PreLoader {
        private final FutureTask<Integer> futureTask =
                new FutureTask<>(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        int ans = 0;
                        for (int j = 0; j < 101; j++) {
                            ans += j;
                        }
                        return ans;
                    }
                });
        private final Thread thread = new Thread(futureTask);

        public void start() {
            thread.start();
        }
        public Integer get () {
            try {
                return futureTask.get();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                throw launderThrowable(cause);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static RuntimeException launderThrowable(Throwable t){
        if(t instanceof  RuntimeException){
            return (RuntimeException) t;
        }else if(t instanceof Error){
            throw (Error)t;
        }else{
            throw  new IllegalStateException("not checked ",t);
        }
    }


    //三个线程打印分别去打印0到100
    public static void main(String[] args) throws InterruptedException {
        PreLoader preLoader = new PreLoader();
        preLoader.start();
        System.out.println(preLoader.get()); //get方法会阻塞
        System.out.println(timeTasks(3,new printTask()));
    }
    private static AtomicInteger  i = new AtomicInteger(0);
    static class printTask implements Runnable{
        @Override
        public void run() {
            while(i.get()<100){
                int i1 = i.incrementAndGet();
                System.out.println("curren value:"+i1);
            }
        }
    }

}
