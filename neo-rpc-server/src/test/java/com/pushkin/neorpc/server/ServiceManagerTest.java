package com.pushkin.neorpc.server;

import com.pushkin.neorpc.Request;
import com.pushkin.neorpc.ServiceDescriptor;
import com.pushkin.neorpc.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static junit.framework.TestCase.assertNotNull;

/**
 * <p>Title: ServiceManagerTest</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-08 16:54
 */
public class ServiceManagerTest {

    ServiceManager sm;

    @Before
    public void init() {
        sm = new ServiceManager();
        TestClass bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }


    @Test
    public void register() {
        TestClass bean = new TestClass();
        sm.register(TestInterface.class, bean);

    }

    @Test
    public void lookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class, method);

        Request request = new Request();
        request.setService(sdp);

        ServiceInstance sis = sm.lookup(request);
        assertNotNull(sis);
    }
}
