package com.pushkin.neorpc.server;

import com.pushkin.neorpc.Request;
import com.pushkin.neorpc.Response;
import com.pushkin.neorpc.codec.Decoder;
import com.pushkin.neorpc.codec.Encoder;
import com.pushkin.neorpc.transport.RequestHandler;
import com.pushkin.neorpc.transport.TransportServer;
import com.pushkin.neorpc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>Title: RpcServer</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-08 17:22
 */
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(){
        this.config=new RpcServerConfig();
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(),this.handler);

        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass,T bean){
        serviceManager.register(interfaceClass,bean);
    }

    public void start(){
        this.net.start();
    }
    public void stop(){
        this.net.stop();
    }
    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response resp = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(recive, recive.available(),true);
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request: {}", request);
                ServiceInstance sis = serviceManager.lookup(request);
                serviceInvoker.invoke(sis, request);
                Object ret = serviceInvoker.invoke(sis, request);
                resp.setData(ret);
            }catch (IOException e){
                log.warn(e.getMessage(),e);
                resp.setCode(1);
                resp.setMessage("RpcServer got error: "+e.getClass().getName()+":"+e.getMessage());
            }finally {
                try {
                    byte[] outBytes = encoder.encode(resp);
                    toResp.write(outBytes);
                }catch (IOException e){
                    log.warn(e.getMessage(),e);
                }

            }
        }
    };

}
