package com.alsace.simplejavajava8.lambda.filter;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/11/6 0006
 */
@FunctionalInterface
public interface FilterEmployee<T> {

    boolean test(T t);
}
