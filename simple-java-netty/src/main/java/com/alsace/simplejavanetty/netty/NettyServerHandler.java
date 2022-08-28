package com.alsace.simplejavanetty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * 自定义handle，需要继承父类 ChannelHandlerAdapter
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据
     * @param ctx 上下文，包含 channel pipeline 等对象
     * @param msg  客户端发过来的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("msg from = 【" + ctx.channel().remoteAddress() + "】,msg = 【" + byteBuf.toString(StandardCharsets.UTF_8) + "】");
    }

    /**
     * 消息处理完成之后的操作
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("write over!");
    }

    /**
     * 异常处理，关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


    public static void main(String[] args) {
        String str = "contract_ss222：contract_1111";
        System.out.println(str.replace("contract_", ""));
    }
}
