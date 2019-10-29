package com.alsace.simplejavadesignmode.strategymode.impl;

import com.alsace.simplejavadesignmode.strategymode.Strategy;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/29 0029
 */
public class DiscountStrategy extends Strategy {
    @Override
    public double getResult(double money) throws Exception {
        return money * 0.8;
    }
}
