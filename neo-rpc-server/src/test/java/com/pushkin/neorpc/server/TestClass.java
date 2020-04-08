package com.pushkin.neorpc.server;

/**
 * <p>Title: TestClass</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-08 16:55
 */
public class TestClass implements TestInterface {
    @Override
    public void hello() {
        System.out.println("hello world");
    }
}
