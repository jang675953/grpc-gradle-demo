package com.cns;

import com.cns.rpc.demo.GreeterGrpc;
import com.cns.rpc.demo.HelloReply;
import com.cns.rpc.demo.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangbin
 * @email 675953827@qq.com
 * @date 2019/7/4 22:42
 */

public class GRPCClient {


    public static void main(String[] args) {
        try {
            start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void start() throws InterruptedException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();
        GreeterGrpc.GreeterBlockingStub greeterBlockingStub = GreeterGrpc.newBlockingStub(managedChannel);

        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setName("James!")
                .build();
        HelloReply helloReply = greeterBlockingStub.sayHello(helloRequest);
        System.out.println(helloReply.getMessage());
        managedChannel.awaitTermination(5, TimeUnit.SECONDS);
    }
}
