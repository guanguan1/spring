package com.guan.net.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * 零拷贝示例
 * @Author : guantenghua
 * @create 2020/5/31 17:50
 */
public class ZeroCopyTest {
    @Test
    public void wrapTest(){
        byte[] arr = {1, 2, 3, 4, 5};
        ByteBuf byteBuf = Unpooled.wrappedBuffer(arr);
        System.out.println(byteBuf.getByte(4));
        arr[4] = 6;
        System.out.println(byteBuf.getByte(4));
    }

    @Test
    public void sliceTest(){
        ByteBuf byteBuf = Unpooled.wrappedBuffer("hello".getBytes());
        ByteBuf newBuffer = byteBuf.slice(1, 2);
        newBuffer.unwrap();
        System.out.println(newBuffer.toString());
    }

    @Test
    public void compositeTest(){
        ByteBuf byteBuf = Unpooled.buffer(3);
        byteBuf.writeByte(1);
        ByteBuf byteBuf1 = Unpooled.buffer(3);
        byteBuf1.writeByte(4);
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        CompositeByteBuf newBuffer = compositeByteBuf.addComponents(true, byteBuf, byteBuf1);
        System.out.println(newBuffer);
    }
}
