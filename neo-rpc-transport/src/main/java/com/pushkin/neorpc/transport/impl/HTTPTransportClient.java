package com.pushkin.neorpc.transport.impl;

import com.pushkin.neorpc.Peer;
import com.pushkin.neorpc.transport.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;

/**
 * <p>Title: HTTPTransportClient</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-07 19:21
 */
public class HTTPTransportClient implements TransportClient {

    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpURLConnection= (HttpURLConnection)new URL(url).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.connect();
            IOUtils.copy(data,httpURLConnection.getOutputStream());

            int resultCode = httpURLConnection.getResponseCode();
            if(resultCode==HttpURLConnection.HTTP_OK){
                return httpURLConnection.getInputStream();
            }else {
                return httpURLConnection.getErrorStream();
            }
        }catch (IOException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
