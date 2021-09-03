package org.study.io.mynetty;


import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioDemo {

    /*public static void main(String[] args) throws Exception {
        ExecutorService threadPools = Executors.newCachedThreadPool();

        final ServerSocket serverSocket = new ServerSocket(6666);

        while (true){
            final Socket mySocket = serverSocket.accept();
            threadPools.execute(new Runnable() {
                @Override
                public void run() {
                    handleSocket(mySocket);
                }
            });
        }
    }*/

    private static void handleSocket(Socket mySocket){
        byte[] mybyte = new byte[1024];
        try{
            while (true){
                System.out.println("开始监听");

                InputStream myinput = mySocket.getInputStream();
                int readed = myinput.read(mybyte);
                if(readed!=1){
                    System.out.print("收到:"+new String(mybyte,0,readed));
                }else {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
