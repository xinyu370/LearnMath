package org.example.netty.nio;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class TestByteBuffer {
    public static void main1(String[] args) {
        try(FileChannel channel =new FileInputStream("/home/xhl/桌面/learn_math/src/main/java/org/example/netty/data.txt").getChannel();){
            ByteBuffer byteBuffer = ByteBuffer.allocate(5);
            //wirte data to buffer
            int read = channel.read(byteBuffer);
            for(;read!=-1;read = channel.read(byteBuffer)){
                byteBuffer.flip();//read mode
                while(byteBuffer.hasRemaining()){
                    byte b = byteBuffer.get();
                    System.out.println((char)b);
                }
                byteBuffer.clear();
            }

        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        testRewind();
    }

    public static void testRewind(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a','b','c','d'});
        buffer.flip();
        printByteBuffer(buffer);
        buffer.rewind();
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        //mark and reset
        buffer.mark(); //position is 1
        System.out.println((char)buffer.get()); //position is 3
        buffer.reset(); //position is 1
        printByteBuffer(buffer);//should  bcd
    }

    public static void printByteBuffer(ByteBuffer buffer){
        buffer.flip();
        System.out.println("----------current position:"+buffer.position()+",limit:"+buffer.limit()+"----------");
        byte[] b = new byte[10];
        while(buffer.hasRemaining()){
            byte b1 = buffer.get();
            System.out.print((char) b1+",");
        }
        System.out.println();
        System.out.println("----------print end position:"+buffer.position()+"---------------");
    }

    public static List<ByteBuffer> splite(ByteBuffer[] buffers){
        List<ByteBuffer> list = new ArrayList<>();
        for(ByteBuffer buffer:buffers){
            int index = 0;
            buffer.flip();
            ByteBuffer buffer1 = ByteBuffer.allocate(buffer.capacity());
            while(index < buffer.capacity()){
                byte b = buffer.get();
                buffer1.put(b);
                if('\n' == b){
                    list.add(buffer1);
                    buffer1 = ByteBuffer.allocate(buffer.capacity());
                }
                index++;
            }
        }
        return list;
    }
}
