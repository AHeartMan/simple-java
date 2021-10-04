package com.alsace.simplejavajuc;

/**
 * <p>
 *  下面这个示例是个错误的加锁示例，add（）方法是静态的，锁对象是 SynCalc.class，get（）方法锁对象是this，是两把不同的锁，没有互斥关系，不能保护同一个资源count
 *  见附图 lock.png
 * </p>
 *
 * @author sang
 * @since 2021/10/2 0002
 */
public class SynCalc {

    static int count = 0;

    public synchronized void get() {
        System.out.println(count);
    }

    public synchronized static void add() {
        count++;
    }
}
