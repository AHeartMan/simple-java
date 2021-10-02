package com.alsace.simplejavanio.wechat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 多人聊天室服务端
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/26 0026
 */
public class Server {

    private final int port = 8899;

    private Charset charset = Charset.forName("utf-8");

    private ByteBuffer inBuffer = ByteBuffer.allocate(1024);

    private ByteBuffer outBuffer = ByteBuffer.allocate(1024);

    private Map<String, SocketChannel> clientMap = new HashMap<>();

    private Selector selector;

    public Server() throws IOException {
        init();
    }

    private void init() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务端启动成功，端口为：" + port);
    }

    public void listen() throws IOException {
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                handle(selectionKey);
                selectionKeys.clear();
            }
        }
    }

    private void handle(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel client = serverSocketChannel.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            clientMap.put(getSocketName(client), client);
        } else if (selectionKey.isReadable()) {
            SocketChannel client = (SocketChannel) selectionKey.channel();
            inBuffer.clear();
            int len = client.read(inBuffer);
            if (len > 0) {
                inBuffer.flip();
                String msg = String.valueOf(charset.decode(inBuffer));
                System.out.println("receive msg : " + msg);
                dispatch(client, msg);
            }
        }
    }

    private void dispatch(SocketChannel client, String message) throws IOException {
        if (!clientMap.isEmpty()) {
            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                SocketChannel channel = entry.getValue();
                outBuffer.clear();
                outBuffer.put(charset.encode(getSocketName(client) + ": " + message));
                outBuffer.flip();
                channel.write(outBuffer);
            }
        }
    }

    private String getSocketName(SocketChannel client) {
        Socket socket = client.socket();
        System.out.println(socket.getInetAddress());
        return "[" + socket.getInetAddress().toString().substring(1) + ":" + Integer.toHexString(client.hashCode()) + "]";
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.listen();
    }
}
