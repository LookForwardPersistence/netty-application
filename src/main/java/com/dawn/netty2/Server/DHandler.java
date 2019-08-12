package com.dawn.netty2.Server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by chenyu on 2019-08-12.
 */
@ChannelHandler.Sharable
public class DHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 输出 bytebuf
        ByteBuf buf = (ByteBuf) msg;
        byte[] content = new byte[buf.readableBytes()];
        buf.readBytes(content);
        System.out.println(Thread.currentThread()+ ": 最终打印"+new String(content));
        ((ByteBuf) msg).release(); // 引用计数减一
        // ctx.fireChannelRead();
    }

    // 异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
