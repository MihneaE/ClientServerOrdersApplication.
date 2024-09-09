package Networking.Server;

import Networking.RpcProtocol.ClientRpcReflectionWorker;
import Service.ServiceManager;

import java.net.Socket;

public class RpcConcurrentServer extends AbsConcurrentServer {

    private ServiceManager server;

    public RpcConcurrentServer(int port, ServiceManager server) {
        super(port);
        this.server = server;
        System.out.println("RpcConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {

        ClientRpcReflectionWorker worker = new ClientRpcReflectionWorker(server, client);

        Thread thread = new Thread(worker);
        return thread;
    }

    @Override
    public void stop() {

        System.out.println("Stopping services...");
    }
}
