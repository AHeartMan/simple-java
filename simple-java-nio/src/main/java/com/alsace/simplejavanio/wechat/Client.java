package com.alsace.simplejavanio.wechat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.Set;

/**
 * <p>
 * 多人聊天室客户端
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/26 0026
 */
public class Client {

    private InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8899);

    private Charset charset = Charset.forName("utf-8");

    private ByteBuffer inBuffer = ByteBuffer.allocate(1024);

    private ByteBuffer outBuffer = ByteBuffer.allocate(1024);

    private static Selector selector;

    public void client() throws IOException {
        init();
    }

    private void init() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(address);

        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(selectionKey -> {
                try {
                    handle(selectionKey);
                    selectionKeys.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void handle(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isConnectable()){
            SocketChannel client = (SocketChannel) selectionKey.channel();
            if (client.isConnectionPending()){
                client.finishConnect();
                System.out.println("连接成功");
                new Thread(() -> {
                    while (true){
                        inBuffer.clear();
                        Scanner scanner = new Scanner(System.in);
                        String msg = scanner.nextLine();
                        inBuffer.put(charset.encode(msg));
                        inBuffer.flip();
                        try {
                            client.write(inBuffer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
            client.register(selector, SelectionKey.OP_READ);
        }else if (selectionKey.isReadable()){
            SocketChannel client = (SocketChannel) selectionKey.channel();
            outBuffer.clear();
            int len = client.read(outBuffer);
            if (len > 0){
                System.out.println(new String(outBuffer.array(), 0, len));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.client();
    }
}
