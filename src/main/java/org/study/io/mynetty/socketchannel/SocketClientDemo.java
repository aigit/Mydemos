package org.study.io.mynetty.socketchannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class SocketClientDemo {

    /*public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",6666);

        boolean connect = socketChannel.connect(inetSocketAddress);
        if(!connect){
            while (!socketChannel.finishConnect()){
                System.out.println("等待连接");
            }
        }

        String str = "hello,东京奥运会";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_8));

        socketChannel.write(byteBuffer);
        System.in.read();
    }*/
}
