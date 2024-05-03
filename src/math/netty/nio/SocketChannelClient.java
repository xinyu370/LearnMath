package org.example.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class SocketChannelClient {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost",8080));
        System.out.println("waiting...");
        sc.write(Charset.defaultCharset().encode("hello\nworld"));
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("please input string:");
            String s = scanner.nextLine();
            sc.write(Charset.defaultCharset().encode(s));
        }

    }
}
