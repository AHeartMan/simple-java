package com.alsace.simplejavajuc;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *
 * </p>
 *
 * @author sang
 * @since 2021/10/6 0006
 */
public class DubboTest {

    private String response;

    private final Lock lock = new ReentrantLock();

    private final Condition done = lock.newCondition();

    public Object get(Long timeOut) throws TimeoutException {
        long startTime = System.nanoTime();
        lock.lock();
        try {
            while (response == null) {
                done.awaitNanos(timeOut);

                if (response != null || System.nanoTime() - startTime > timeOut) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        if (response == null) {
            throw new TimeoutException();
        }
        return response;
    }


    public void received(String res) {
        lock.lock();
        try {
            response = res;
            done.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
