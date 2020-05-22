package sample;

import sample.server.Server;

public class ServerStarter {
    public static void main(String[] argc) {
        Server server = new Server();
        server.startServer();
    }
}
