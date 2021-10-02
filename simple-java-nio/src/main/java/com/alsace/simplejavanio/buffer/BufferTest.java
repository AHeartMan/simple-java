package com.alsace.simplejavanio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;

/**
 * <p>
 * 1. 缓冲区（buffer）:在java Nio中负责数据的存取，缓冲区就是数组，用于存储不用数据类型的数据
 * <p>
 * 根据数据类型不同（boolean除外），提供相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * <p>
 * 上述缓冲区的管理方式几乎一致，通过 allocate()获取缓冲区
 * <p>
 * 2. 缓冲区存取数据的两个核心方法：
 * <p>
 * put()：将数据存入到缓冲区；
 * get()：获取缓冲区中的数据
 * <p>
 * 3. 缓冲区的4个核心属性：
 * capacity：容量，表示缓冲区中最大存储数据的容量，一旦声明不能改变
 * limit：界限，表示缓冲区中可以操作数据的大小，limit后的数据不能进行读写
 * position：位置，表示缓冲区中正在操作数据的位置
 * mark：标记，表示记录当前position的位置，可以通过reset()恢复到mark的位置
 * <p>
 * 0 <= mark <= position <= limit <= capacity
 * <p>
 * 4. 直接缓冲区与非直接缓冲区：
 * 非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在jvm的内存中；
 * 直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区建立在物理内存中，可以提高效率
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/19 0019
 */
public class BufferTest {

    private static String str = "hello word";

    public static void main(String[] args) {
        //分配一个指定大小的缓冲区
        LongBuffer longBuffer = LongBuffer.allocate(1024);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.mark());
        System.out.println(buffer.position());

        //利用put()存放数据
        buffer.put(str.getBytes());

        //切换读模式
        buffer.flip();

        //利用get()读取数据
        byte[] bytes = new byte[1024];
        buffer.get(bytes, 0, buffer.limit());
        System.out.println(new String(bytes));


        //rewind()可重复读
        Buffer rewind = buffer.rewind();
        System.out.println(rewind);

        //清空缓冲区，但是缓冲区中的数据依然存在，只是处于被遗忘的状态
        Buffer clear = buffer.clear();
        System.out.println(clear);
    }
}
