package com.alsace.simplejavajuc;

import java.util.concurrent.Semaphore;

/**
 * <p>
 *
 * </p>
 *
 * @author sang
 * @since 2021/10/6 0006
 */
public class SemaphoreTest1 {

    private int count = 0;
    private final Semaphore semaphore = new Semaphore(1);

    private void add() {
        try {
            semaphore.acquire();
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
