package com.pushkin.neorpc.transport;

/**
 * <p>Title: TransportServer</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：
 * 1. 启动、监听
 * 2. 接受请求 --> 抽象出Handler 让使用者实现
 * 3. 关闭监听
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-07 19:16
 */
public interface TransportServer {
    void init(int port, RequestHandler handler);
    void start();
    void stop();
}
