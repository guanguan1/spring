package com.guan.net.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟服务端-多线程
 * @Author : guantenghua
 * @create 2020/5/29 14:36
 */
public class BIOServerByThreadPool {

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动成功");
        while(!serverSocket.isClosed()){
            Socket request = serverSocket.accept();
            System.out.println("收到新连接：" + request.toString());
            threadPool.execute(() ->{
                // 接收数据，打印   net + io
                InputStream inputStream = null;
                try {
                    inputStream = request.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    String msg;
                    // 没有数据，阻塞
                    while ((msg = reader.readLine()) != null){
                        if(msg.length() == 0){
                            break;
                        }
                        System.out.println(msg);
                    }
                    System.out.println("收到数据，来自：" + request.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        request.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        serverSocket.close();
    }
}
