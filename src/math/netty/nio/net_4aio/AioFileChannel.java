package org.example.netty.nio.net_4aio;

import org.example.netty.nio.TestByteBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AioFileChannel {
    public static void main(String[] args) throws InterruptedException, IOException {
        AsynchronousFileChannel channel = null;
        try{
             channel = AsynchronousFileChannel.open(
                    Paths.get("/home/xhl/桌面/learn_math/src/main/java/org/example/netty/data.txt"),
                    StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(16);
            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("read bytes:"+result);
                    TestByteBuffer.printByteBuffer(attachment);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                        exc.printStackTrace();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        Thread.sleep(3000);
    }
}
