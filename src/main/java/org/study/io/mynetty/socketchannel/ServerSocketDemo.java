package org.study.io.mynetty.socketchannel;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class ServerSocketDemo {

    /*public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        while (true){
            if(selector.select(1000)==0){
                System.out.println("等待了1秒，无连接...");
                continue;
            }
            Set<SelectionKey> selectKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectKeys.iterator();
            while (selectionKeyIterator.hasNext()){
                SelectionKey selectKey = selectionKeyIterator.next();
                if(selectKey.isConnectable()){
                    System.out.println("已经连接");
                }
                if(selectKey.isAcceptable()){
                    System.out.println("已经监听");
                    SocketChannel acceptChannel = serverSocketChannel.accept();
                    acceptChannel.configureBlocking(false);
                    acceptChannel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));
                }
                if(selectKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) selectKey.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("from 客户端"+new String(byteBuffer.array()));

                }
                selectKeys.remove(selectKey);
            }
        }

    }*/
}
