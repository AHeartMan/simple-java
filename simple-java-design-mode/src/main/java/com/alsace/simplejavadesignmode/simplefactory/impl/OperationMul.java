package com.alsace.simplejavadesignmode.simplefactory.impl;

import com.alsace.simplejavadesignmode.simplefactory.BaseOperation;

/**
 * <p>
 * 乘
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/28 0028
 */
public class OperationMul extends BaseOperation {

    @Override
    public double getResult() {
        return numA * numB;
    }
}
