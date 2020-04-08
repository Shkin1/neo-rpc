package com.pushkin.neorpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * <p>Title: ServiceInstance</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：表示一个具体服务
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-08 16:33
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;
}
