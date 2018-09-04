package com.socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;

public class Client {
    private static final String IP_ADDR = "localhost";
    private static final int PORT = 8888;

    public static void main(String[] args) {
        System.out.println("客户端启动...");
        System.out.println("当接收到服务器端字符为 \"OK\"的时候，客户端将终止\n");
        for (;;){
            Socket socket = null;
            try {
                socket = new Socket(IP_ADDR, PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("请输入: \t");
//                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
                out.writeUTF(LocalTime.now().toString());

                String ret = input.readUTF();
                System.out.println("服务器返回值为：" + ret);
                if ("OK".equals(ret)){
                    System.out.println("客户端即将关闭");
                    Thread.sleep(500);
                    break;
                }
                Thread.sleep((int) (Math.random() * 1000));
            }catch (Exception e){
                System.out.println("客户端异常：" + e.getMessage());
            }finally {
                if (socket != null){
                    try {

                        socket.close();
                    } catch (IOException e) {
                        socket = null;
                        System.out.println("客户端finally异常：" + e.getMessage());
                    }
                }
            }
        }
    }
}
