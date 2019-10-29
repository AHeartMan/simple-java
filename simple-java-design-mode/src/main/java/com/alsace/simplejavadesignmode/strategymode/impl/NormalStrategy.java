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
public class NormalStrategy extends Strategy {
    @Override
    public double getResult(double money) {
        return money;
    }
}
