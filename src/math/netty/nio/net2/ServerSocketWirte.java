package org.example.netty.nio.net2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class ServerSocketWirte {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT,null);
        ssc.bind(new InetSocketAddress(8080));
        while(true){
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()){
                    SocketChannel channel = ssc.accept();
                    channel.configureBlocking(false);
                    SelectionKey selectionKey = channel.register(selector, 0, null);
                    selectionKey.interestOps(SelectionKey.OP_READ);

                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < 400000000; i++) {
                        sb.append("a");
                    }
                    ByteBuffer buffer = Charset.defaultCharset().encode(sb.toString());
                    int write = channel.write(buffer);
                    System.out.println(write);
                    if(buffer.hasRemaining()){
                        selectionKey.interestOps(selectionKey.interestOps()+SelectionKey.OP_WRITE);
                        selectionKey.attach(buffer);
                    }
                }else if(key.isWritable()){
                    ByteBuffer attachment = (ByteBuffer)key.attachment();
                    SocketChannel channel = (SocketChannel)key.channel();
                    int write = channel.write(attachment);
                    System.out.println(write);
                    if(!attachment.hasRemaining()){
                        key.attach(null);
                        key.interestOps(key.interestOps()-SelectionKey.OP_WRITE);
                    }
                }
            }
        }

    }
}
