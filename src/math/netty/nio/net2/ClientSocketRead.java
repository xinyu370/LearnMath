package org.example.netty.nio.net2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientSocketRead {

    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("localhost",8080));
        int count = 0;
        while(true){
            ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
            int read = channel.read(buffer);
            count += read;
            System.out.println("read:"+count);
            buffer.clear();
        }
    }
}
