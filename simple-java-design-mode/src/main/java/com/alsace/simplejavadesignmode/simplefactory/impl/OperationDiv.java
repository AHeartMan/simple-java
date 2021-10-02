package com.alsace.simplejavadesignmode.simplefactory.impl;

import com.alsace.simplejavadesignmode.simplefactory.BaseOperation;

/**
 * <p>
 * 除
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/28 0028
 */
public class OperationDiv extends BaseOperation {

    @Override
    public double getResult() throws Exception {
        if (numB == 0) {
            throw new Exception("除数不能为 0");
        }
        return numA / numB;
    }
}
