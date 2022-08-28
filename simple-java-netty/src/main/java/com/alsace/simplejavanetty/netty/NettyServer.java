package com.alsace.simplejavanetty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;

public class NettyServer {

    public static void main(String[] args) throws Exception {
        // 创建线程组bossGroup 和 workGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        // 创建服务器的启动对象，设置参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workGroup) // 设置2个线程组
                .channel(ServerSocketChannel.class)  // 使用 ServerSocketChannel 类型作为服务器的通道实现
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() {  // 创建通道对象
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyServerHandler());  // 给pipeline设置处理器
                    }
                });

        System.out.println("server is ready...");

        ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();

        channelFuture.channel().closeFuture().sync();
    }
}
