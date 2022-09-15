package com.sgw;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @description:
 * @author: sunnysgw
 * @since: 1.0
 **/
public class GrpcServer {

    private static final Integer port = 9999;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(port)
                .addService(new GrpcService())
                .build()
                .start();
        server.awaitTermination();

    }

}
