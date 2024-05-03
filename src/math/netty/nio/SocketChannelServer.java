package org.example.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class SocketChannelServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocate(16);

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);//default is true ,true:block, false:unblock
        ssc.bind(new InetSocketAddress(8080));

        List<SocketChannel> channels = new ArrayList<>();
        while(true){
            SocketChannel accept = ssc.accept(); //blocked
            if(accept != null){
                System.out.println("connected!");
                accept.configureBlocking(false);
                channels.add(accept);
            }

            for(SocketChannel channel : channels){
                int n = channel.read(buffer); //blocked
                if(n > 0) {
                    System.out.println("read data from channel! "+channel);
                    buffer.flip();
                    TestByteBuffer.printByteBuffer(buffer);
                    buffer.clear();
                }
            }
            Thread.sleep(2000);
        }
    }
}
