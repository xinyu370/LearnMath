package org.example.netty2;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.*;

public class TestJdkFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testDefaultPromise();
    }
    public static void testJdkFuture() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> submit = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return 123;
            }
        });
        Integer integer = submit.get();
        System.out.println(integer);
        service.shutdown();
    }

    public static void testNettyFuture() throws ExecutionException, InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EventLoop next = eventLoopGroup.next();
        io.netty.util.concurrent.Future<Integer> submit = next.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return 123;
            }
        });
        submit.addListener(new GenericFutureListener<io.netty.util.concurrent.Future<? super Integer>>() {
            @Override
            public void operationComplete(io.netty.util.concurrent.Future<? super Integer> future) throws Exception {
                System.out.println(future.getNow());
                next.shutdown();
            }
        });
//        System.out.println(submit.get());
//        next.shutdown();
    }

    public static void testDefaultPromise() throws ExecutionException, InterruptedException {
        EventLoop eventLoop = new NioEventLoopGroup().next();
        DefaultPromise<Integer> defaultPromise = new DefaultPromise<>(eventLoop);

        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            defaultPromise.setSuccess(80);
        }).start();
        System.out.println("the res->"+defaultPromise.get());
    }


}
