package Networking.Server;

import java.net.Socket;

public abstract class AbsConcurrentServer extends AbstractServer{
    public AbsConcurrentServer(int port) {
        super(port);
        System.out.println("Concurrent AbstractServer");
    }

    @Override
    protected void processRequest(Socket client) {

        Thread thread = createWorker(client);
        thread.start();
    }

    protected abstract Thread createWorker(Socket client);
}
