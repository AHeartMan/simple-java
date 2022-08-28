package com.alsace.simplejavanetty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Channel {

    public static void main(String[] args) throws Exception{
        String str = "一个小目标";
        FileOutputStream fileOutputStream = new FileOutputStream("d:/work_space/test/channel.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);
        channel.close();
        fileOutputStream.close();
    }
}
