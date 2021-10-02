package com.alsace.simplejavanio.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * <p>
 * 管道：
 * 通信方式
 * sink写数据
 * source读数据
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/26 0026
 */
public class PipeTest {

    public static void main(String[] args) throws IOException {
        //获取管道
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sink = pipe.sink();

        //指定大小的缓冲区
        ByteBuffer srcBuffer = ByteBuffer.allocate(1024);
        ByteBuffer targetBuffer = ByteBuffer.allocate(1024);

        //写数据
        srcBuffer.put("hello world".getBytes());
        srcBuffer.flip();
        sink.write(srcBuffer);

        //读取数据
        Pipe.SourceChannel source = pipe.source();
        int len = source.read(targetBuffer);
        System.out.println(new String(targetBuffer.array(), 0, len));

        //关闭资源
        source.close();
        sink.close();
    }
}
