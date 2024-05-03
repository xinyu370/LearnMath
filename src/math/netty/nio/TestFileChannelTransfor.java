package org.example.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestFileChannelTransfor {
    public static void main(String[] args) {
        try(FileChannel from = new FileInputStream("/home/xhl/桌面/learn_math/src/main/java/org/example/netty/data.txt").getChannel();
            FileChannel to = new FileOutputStream("/home/xhl/桌面/learn_math/src/main/java/org/example/netty/to.txt").getChannel();)
        {
            from.transferTo(0,from.size(),to);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
