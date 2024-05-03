package org.example.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class SelectorServer {
    public static void main(String[] args) throws IOException {

        //manangement channels
        Selector selector = Selector.open();

        ServerSocketChannel sc = ServerSocketChannel.open();
        sc.configureBlocking(false);

        //open transcate, by this get channel transtion:accept,connect,read,write
        SelectionKey selectionKey = sc.register(selector, 0, null);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
        System.out.println("register key:"+selectionKey);

        sc.bind(new InetSocketAddress(8080));

        while(true){
            selector.select();//blocked
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();  //get selection key
                System.out.println("register key:"+key);
                if(key.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel)key.channel(); //by key get channel
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    //add readable transcation to the selector
                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    SelectionKey acceptKey = accept.register(selector, 0, buffer);
                    acceptKey.interestOps(SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer)key.attachment();
//                    channel.configureBlocking(false);
                        int n = channel.read(buffer); //blocked
                        if (n > 0) {
                            System.out.println("read data from channel! " + channel);
                            buffer.flip();
                            //TestByteBuffer.printByteBuffer(buffer);
                            split(buffer);
                            TestByteBuffer.printByteBuffer(buffer);
                            if(buffer.position() == buffer.limit()){
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity()<<2);
                                buffer.flip();
                                newBuffer.put(buffer);
                                key.attach(newBuffer);
                            }
                        }else if(n == -1){
                            key.cancel();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        key.cancel();
                    }
                }
                iterator.remove();//remove this key. in key collection not remove key
            }
        }
    }

    public static void split(ByteBuffer source){
        for (int i = 0; i < source.limit(); i++) {
            if(source.get(i) == '\n'){
                int length = i+1-source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j= 0; j<length;j++){
                    target.put(source.get());
                }
                TestByteBuffer.printByteBuffer(target);
            }
        }
        source.compact();
    }
}
