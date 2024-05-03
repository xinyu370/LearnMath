package org.example.netty2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.Charset;

public class TestHandler {
    public static void main(String[] args) {
        new ServerBootstrap().group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast("h1", new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("1 the thread->"+Thread.currentThread().getName());
                                ByteBuf buf = (ByteBuf)msg;
                                Student student = new Student(buf.toString(Charset.defaultCharset()));
                                super.channelRead(ctx, student);
                                //nioSocketChannel.pipeline().write(ctx.alloc().buffer().writeBytes("hello".getBytes()));
                            }
                        }).addLast("h2",new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                Student student = (Student)msg;
                                System.out.println(student.getName()+","+((Student) msg).getName());
                                System.out.println(msg.getClass());
                                //super.channelRead(ctx, msg);
                            }
                        }).addLast("last", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                System.out.println("out oooo! the thread->"+Thread.currentThread().getName());
                                super.write(ctx, msg, promise);
                            }
                        });
                    }
                }).bind(8080);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Student{
        private String name;
    }
}
