package com.pushkin.neorpc.codec;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * <p>Title: JSONDecoderTest</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-07 18:56
 */
public class JSONDecoderTest {
    @Test
    public void decode() {
        JSONEncoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();
        bean.setName("hello");
        bean.setAge(18);
        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);

        JSONDecoder decoder = new JSONDecoder();
        TestBean testBean = new TestBean();
        TestBean decode = decoder.decode(bytes, testBean.getClass());
        System.out.println("name: "+decode.getName()+"  age: "+decode.getAge());
    }
}
