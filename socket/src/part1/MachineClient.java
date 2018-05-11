package part1;

import java.net.Socket;

public class MachineClient {
    Socket socket;
    String name;
    String ip;

    public MachineClient() {
    }

    public MachineClient(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void onClose() {
        System.out.println("listen close");
        System.out.println("InetAddress:"+socket.getInetAddress() + "下线了");
        System.out.println("LocalAddress:"+socket.getLocalAddress());
        System.out.println("LocalSocketAddress:"+socket.getLocalSocketAddress());
        System.out.println("Port:"+socket.getPort() + "下线了");
        System.out.println("LocalPort:"+socket.getLocalPort() + "下线了");
    }
}
