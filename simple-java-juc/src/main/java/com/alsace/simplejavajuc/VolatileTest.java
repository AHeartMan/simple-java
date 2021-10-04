package com.alsace.simplejavajuc;

/**
 * <p>
 *
 * </p>
 *
 * @author sang
 * @since 2021/10/2 0002
 */
public class VolatileTest {
    private int num = 0;
    private volatile boolean flag = false;

    public void set() {
        num = 42;
        flag = true;
    }

    public void get() {
        if (flag) {
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        test.set();
        test.get();
    }
}
