package com.guan.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author : guantenghua
 * @create 2020/5/31 10:18
 */
public class NIOServerHttp {
    public static Selector selector;

    // 定义线程池
    public static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(25, 25, 25, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    private static ServerSocketChannel socketChannel;

    private static final int port = 8080;

    public static void main(String[] args) throws IOException {
        socketChannel = ServerSocketChannel.open(); // 开启通道
        socketChannel.configureBlocking(false);     // 非阻塞状态
        socketChannel.bind(new InetSocketAddress(port)); // 绑定端口
        System.out.println("NIO启动：" + port);
        // 获取一个选择器，底层的事件通知机制
        NIOServerHttp.selector = Selector.open();
        // 对OP_ACCEPT事件感兴趣，并返回一个标记
        SelectionKey selectionKey = socketChannel.register(NIOServerHttp.selector, 0);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);

        while (true) {
            // 如果没有新的socket与服务器有连接或者数据交互，这里就会等待1s
            NIOServerHttp.selector.select(1000L);
            // 开始处理
            Set<SelectionKey> selected = NIOServerHttp.selector.selectedKeys();
            Iterator<SelectionKey> iter = selected.iterator();
            while (iter.hasNext()) {
                // 获取注册上面标记
                SelectionKey key = iter.next();
                // 判断是否OP_ACCEPT通知
                if (key.isAcceptable()) {
                    // 处理连接
                    System.out.println("有新的连接啦，当前线程数量:" + NIOServerHttp.threadPoolExecutor.getActiveCount());
                    // 有新的连接
                    SocketChannel channel = socketChannel.accept();
                    channel.configureBlocking(false);
                    // 注册一个新监听。
                    // 表示希望收到该连接上OP_READ数据传输事件的通知
                    channel.register(NIOServerHttp.selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 取出附着在上面的信息，也就是上面代码中附着的连接信息
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // 处理中，不需要收到任何通知
                    key.cancel();
                    socketChannel.configureBlocking(false);
                    NIOServerHttp.threadPoolExecutor.execute(() -> {
                        try {
                            // 读取里面的内容，请注意，此处大小随意写的。
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            socketChannel.read(byteBuffer);
                            byteBuffer.flip(); // 转为读模式
                            String request = new String(byteBuffer.array());
                            System.out.println("收到新数据，当前线程数："
                                    + NIOServerHttp.threadPoolExecutor.getActiveCount()
                                    + "，请求内容：" + request);
                            // 给一个当前时间作为返回值
                            // 随意返回，不是Http的协议
                            byteBuffer.clear();
                            ByteBuffer wrap = ByteBuffer.wrap(("tom" + System.currentTimeMillis()).getBytes());
                            socketChannel.write(wrap);
                            wrap.clear();
                            socketChannel.configureBlocking(false);
                            // 注册一个新监听。 表示希望收到该连接上OP_READ事件的通知
                            socketChannel.register(NIOServerHttp.selector, SelectionKey.OP_READ);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " 服务器线程处理完毕，当前线程数："
                                + threadPoolExecutor.getActiveCount());
                    });
                }
                iter.remove();
            }
            selected.clear();
            //过滤掉cancelled keys
            NIOServerHttp.selector.selectNow();
        }
    }
}
