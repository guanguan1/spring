package com.guan.net.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟服务端
 * @Author : guantenghua
 * @create 2020/5/29 14:36
 */
public class BIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动成功");
        while(!serverSocket.isClosed()){
            // 阻塞
            Socket request = serverSocket.accept();
            System.out.println("收到新连接：" + request.toString());
            // 接收数据，打印   net + io
            InputStream inputStream = request.getInputStream();
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
            request.close();
        }
        serverSocket.close();
    }
}
