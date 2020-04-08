package com.pushkin.neorpc.client;

import com.pushkin.neorpc.Request;
import com.pushkin.neorpc.Response;
import com.pushkin.neorpc.ServiceDescriptor;
import com.pushkin.neorpc.codec.Decoder;
import com.pushkin.neorpc.codec.Encoder;
import com.pushkin.neorpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>Title: RemoteInvoker</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-08 22:02
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {

    private Class clazz;

    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.decoder = decoder;
        this.encoder = encoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);
        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote:" + response);
        }
        return response.getData();
    }


    private Response invokeRemote(Request request) {
        Response resp = null;
        TransportClient client = null;
        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream revice = client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(revice, revice.available(), true);
            resp = decoder.decode(inBytes, Response.class);
        }
        catch (IOException e) {
            log.warn(e.getMessage(), e);
            resp = new Response();
            resp.setCode(1);
            resp.setMessage("RpcClient got error:" + e.getClass() + ":" + e.getMessage());
        }
        finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return resp;
    }
}
