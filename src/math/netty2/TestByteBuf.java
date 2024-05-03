package org.example.netty2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.internal.StringUtil;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;


public class TestByteBuf {

    public static void log(ByteBuf buf){
        int length = buf.readableBytes();
        int rows = length/16 + (length%15==0?0:1)+4;
        StringBuilder builder = new StringBuilder(rows*80*2)
                .append("read index:").append(buf.readerIndex())
                .append("write index:").append(buf.writerIndex())
                .append("capacity:").append(buf.capacity())
                .append(StringUtil.NEWLINE);
        appendPrettyHexDump(builder,buf);
        System.out.println(builder.toString());
    }
    public static void createByteBuf(){
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        System.out.println(buffer.getClass());
        log(buffer);
        buffer.writeBytes("hello".getBytes());
        log(buffer);
    }
    public static void main(String[] args) {
        createByteBuf();
    }
}
