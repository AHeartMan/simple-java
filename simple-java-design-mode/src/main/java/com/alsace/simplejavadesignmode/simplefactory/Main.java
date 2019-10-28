package com.alsace.simplejavadesignmode.simplefactory;

/**
 * <p>
 * 测试类
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/28 0028
 */
public class Main {

    public static void main(String[] args) {
        BaseOperation operation = OperationFactory.createOperation("/");
        operation.numA = 1;
        operation.numB = 10;
        try {
            double result = operation.getResult();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("----------------------");
        BaseOperation operation1 = OperationFactory.createOperation("+");
        operation1.numA = 19000;
        operation1.numB = 98000;
        try {
            double result = operation1.getResult();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
