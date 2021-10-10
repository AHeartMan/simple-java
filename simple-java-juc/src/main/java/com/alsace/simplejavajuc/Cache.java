package com.alsace.simplejavajuc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 *
 * </p>
 *
 * @author sang
 * @since 2021/10/10 0010
 */
public class Cache<K, V> {

    final Map<K, V> cache = new HashMap<>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock rk = rwl.readLock();
    final Lock wl = rwl.writeLock();

    /**
     * read cache
     * @param k
     * @return
     */
    public V get(K k) {
        V v = null;
        rk.lock();
        try {
            v = cache.get(k);
        } finally {
            rk.unlock();
        }

        if (null != v) {
            return v;
        }

        wl.lock();
        try {
            v = cache.get(k);
            if (null == v) { // check again if other thread had loaded source data.
                //v= query db;
                cache.put(k, v);
            }
        } finally {
            wl.unlock();
        }
        return v;
    }

    /**
     * write cache
     * @param k
     * @param v
     */
    public void put(K k, V v) {
        wl.lock();
        try {
            cache.put(k, v);
        } finally {
            wl.unlock();
        }
    }
}
