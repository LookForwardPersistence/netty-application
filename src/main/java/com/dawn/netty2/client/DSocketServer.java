package com.dawn.netty2.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chenyu on 2019-08-12.
 */
public class DSocketServer {
    public static void main(String[] args) throws IOException, Exception {
        // server
        ServerSocket serverSocket = new ServerSocket(9999);

        // 获取新连接
        while (true) {
            final Socket accept = serverSocket.accept();
            // accept.getOutputStream().write("推送实例");
            InputStream inputStream = accept.getInputStream();
            while (true) {
                byte[] request = new byte[220];
                int read = inputStream.read(request);
                if (read == -1) {
                    break;
                }
                // 得到请求内容，解析，得到发送对象和发送内容
                String content = new String(request);
                if(content.getBytes().length > 220) {
                    // TODO
                } else if(content.getBytes().length < 220) {

                }
                // 每次读取到的数据，不能够保证是一条完整的信息
                System.out.println(content);
            }
        }
    }

}