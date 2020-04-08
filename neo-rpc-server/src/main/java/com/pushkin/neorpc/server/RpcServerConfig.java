package com.pushkin.neorpc.server;

import com.pushkin.neorpc.codec.Decoder;
import com.pushkin.neorpc.codec.Encoder;
import com.pushkin.neorpc.codec.JSONDecoder;
import com.pushkin.neorpc.codec.JSONEncoder;
import com.pushkin.neorpc.transport.TransportServer;
import com.pushkin.neorpc.transport.impl.HTTPTransportServer;
import lombok.Data;

/**
 * <p>Title: RpcServerConfig</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：server 配置
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-08 16:23
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
