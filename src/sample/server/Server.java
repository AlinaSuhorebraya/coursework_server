package sample.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server implements ConnectionListener {

    private Set<Connection> connections = new HashSet<Connection>();//когда несколько клиентов Хеш-таблица представляет такую структуру данных, в которой все объекты имеют уникальный ключ или хеш-код. Данный ключ позволяет уникально идентифицировать объект в таблице.

    private ServerSocket serverSocket;

    public Server() {
        try {
            this.serverSocket = new ServerSocket(Connection.PORT);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void startServer() {

        System.out.println("Server  started..");

        while (true) {
            try {
                Socket socket = serverSocket.accept();//принять изменения

                connectionCreated(new ConnectionListenerImpl(socket, this));//чтобы инфу прослушивал коннекшнлисенер
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }


    @Override
    public void connectionCreated(Connection c) {
        System.out.println("Connection added." + c.toString());

        connections.add(c);
    }

    @Override
    public void connectionClose(Connection c) {
        System.out.println("Connection closed");
        connections.remove(c);
        c.close();
    }

    @Override
    public void connectionException(Exception ex) {
        ex.printStackTrace();
    }
}
