package org.example.netty2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

public class TestSlice {

    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(5);
        buf.writeBytes(new byte[]{'a','b','c','d','e'});
        ByteBuf slice1 = buf.slice(0, 2);
        slice1.retain();
        ByteBuf slice2 = buf.slice(2,3);
        slice2.retain();

        TestByteBuf.log(slice1);
        TestByteBuf.log(slice2);
        System.out.println("------------------------------");
        buf.release();
        buf.setByte(2,'f');
        TestByteBuf.log(slice1);
        TestByteBuf.log(slice2);

        CompositeByteBuf byteBufs = ByteBufAllocator.DEFAULT.compositeBuffer();
        byteBufs.addComponents(true,slice1,slice2);
        TestByteBuf.log(byteBufs);
    }
}
