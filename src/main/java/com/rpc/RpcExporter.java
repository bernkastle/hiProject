package com.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RpcExporter {
    static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void exporter(String hostName, int port) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(hostName, port));
        try {
            for (;;){
                executor.execute(new ExporterTask(server.accept()));
            }
        }finally {
            server.close();
        }

    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }


    private static class ExporterTask implements Runnable{
        Socket client = null;

        public ExporterTask(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {

        }
    }
}


