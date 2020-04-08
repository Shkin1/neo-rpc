package com.pushkin.neorpc.codec;

/**
 * <p>Title: Decoder</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：反序列化
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-07 18:49
 */
public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}
