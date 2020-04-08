package com.pushkin.neorpc.client;

import com.pushkin.neorpc.Peer;
import com.pushkin.neorpc.codec.Decoder;
import com.pushkin.neorpc.codec.Encoder;
import com.pushkin.neorpc.codec.JSONDecoder;
import com.pushkin.neorpc.codec.JSONEncoder;
import com.pushkin.neorpc.transport.TransportClient;
import com.pushkin.neorpc.transport.impl.HTTPTransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Title: RpcClientConfig</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-08 22:04
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private  Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount =1;
    /**
     *  server的地址信息
     */
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1",3000));
}
