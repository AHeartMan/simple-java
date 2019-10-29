package com.alsace.simplejavadesignmode.strategymode.cashtypeenum;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/29 0029
 */
public enum CashTypeEnum {

    NORMAL("正常收费"),
    FULLREDUCTION("满减"),
    DISCOUNT("打折");

    private String type;

    CashTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
