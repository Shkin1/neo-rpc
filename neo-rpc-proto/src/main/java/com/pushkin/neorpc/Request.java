package com.pushkin.neorpc;

import lombok.Data;

/**
 * <p>Title: Request</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description: RPC请求
 * 描述：
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-07 17:03
 */
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;
}
