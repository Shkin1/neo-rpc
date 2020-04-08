package com.pushkin.neorpc;

import com.pushkin.neorpc.client.RpcClient;

/**
 * <p>Title: Client</p>
 * <p>https://github.com/Shkin1/neo-rpc.git </p>
 * <p>Description:
 * 描述：
 * </p>
 *
 * @author jinpu.shi
 * @version v1.0.0
 * @since 2020-04-08 22:41
 */
public class Client {
    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        CalcService service = rpcClient.getProxy(CalcService.class);

        int addResult = service.add(1, 2);
        int minusResult = service.minus(10, 5);

        System.out.println(addResult);
        System.out.println(minusResult);
    }
}
