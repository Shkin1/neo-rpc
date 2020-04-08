package com.pushkin.neorpc;

import lombok.Data;

/**
 * <p>Title: Response</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description: RPC返回
 * 描述：
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-07 17:07
 */
@Data
public class Response {
    /**
     * 服务返回编码, 0-成功; 非0失败
     */
    private int code = 0;
    /**
     * 具体的错误信息
     */
    private String message = "ok";
    /**
     * 返回的数据
     */
    private Object data;
}
