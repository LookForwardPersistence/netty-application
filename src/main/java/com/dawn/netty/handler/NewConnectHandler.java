package com.dawn.netty.handler;

import com.dawn.netty.TestCenter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.AttributeKey;

import java.util.List;
import java.util.Map;

/**
 * Created by chenyu on 2019-08-12.
 */
public class NewConnectHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        // 解析请求，判断token，拿到用户ID。
        Map<String, List<String>> parameters = new QueryStringDecoder(msg.uri()).parameters();
        // String token = parameters.get("token").get(0);  不是所有人都能连接，比如需要登录之后，发放一个推送的token
        String userId = parameters.get("userId").get(0);
        ctx.channel().attr(AttributeKey.valueOf("userId")).getAndSet(userId); // channel中保存userId
        TestCenter.saveConnection(userId, ctx.channel()); // 保存连接

        // 结束
    }
}
