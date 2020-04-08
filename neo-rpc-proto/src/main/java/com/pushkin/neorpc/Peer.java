package com.pushkin.neorpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>Title: Peer</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：表示网络传输的一个端点
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-07 16:59
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}
