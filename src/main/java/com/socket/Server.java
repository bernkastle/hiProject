package com.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 8888;
    private ExecutorService executor;

    public Server() {
        executor = Executors.newFixedThreadPool(10);
    }

    public static void main(String[] args) {
        System.out.println("服务器启动...\n");
        Server server = new Server();
        server.init();


    }

    public void init(){
        try {
            ServerSocket serverSocket = new ServerSocket(PORT, 100);
            for (;;){
                Socket client = serverSocket.accept();
                Runnable handler = new HandlerThread(client);
                executor.execute(handler);
//                new HandlerThread(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }
    }

    private class HandlerThread implements Runnable{
        private Socket socket;

        public HandlerThread(Socket socket) {
            this.socket = socket;
//            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                String clientInputStr = inputStream.readUTF();
                System.out.println("客户端发过来的内容:" + clientInputStr);
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                System.out.println("请输入:\t");
//                String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
                outputStream.writeUTF(LocalTime.now().toString());
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (socket != null){
                    try {
                        System.out.println("连接即将关闭");
                        Thread.sleep(1000);
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("服务端finally异常：" + e.getMessage());
                    }
                }
            }
        }
    }
}
