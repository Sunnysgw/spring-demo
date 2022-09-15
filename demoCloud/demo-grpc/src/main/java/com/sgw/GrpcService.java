package com.sgw;

import io.grpc.stub.StreamObserver;

/**
 * @description:
 * @author: sunnysgw
 * @since: 1.0
 **/
public class GrpcService extends transPersonGrpc.transPersonImplBase{

    @Override
    public void getPerson(Demo.Person request, StreamObserver<Demo.Person> responseObserver) {
        Demo.Person response = null;
        try {
            response = Demo.Person.newBuilder()
                    .setEmail("hello@world.com")
                    .build();
        } catch (Exception e) {
            responseObserver.onError(e);
        } finally {
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }
}
