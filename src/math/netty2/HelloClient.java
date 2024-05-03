package org.example.netty2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


@Slf4j
public class HelloClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                                        .group(nioEventLoopGroup)
                                        .channel(NioSocketChannel.class)
                                        .handler(new ChannelInitializer<NioSocketChannel>() {
                                            @Override
                                            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                                                nioSocketChannel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG)).addLast(new StringEncoder());
                                            }
                                        })
                                        //this method was sync
                                        .connect(new InetSocketAddress("localhost", 8080));
        channelFuture.sync();
        Channel channel = channelFuture.channel();
        channel.writeAndFlush("hello");
        ChannelFuture closeFuture = channel.closeFuture();
        new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            while(true){
                String s = scanner.nextLine();
                if("q".equals(s)){
                    channel.close();
                    break;
                }
                channel.writeAndFlush(s);
            }

        }).start();
        System.out.println("waiting close");
        closeFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                System.out.println("waiting quite");
                nioEventLoopGroup.shutdownGracefully();
            }
        });

//        channelFuture.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                Channel channel = channelFuture.channel();
//                System.out.println(channel+""+Thread.currentThread().getName());
//                channel.writeAndFlush("hello hetty!");
//            }
//        });
    }
    public static void test(){
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(2);
        EventLoop next = nioEventLoopGroup.next();
        next.scheduleAtFixedRate(()->{
            System.out.println(":he");
        },2,1, TimeUnit.SECONDS);
//        nioEventLoopGroup.execute(()->{
//            try {
//                Thread.sleep(2000);
//                System.out.println("ok->"+Thread.currentThread().getName());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
        System.out.println("main->"+Thread.currentThread().getName());
    }
}
