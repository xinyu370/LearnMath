package org.example.netty.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatteringRead {
    public static void main(String[] args) {
        testGatheringWrite();
    }

    public static void testScatteringRead(){
        try (FileChannel channel = new RandomAccessFile("/home/xhl/桌面/learn_math/src/main/java/org/example/netty/data.txt","r").getChannel()) {
            ByteBuffer buffer1 = ByteBuffer.allocate(5);
            ByteBuffer buffer2 = ByteBuffer.allocate(5);
            ByteBuffer buffer3 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{buffer1,buffer2,buffer3});
            buffer1.flip();
            buffer2.flip();
            buffer3.flip();
            TestByteBuffer.printByteBuffer(buffer1);
            TestByteBuffer.printByteBuffer(buffer2);
            TestByteBuffer.printByteBuffer(buffer3);
        } catch (IOException e) {
        }
    }
    public static void testGatheringWrite(){
        try (FileChannel channel = new RandomAccessFile("/home/xhl/桌面/learn_math/src/main/java/org/example/netty/data.txt","r").getChannel()){
            ByteBuffer buffer1 = ByteBuffer.allocate(5);
            ByteBuffer buffer2 = ByteBuffer.allocate(5);
            ByteBuffer buffer3 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{buffer1,buffer2,buffer3});
            buffer1.flip();
            buffer2.flip();
            buffer3.flip();
            try(FileChannel writeChannel = new RandomAccessFile("/home/xhl/桌面/learn_math/src/main/java/org/example/netty/write.txt", "rw").getChannel()){
                writeChannel.write(new ByteBuffer[]{buffer3,buffer1,buffer2});
            }catch (Exception e){

            }


        }catch (Exception e){

        }

    }
}
