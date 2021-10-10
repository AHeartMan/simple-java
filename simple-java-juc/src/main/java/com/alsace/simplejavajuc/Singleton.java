package com.alsace.simplejavajuc;

/**
 * <p>
 *
 * </p>
 *
 * @author sang
 * @since 2021/10/2 0002
 */
public class Singleton {

    private Singleton(){};

    private Singleton instance;

    /**
     * double check to get a single instance, is not a safety method,
     *  instance = new Singleton(); is three step on cpu,
     *  # init a memory
     *  # point is to instance（when change thread, happen nullPointException）
     *  # init Singleton
     * @return
     */
    public Singleton getinstance() {
        if (null == instance) {
            synchronized(Singleton.class) {
                if (null == instance) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
