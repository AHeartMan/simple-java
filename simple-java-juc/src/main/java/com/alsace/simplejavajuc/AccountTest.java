package com.alsace.simplejavajuc;

/**
 * <p>
 * 对于两个不同的资源 password，money创建2把锁，各自保护各自的资源，也可以用同一把锁 this,但是会导致关于 password，money 的操作都变成串行的，性能更差
 * </p>
 *
 * @author sang
 * @since 2021/10/2 0002
 */
public class AccountTest {

    private String password;

    private final Object pwl = new Object();

    private double money;

    private final Object mnl = new Object();

    public String getPassword() {
        synchronized (pwl) {
            return password;
        }
    }

    public void setPassword(String password) {
        synchronized (pwl) {
            this.password = password;
        }
    }

    public double getMoney() {
        synchronized (mnl) {
            return money;
        }
    }

    public void setMoney(double money) {
        synchronized (mnl) {
            this.money = money;
        }
    }
}
