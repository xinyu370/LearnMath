package org.example.netty.nio.net3_mutiThread;

import org.example.netty.nio.TestByteBuffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BossServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
         ssc.register(selector, SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(8080));
        Worker worker = new Worker("worker-0");
        while(true){
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    System.out.println("connected"+accept.getRemoteAddress());
                    worker.register(accept);
                }else if(selectionKey.isReadable()){

                }

            }
        }
    }

    static class Worker implements  Runnable{
        private Thread thread;
        private Selector selector;
        private String name;
        private boolean isInit = false;
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

        public Worker(String name){
            this.name = name;
        }


        public void register(SocketChannel accept) throws IOException {
            if(!isInit) {
                thread = new Thread(this, name);
                thread.start();
                selector = Selector.open();
                isInit = true;
            }
            queue.add(()->{
                try {
                    accept.register(selector, SelectionKey.OP_READ,null);
                } catch (ClosedChannelException e) {
                    throw new RuntimeException(e);
                }
            });
            selector.wakeup();

        }

        @Override
        public void run() {
            while(true){
                try {
                    selector.select();
                    Runnable runnable = queue.poll();
                    if(runnable!=null){
                        runnable.run();
                    }
                    Iterator<SelectionKey> iterator = selector.keys().iterator();
                    while(iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if(key.isReadable()){
                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel)key.channel();
                            channel.read(buffer);
                            buffer.flip();
                            TestByteBuffer.printByteBuffer(buffer);
                        }else if(key.isWritable()){

                        }

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
