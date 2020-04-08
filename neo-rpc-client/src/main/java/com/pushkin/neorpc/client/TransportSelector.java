package com.pushkin.neorpc.client;

import com.pushkin.neorpc.Peer;
import com.pushkin.neorpc.transport.TransportClient;

import java.util.List;

/**
 * <p>Title: TransportSelector</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：表示选择哪个Server去连接
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-08 21:39
 */
public interface TransportSelector {

    /**
     * 初始化selector
     *
     * @param peers 可以连接的server断点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Peer> peers, int count, Class<? extends  TransportClient> clazz);

    /**
     * 选择一个transport与server做交互
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
    void release(TransportClient client);

    void close();

}
