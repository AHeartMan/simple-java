package com.alsace.simplejavajava8.jvm;

import java.util.ArrayList;
import java.util.List;

public class HeapOom {

    /**
     * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        for (;;){
            System.out.println(list.size());
            list.add(new byte[1024*1024]);
        }
    }
}
