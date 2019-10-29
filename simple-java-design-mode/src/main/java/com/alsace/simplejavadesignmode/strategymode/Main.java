package com.alsace.simplejavadesignmode.strategymode;


import com.alsace.simplejavadesignmode.strategymode.cashtypeenum.CashTypeEnum;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/30 0030
 */
public class Main {

    public static void main(String[] args) throws Exception {
        CashContext cc = new CashContext();
        double result = cc.getInstance(CashTypeEnum.NORMAL.getType())
                .getResult(300);
        System.out.println(result);

        System.out.println("------------------");

        double result1 = cc.getInstance(CashTypeEnum.DISCOUNT.getType())
                .getResult(3000);
        System.out.println(result1);
    }
}
