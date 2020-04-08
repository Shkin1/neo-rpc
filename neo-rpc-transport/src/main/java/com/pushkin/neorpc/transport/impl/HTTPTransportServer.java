package com.pushkin.neorpc.transport.impl;

import com.pushkin.neorpc.transport.RequestHandler;
import com.pushkin.neorpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>Title: HTTPTransportServer</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-07 19:39
 */
@Slf4j
public class HTTPTransportServer implements TransportServer {

    private RequestHandler handler;
    private Server server;

    @Override
    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        this.server = new Server(port);

        // servlet 接受请求
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder, "/*");

    }

    @Override
    public void start() {
        try {
            server.start();
            // 让server等待在这,不要立马返回
            server.join();
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    @Override
    public void stop() {
        try {
            server.stop();
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    class RequestServlet extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("client connect <------------>");

            ServletInputStream in = req.getInputStream();
            OutputStream out = resp.getOutputStream();

            if ( handler != null ){
                handler.onRequest(in, out);
            }

            out.flush();
        }
    }
}
