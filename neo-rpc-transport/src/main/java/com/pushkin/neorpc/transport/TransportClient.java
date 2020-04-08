package com.pushkin.neorpc.transport;

import com.pushkin.neorpc.Peer;

import java.io.InputStream;


/**
 * <p>Title: TransportClient</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：
 * 1. 创建连接
 * 2. 发送数据, 并且等待响应
 * 3. 关闭连接
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-07 19:12
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
