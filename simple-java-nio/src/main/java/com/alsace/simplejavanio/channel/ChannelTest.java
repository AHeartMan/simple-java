package com.alsace.simplejavanio.channel;

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
 *      RandomAccessFile
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
    
}
