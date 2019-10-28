package com.alsace.simplejavadesignmode.simplefactory;

import com.alsace.simplejavadesignmode.simplefactory.impl.OperationAdd;
import com.alsace.simplejavadesignmode.simplefactory.impl.OperationDiv;
import com.alsace.simplejavadesignmode.simplefactory.impl.OperationMul;
import com.alsace.simplejavadesignmode.simplefactory.impl.OperationSub;

/**
 * <p>
 * 运算工厂类，根据不同的操作类型，多态产生不同的运算类
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/28 0028
 */
class OperationFactory {

     static BaseOperation createOperation(String operate){
         BaseOperation oper = null;
        switch (operate){
            case "+":
                oper = new OperationAdd();
                break;
            case "-":
                oper = new OperationSub();
                break;
            case "*":
                oper = new OperationMul();
                break;
            case "/":
                oper = new OperationDiv();
                break;
            default:
                break;
        }
        return oper;
    }
}
