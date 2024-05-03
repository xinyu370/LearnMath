package org.example.netty2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;


public class HelloServer {
    public static void main(String[] args) {
        //1.startrt make netty component
        new ServerBootstrap()
                //event group (thought a selector)
                .group(new NioEventLoopGroup())
                //implement severSocketChannel
                .channel(NioServerSocketChannel.class)
                //add worker handler event
                .childHandler(
                        //channel  client and server read/write initializer
                        new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                System.out.println(buf.toString(Charset.defaultCharset()));
                            }
                        }).addLast();
//                        nioSocketChannel.pipeline().addLast(new StringDecoder());//bytebuf -> string
//                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
//                            @Override
//                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                System.out.println(msg);
//                                super.channelRead(ctx, msg);
//                            }
//                        });
                    }

//                    @Override
//                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                        System.out.println(msg);
//                        super.channelRead(ctx, msg);
//                    }
                }).bind(8080);
    }
}
