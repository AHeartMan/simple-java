package com.alsace.simplejavajuc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *  利用lock锁来实现一个线程安全的队列，包含出对和入队两个方法，
 *  入对操作，如果发现队列已经满了，就把线程放到 notFull 这个条件变量的等待队列里面等待，等待队列不满的情况出现
 *      否则，执行入队操作，入队成功之后，队列肯定不空了，就唤醒 notEmpty 这个条件变量的等待队列里面的线程，
 *  出对操作，原理是一样的
 * </p>
 *
 * @author sang
 * @since 2021/10/3 0003
 */
public class SynchQueueDemo {

    private final Lock lock = new ReentrantLock();

    private final Condition notEmpty = lock.newCondition();   // 队列不空

    private final Condition notFull = lock.newCondition();    // 队列不满

    /**
     * 入队操作
     */
    public void enq() {
        lock.lock();
        try {
            while (1 == (int)Math.random()) {// todo 判断队列满了
                notFull.await();
            }
            // 执行入队操作
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void deq() {
        lock.lock();
        try {
            while (1 == (int)Math.random()) { //todo 判断队列为空
                notEmpty.await();
            }
            //执行出对操作
            notFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
