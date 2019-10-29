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
public class FullReductionStrategy extends Strategy {

    private final double FullReductionMoney = 300;

    @Override
    public double getResult(double money) throws Exception {
        if (money < FullReductionMoney) {
            throw new Exception("金额不足300，不符合满减要求");
        }
        return money - 100;
    }
}
