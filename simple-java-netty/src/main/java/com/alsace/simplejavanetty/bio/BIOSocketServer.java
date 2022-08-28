package com.alsace.simplejavanetty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOSocketServer {

    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            final Socket socket = server.accept();
            threadPool.submit(() -> handle(socket));
        }
    }

    private static void handle(Socket socket) {
        byte[] buffer = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                int read = inputStream.read(buffer);
                if (-1 != read) {
                    System.out.println(new String(buffer, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
