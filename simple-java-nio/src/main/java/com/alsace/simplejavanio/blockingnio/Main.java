package com.alsace.simplejavanio.blockingnio;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/10/25 0025
 */
public class Main {

    public static void main(String[] args) throws Exception {
        TestBlockingNio blockingNio = new TestBlockingNio();
        blockingNio.client();
    }
}
