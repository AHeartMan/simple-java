package com.alsace.simplejavajuc;

/**
 * <p>
 *
 * </p>
 *
 * @author sang
 * @since 2021/10/3 0003
 */
public class Test2 {

    private int count = 0;

    public synchronized int get() {
        return count;
    }

    public synchronized void set(int count) {
        this.count = count;
    }

    public void add10k() {
        for (int i = 0; i < 10000; i++) {
            set(get() + 1);  // 这一步操作不是原子操作，虽然set(), get()方法分别是线程安全的，但是组合在一起不是安全的
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test2 test2 = new Test2();
        Thread t1 = new Thread(() -> test2.add10k());
        Thread t2 = new Thread(() -> test2.add10k());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(test2.count);
    }
}
