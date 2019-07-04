package com.cns;

import com.cns.rpc.demo.GreeterGrpc;
import com.cns.rpc.demo.HelloReply;
import com.cns.rpc.demo.HelloRequest;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * @author zhangbin
 * @email 675953827@qq.com
 * @date 2019/7/4 22:42
 */

public class GRPCServer extends GreeterGrpc.GreeterImplBase {

    public static void main(String[] args) {
        GRPCServer grpcServer = new GRPCServer();
        try {
            grpcServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException, InterruptedException {
        Server server =
                ServerBuilder.forPort(8080)
                        .addService(this)
                        .executor(Executors.newFixedThreadPool(1))
                        .build();
        server.start();
        server.awaitTermination();
    }


    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> observer) {
        observer.onNext(HelloReply.newBuilder().setMessage("Hello, " + request.getName()).build());
        observer.onCompleted();
    }
}
