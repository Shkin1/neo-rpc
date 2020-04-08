package com.pushkin.neorpc.server;

import com.pushkin.neorpc.Request;
import com.pushkin.neorpc.utils.ReflectionUtils;

/**
 * <p>Title: ServiceInvoker</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：调用serverviceInstance实例的一个辅助类
 *
 * 调用具体服务
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-08 17:12
 */
public class ServiceInvoker {

    public Object invoke(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(
                service.getTarget(),
                service.getMethod(),
                request.getParameters()
        );
    }
}
