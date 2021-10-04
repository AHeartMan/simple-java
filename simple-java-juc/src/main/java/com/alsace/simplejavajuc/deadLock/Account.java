package com.alsace.simplejavajuc.deadLock;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author sang
 * @since 2021/10/3 0003
 */
public class Account {

    private int id;
    private double balance;

    /**
     * 只锁定了自己的账户this.不能锁定目标账户 target
     *
     * @param target
     * @param amt
     */
    public void transfer(Account target, double amt) {
        synchronized (this) {
            if (this.balance >= amt) {
                balance -= amt;
                target.balance += amt;
            }
        }
    }

    /**
     * 同时锁定自己的账户和目标账户之后进行转账，理论上就没问题，但是会出现死锁
     *
     * @param target
     * @param amt
     */
    public void transfer1(Account target, double amt) {
        synchronized (this) {
            synchronized (target) {
                if (this.balance >= amt) {
                    balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }

    /**
     * 通过破坏条件1---占有且等待，来避免死锁
     *
     * @param target
     * @param amt
     */
    public void transfer2(Account target, double amt) {
        Allocator allocator = Allocator.getAllocator();
        try {
            while (!allocator.apply(this, target)) ; // 自旋，申请失败就自动重拾，直到成功为止，有带你消耗cpu，但是加锁粒度比 Account.class细，并发度高一点
            synchronized (this) {
                synchronized (target) {
                    if (this.balance >= amt) {
                        balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            allocator.free(this, target); //释放资源
        }
    }

    /**
     * 通过破坏 循环等待条件，来避免死锁
     *
     * @param target
     * @param amt
     */
    public void transfer3(Account target, double amt) {
        // 先把两个账户按照id进行排序
        Account left = this;
        Account right = target;
        if (this.id > target.id) {
            left = target;
            right = this;
        }

        // 按id顺序进行加锁
        synchronized (left) {
            synchronized (right) {
                if (this.balance >= amt) {
                    balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }

    public static class Allocator {

        volatile static Allocator allocator = null;

        private Allocator() {
        }

        public static Allocator getAllocator() {
            if (null == allocator) {
                synchronized (Allocator.class) {
                    if (null == allocator) {
                        allocator = new Allocator();
                    }
                }
            }
            return allocator;
        }

        private List<Object> list = new ArrayList<>();

        /**
         * 同时申请转出账户和转入账户
         *
         * @param from
         * @param to
         * @return
         */
        private synchronized boolean apply(Object from, Object to) {
            if (list.contains(from) || list.contains(to)) {
                return false;
            }
            list.add(from);
            list.add(to);
            return true;
        }

        /**
         * 释放转出账户和转入账户，不能用clear，因为还有其他的账户信息存在
         *
         * @param from
         * @param to
         */
        private synchronized void free(Object from, Object to) {
            list.remove(from);
            list.remove(to);
//            list.clear();
        }
    }

    /**
     * 使用生产者消费者模式，如果条件不满足，就wait(), 条件满足就通知所有 notifyAll（），不能使用 notify（），因为个别线程有可能会永远通知不到
     * 经典使用范式如下：
     * while(条件不满足){
     *     wait();
     * }
     */
    class Allocator1 {
        private List<Object> list = new ArrayList<>();

        public synchronized void apply(Object from, Object to) {
            while (list.contains(from) || list.contains(to)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            list.add(from);
            list.add(to);
        }

        public synchronized void free(Object from, Object to) {
            list.remove(from);
            list.remove(to);
            notifyAll();
        }
    }
}
