package com.alsace.simplejavadesignmode.simplefactory;

/**
 * <p>
 * 运算工具父类
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/28 0028
 */
public abstract class BaseOperation {

    protected double numA = 0;

    protected double numB = 0;

    public double getNumA() {
        return numA;
    }

    public void setNumA(double numA) {
        this.numA = numA;
    }

    public double getNumB() {
        return numB;
    }

    public void setNumB(double numB) {
        this.numB = numB;
    }

    public abstract double getResult() throws Exception;
}
