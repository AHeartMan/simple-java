package com.alsace.simplejavajuc;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * <p>
 * 用信号量简单实现一个对象池的功能，在构造参数里面初始化对象池 pool 和 信号量 semaphore；
 * 提供exec() 方法获取对象，
 * </p>
 *
 * @author sang
 * @since 2021/10/6 0006
 */
public class ObjPool<T, R> {

    private List<T> pool;

    private Semaphore sem;

    public ObjPool(int size, T t) {
        sem = new Semaphore(size);
        pool = new Vector<>();

        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
    }

    R exec(Function<T, R> func) {
        T t = null;
        try {
            sem.acquire();
            t = pool.remove(0);   // get obj
            return func.apply(t);   // return obj
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            pool.add(t);    // release obj
            sem.release();
        }
        return null;
    }

    public static void main(String[] args) {
        ObjPool pool = new ObjPool(10, new DubboTest());

        pool.exec(t -> {
            System.out.println(t);
            return t.toString();
        });
    }
}
