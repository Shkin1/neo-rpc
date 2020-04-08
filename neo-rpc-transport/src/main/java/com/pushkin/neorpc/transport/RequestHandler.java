package com.pushkin.neorpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>Title: RequestHandler</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：处理网络请求的handler
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-07 19:18
 */
public interface RequestHandler {
    void onRequest(InputStream receive, OutputStream toResp);
}
