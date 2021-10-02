package com.alsace.simplejavadesignmode.strategymode;

import com.alsace.simplejavadesignmode.strategymode.impl.DiscountStrategy;
import com.alsace.simplejavadesignmode.strategymode.impl.FullReductionStrategy;
import com.alsace.simplejavadesignmode.strategymode.impl.NormalStrategy;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/29 0029
 */
public class CashContext {

    private volatile Strategy strategy;

    public synchronized Strategy getInstance(String type) {
        switch (type) {
            case "正常收费":
                strategy = new NormalStrategy();
                break;
            case "满减":
                strategy = new FullReductionStrategy();
                break;
            case "打折":
                strategy = new DiscountStrategy();
                break;
            default:
                break;
        }
        return strategy;
    }

    public double getResult(double money) {
        try {
            return strategy.getResult(money);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
