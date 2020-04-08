package com.pushkin.neorpc.codec;

/**
 * <p>Title: Encoder</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：序列化
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-07 18:47
 */
public interface Encoder {
    byte[] encode(Object obj);
}
