package client;

import java.net.Socket;

public class UserSocket {
    private Socket socket;//客户端的socket
    private String user;//客户名
    public UserSocket(Socket socket, String user) {
        this.socket=socket;
        this.user=user;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUser() {
        return user;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
