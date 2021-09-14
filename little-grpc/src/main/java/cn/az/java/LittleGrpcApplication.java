package cn.az.java;

import cn.az.java.hello.HelloServer;

/**
 * gRPC Server
 *
 * @author az
 * @since 2021-09-14 22:48
 */
public class LittleGrpcApplication {

    public static void main(String[] args) throws Exception {
        HelloServer server = new HelloServer();
        server.start();
        server.blockUntilShutdown();
    }
}
