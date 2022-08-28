package com.alsace.simplejavanetty.nio;

import java.nio.IntBuffer;

public class Buffer {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(1024);
        intBuffer.put(1);
        intBuffer.put(2);
        intBuffer.put(3);
        intBuffer.put(4);

        System.out.println(intBuffer.limit());
        System.out.println(intBuffer.capacity());
        System.out.println(intBuffer.position());

        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
