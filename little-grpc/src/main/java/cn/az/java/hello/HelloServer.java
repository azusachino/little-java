package cn.az.java.hello;

import cn.az.java.hello.proto.HelloServiceGrpc;
import cn.az.java.hello.proto.Request;
import cn.az.java.hello.proto.Response;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author az
 * @since 2021-09-14 22:05
 */
public class HelloServer {

    private static final Logger LOGGER = Logger.getLogger(HelloServer.class.getName());

    private Server server;

    public void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
            .addService(new HelloImpl())
            .build()
            .start();
        LOGGER.info("server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new ThreadFactoryBuilder().build().newThread(
            () -> {
                System.err.println("shutting down gRPC server since JVM is shutting down.");
                try {
                    HelloServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        ));
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static class HelloImpl extends HelloServiceGrpc.HelloServiceImplBase {
        @Override
        public void sayHello(Request request, StreamObserver<Response> responseObserver) {
            Response response = Response.newBuilder().setMsg("Hello " + request.getMsg() + ", today is " + request.getDate()).setCode(200).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
