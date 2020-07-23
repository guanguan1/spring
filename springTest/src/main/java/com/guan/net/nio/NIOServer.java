package com.guan.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 直接基于非阻塞的写法服务端
 * @Author : guantenghua
 * @create 2020/5/29 17:01
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建网络服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("启动成功");
        while (true){
            // 获取新的tcp连接通道
            SocketChannel socketChannel = serverSocketChannel.accept();
            // tcp请求
            if(socketChannel != null){
                System.out.println("收到新的连接：" + socketChannel.getRemoteAddress());
                // 默认是阻塞的，一定要设置为非阻塞
                socketChannel.configureBlocking(false);
                ByteBuffer requsetBuffer = ByteBuffer.allocate(1024);
                while(socketChannel.isOpen() && socketChannel.read(requsetBuffer) != -1){
                    // 长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
                    if(requsetBuffer.position() > 0){
                        break;
                    }
                }
                // 如果没有数据了，则不继续后面的处理
                if(requsetBuffer.position() == 0){
                    continue;
                }
                requsetBuffer.flip();
                byte[] content = new byte[requsetBuffer.limit()];
                requsetBuffer.get(content);
                System.out.println(new String(content));
                System.out.println("收到数据，来自：" + socketChannel.getRemoteAddress());

                // 响应结果 200
                String response = "HTTP/1.1 200 OK\r\n" +
                        "Content-Length: 11\r\n\r\n" +
                        "Hello World";
                ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                while (buffer.hasRemaining()){
                    // 非阻塞
                    socketChannel.write(buffer);
                }
            }
        }
    }
}
