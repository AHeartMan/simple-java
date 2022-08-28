package com.alsace.simplejavajuc;

/**
 * <p>
 *
 * </p>
 *
 * @author sang
 * @since 2021/10/3 0003
 */
public class ThreadTest {

    public static void main(String[] args) {
        ThreadTest t = new ThreadTest();
//        Thread t1= new Thread(() -> thread());
//        t1.start();
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(t1.getName());
//        t1.interrupt();
    }

    private static void thread() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        while (true) {
            if (thread.isInterrupted()) {
                System.out.println("线程中断了.....");
                break;
            }

            System.out.println("线程没中断一直在执行....");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                thread.interrupt();
                e.printStackTrace();
            }
        }
    }
}
