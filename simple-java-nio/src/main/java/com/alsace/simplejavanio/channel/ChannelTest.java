package com.alsace.simplejavanio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * <p>
 * 1. 通道（channel）：用于源节点与目标节点的连接，在java Nio中负责缓冲区中数据的传输，
 *  Channel本身不存储数据，因此需要配合缓冲区进行传输
 *
 * 2. 通道的主要实现方式：
 *  @see java.nio.channels.Channel 接口：
 *      |--FileChannel
 *      |--SocketChannel
 *      |--ServerSocketChannel
 *      |--DatagramChannel
 *
 * 3. 获取通道
 *  1) java针对支持通道的类提供了 getChannel() 方法
 *      本地IO:
 *      FileInputStream/FileOutputStream
 *      @see RandomAccessFile
 *
 *      网络IO:
 *      Socket
 *      ServerSocket
 *      DatagramSocket
 *
 *  2) 在 JDK1.7 中的NIO2针对各个通道提供了静态方法 open()
 *
 *  3) 在 JDK1.7 中的NIO2的Files工具类的 newByteChannel()
 *
 * 4. 通道之间的数据传输
 *  transferFrom()
 *  transferTo()
 *
 * 5. 分散（Scatter）与聚集（Gather）
 *  分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 *  聚集写入（Gatherint Writes）：将多个缓冲区中的数据聚集到通道中
 *
 * 6. 字符集：Charset
 *  编码：字符串 -> 字节数组
 *  解码：字节数组 -> 字符串
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/20 0020
 */
public class ChannelTest {

    public static void main(String[] args) throws Exception {
        ChannelTest channel = new ChannelTest();
        channel.test1();
        System.out.println("----------------------");
        channel.test2();
        System.out.println("----------------------");
        channel.test3();
        System.out.println("----------------------");
        channel.test4();
    }

    /**
     * 利用通道完成文件的复制（非直接缓冲区）
     */
    public void test1(){
        long start = System.currentTimeMillis();

        //1.获取通道
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fileInputStream = new FileInputStream("D:/work_space/simple-java/simple-java-nio/pic1.jpg");
            fileOutputStream = new FileOutputStream("D:/work_space/simple-java/simple-java-nio/pic2.jpg");
            in = fileInputStream.getChannel();
            out = fileOutputStream.getChannel();

            //2.分配制定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //3.将通道中的数据存入缓冲区
            while (in.read(buffer) != -1){
                //4.切换读模式
                buffer.flip();
                //5.将buffer中的数据写入到通道中
                out.write(buffer);
                //6.清空缓冲区
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeResource(out, in, fileOutputStream, fileInputStream);
        }
        long end = System.currentTimeMillis();
        System.out.println("spend time : " + (end - start));
    }

    private void closeResource(Closeable ... objs){
        for (Closeable obj : objs) {
            if (null != obj){
                try {
                    obj.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用直接缓冲区复制文件（内存映射文件）
     */
    public void test2() throws Exception {
        long start = System.currentTimeMillis();

        //1.获取通道
        FileChannel inChannel = FileChannel.open(Paths.get("D:/work_space/simple-java/simple-java-nio/pic1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:/work_space/simple-java/simple-java-nio/pic3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //2.内存映射文件
        MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //3.直接对缓冲区进行数据的读写操作
       byte[] bytes = new byte[inBuffer.limit()];
       inBuffer.get(bytes);
       outBuffer.get(bytes);

        outChannel.close();
        inChannel.close();

       long end = System.currentTimeMillis();
        System.out.println("spend time : " + (end - start));
    }

    /**
     * 通道之间的传输
     */
    public void test3() throws Exception {
        //获取通道
        FileChannel inChannel = FileChannel.open(Paths.get("D:/work_space/simple-java/simple-java-nio/pic1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:/work_space/simple-java/simple-java-nio/pic5.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //传输数据
        outChannel.transferFrom(inChannel, 0, inChannel.size());
//        inChannel.transferTo(0, inChannel.size(), outChannel);

        outChannel.close();
        inChannel.close();
    }

    /**
     * 分散和聚集
     */
    public void test4() throws Exception {
        //获取通道
        RandomAccessFile raf = new RandomAccessFile("D:/work_space/simple-java/simple-java-nio/pic1.jpg", "rw");
        FileChannel inChannel = raf.getChannel();

        //分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        //分散读取
        ByteBuffer[] byteBuffers = {buf1, buf2};
        inChannel.read(byteBuffers);

        //切换读模式
        for (ByteBuffer byteBuffer : byteBuffers) {
            byteBuffer.flip();
        }

        System.out.println(new String(byteBuffers[0].array(), 0, byteBuffers[0].limit()));
        System.out.println("-----------------");
        System.out.println(new String(byteBuffers[1].array(), 0, byteBuffers[1].limit()));

        //聚集写入
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:/work_space/simple-java/simple-java-nio/pic6.jpg", "rw");
        FileChannel outChannel = randomAccessFile.getChannel();
        outChannel.write(byteBuffers);

        outChannel.close();
        inChannel.close();
    }
}
