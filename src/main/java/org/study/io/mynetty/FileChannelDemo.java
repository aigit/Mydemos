package org.study.io.mynetty;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileChannelDemo {

    /*public static void main(String[] args) throws Exception{
        FileChannelDemo fileChannelDemo = new FileChannelDemo();
        //fileChannelDemo.writeFile();
        fileChannelDemo.readFile();
    }*/

    private void writeFile(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\data\\oss\\img\\20210728\\hello.txt");
            String str = "hello,东京奥运会";
            ByteBuffer byteBuffer = ByteBuffer.allocate(25);
            byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));
            FileChannel fileChannel = fileOutputStream.getChannel();
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            fileChannel.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void readFile(){
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\data\\oss\\img\\20210728\\hello.txt");
            FileChannel channel = fileInputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(25);
            channel.read(byteBuffer);

            String str = new String(byteBuffer.array());
            System.out.println("读到的字符串:"+str);
            fileInputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
