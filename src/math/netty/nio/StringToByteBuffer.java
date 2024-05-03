package org.example.netty.nio;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class StringToByteBuffer {

    public static void main(String[] args) {
        //string to bytebuffer
        ByteBuffer buffer = stringToByteBuffer("hello");
        byteBufferToString(buffer);
    }

    public static ByteBuffer stringToByteBuffer(String s){
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put(s.getBytes());
        buffer.flip();
        TestByteBuffer.printByteBuffer(buffer);

        ByteBuffer hello = StandardCharsets.UTF_8.encode("hello");
        TestByteBuffer.printByteBuffer(hello);

        ByteBuffer wrap = ByteBuffer.wrap(s.getBytes());
        TestByteBuffer.printByteBuffer(wrap);
        return buffer;
    }

    public static String byteBufferToString(ByteBuffer buffer){
        buffer.flip();
        String s = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(s);
        return s;
    }
}
