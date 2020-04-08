package com.pushkin.neorpc.server;

import com.pushkin.neorpc.Request;
import com.pushkin.neorpc.ServiceDescriptor;
import com.pushkin.neorpc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Title: ServiceManager</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：管理rpc暴露的服务
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-08 16:34
 */
@Slf4j
public class ServiceManager {
    // 1. 注册服务
    // 2. 查找服务

    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    /**
     *
     * @param interfaceClass
     * @param bean 服务提供者;  interfaceClass接口实现的子类
     */
    public <T> void register(Class<T> interfaceClass, T bean){
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);

            services.put(sdp, sis);
            log.info("register service: {} {}", sdp, sis);
        }

    }

    public ServiceInstance lookup(Request request) {
        ServiceDescriptor service = request.getService();
        log.info("lookup service: {}", service.getClass().getName());
        return services.get(service);
    }

}
