package part1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    static List<MachineClient> machineClientList = new ArrayList();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            while (true) {
                Socket accept = serverSocket.accept();
                MachineClient machineClient = new MachineClient(accept);
                machineClientList.add(machineClient);
                new Thread(() -> {
                    System.out.println(accept.getInetAddress() + "上线了");
                    InputStream inputStream = null;
                    try {
                        inputStream = accept.getInputStream();
                        System.out.println(inputStream.toString());

                        inputStream = accept.getInputStream();
                        System.out.println(inputStream.toString());

                        inputStream = accept.getInputStream();
                        System.out.println(inputStream.toString());

                        inputStream = accept.getInputStream();
                        System.out.println(inputStream.toString());

                        int read = 0;
                        byte[] bytes = new byte[1024];
                        while ((read = inputStream.read(bytes)) > 0) {
                            System.out.println(new String(bytes,0 , read));
                        }
                    } catch (IOException e) {
                        machineClient.onClose();
//                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
