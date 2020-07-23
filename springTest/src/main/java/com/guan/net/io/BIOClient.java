package com.guan.net.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * 模拟客户端
 * @Author : guantenghua
 * @create 2020/5/29 14:32
 */
public class BIOClient {
    private static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 8080);
        OutputStream out = s.getOutputStream();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String msg = scanner.nextLine();
        // 阻塞，写完成
        out.write(msg.getBytes(charset));
        scanner.close();
        s.close();
    }
}
