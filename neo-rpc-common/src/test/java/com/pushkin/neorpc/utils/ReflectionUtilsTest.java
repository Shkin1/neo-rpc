package com.pushkin.neorpc.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * <p>Title: ReflectionUtilsTest</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-07 18:27
 */
public class ReflectionUtilsTest {


    @Test
    public void newInstance() {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(t);

    }

    @Test
    public void getPublicMethod() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1, methods.length);
        String name = methods[0].getName();
        assertEquals("bMethod", name);
    }

    @Test
    public void invoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Method b = methods[0];
        TestClass t = new TestClass();
        Object r = ReflectionUtils.invoke(t, b);
        assertEquals("b", r);

    }
}
