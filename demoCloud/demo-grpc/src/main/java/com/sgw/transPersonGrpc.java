package com.sgw;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.11.0)",
    comments = "Source: demo.proto")
public final class transPersonGrpc {

  private transPersonGrpc() {}

  public static final String SERVICE_NAME = "protocobuff_Demo.transPerson";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getGetPersonMethod()} instead.
  public static final io.grpc.MethodDescriptor<Demo.Person,
      Demo.Person> METHOD_GET_PERSON = getGetPersonMethodHelper();

  private static volatile io.grpc.MethodDescriptor<Demo.Person,
      Demo.Person> getGetPersonMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<Demo.Person,
      Demo.Person> getGetPersonMethod() {
    return getGetPersonMethodHelper();
  }

  private static io.grpc.MethodDescriptor<Demo.Person,
      Demo.Person> getGetPersonMethodHelper() {
    io.grpc.MethodDescriptor<Demo.Person, Demo.Person> getGetPersonMethod;
    if ((getGetPersonMethod = transPersonGrpc.getGetPersonMethod) == null) {
      synchronized (transPersonGrpc.class) {
        if ((getGetPersonMethod = transPersonGrpc.getGetPersonMethod) == null) {
          transPersonGrpc.getGetPersonMethod = getGetPersonMethod = 
              io.grpc.MethodDescriptor.<Demo.Person, Demo.Person>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protocobuff_Demo.transPerson", "getPerson"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Demo.Person.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Demo.Person.getDefaultInstance()))
                  .setSchemaDescriptor(new transPersonMethodDescriptorSupplier("getPerson"))
                  .build();
          }
        }
     }
     return getGetPersonMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static transPersonStub newStub(io.grpc.Channel channel) {
    return new transPersonStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static transPersonBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new transPersonBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static transPersonFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new transPersonFutureStub(channel);
  }

  /**
   */
  public static abstract class transPersonImplBase implements io.grpc.BindableService {

    /**
     */
    public void getPerson(Demo.Person request,
                          io.grpc.stub.StreamObserver<Demo.Person> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPersonMethodHelper(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetPersonMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                Demo.Person,
                Demo.Person>(
                  this, METHODID_GET_PERSON)))
          .build();
    }
  }

  /**
   */
  public static final class transPersonStub extends io.grpc.stub.AbstractStub<transPersonStub> {
    private transPersonStub(io.grpc.Channel channel) {
      super(channel);
    }

    private transPersonStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected transPersonStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new transPersonStub(channel, callOptions);
    }

    /**
     */
    public void getPerson(Demo.Person request,
                          io.grpc.stub.StreamObserver<Demo.Person> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPersonMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class transPersonBlockingStub extends io.grpc.stub.AbstractStub<transPersonBlockingStub> {
    private transPersonBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private transPersonBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected transPersonBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new transPersonBlockingStub(channel, callOptions);
    }

    /**
     */
    public Demo.Person getPerson(Demo.Person request) {
      return blockingUnaryCall(
          getChannel(), getGetPersonMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class transPersonFutureStub extends io.grpc.stub.AbstractStub<transPersonFutureStub> {
    private transPersonFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private transPersonFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected transPersonFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new transPersonFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Demo.Person> getPerson(
        Demo.Person request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPersonMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_PERSON = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final transPersonImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(transPersonImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_PERSON:
          serviceImpl.getPerson((Demo.Person) request,
              (io.grpc.stub.StreamObserver<Demo.Person>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class transPersonBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    transPersonBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Demo.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("transPerson");
    }
  }

  private static final class transPersonFileDescriptorSupplier
      extends transPersonBaseDescriptorSupplier {
    transPersonFileDescriptorSupplier() {}
  }

  private static final class transPersonMethodDescriptorSupplier
      extends transPersonBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    transPersonMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (transPersonGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new transPersonFileDescriptorSupplier())
              .addMethod(getGetPersonMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
