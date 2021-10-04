package com.alsace.simplejavajuc;

/**
 * <p>
 *
 * </p>
 *
 * @author sang
 * @since 2021/10/2 0002
 */
public class Test1 {

    private Integer count = 0;

    public void add10k() {
        int i = 0;
        while (i++ < 100000) {
            count++;
        }
    }

    /**
     * no lock on add10k() method, result of main is not 200000, but a number between 100000 and 200000
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Test1 test = new Test1();
        Thread t1 = new Thread(() -> test.add10k());
        Thread t2 = new Thread(() -> test.add10k());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(test.count);
    }
}
