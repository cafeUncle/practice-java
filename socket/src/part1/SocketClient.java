package part1;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class SocketClient {

    static String ip = null;
    static Integer port = null;

    static {
        Properties properties = new Properties();
        try {
//            properties.load(SocketClient.class.getClassLoader().getResourceAsStream("config.properties"));
            properties.load(new FileInputStream("config.properties"));
            ip = properties.getProperty("ip");
            port = Integer.valueOf(properties.getProperty("port"));
            properties.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        byte[] data = new byte[]
                {(byte) 0x8e, 2, 3, 5, (byte) 0xED};
        Socket socket = null;
        try{
            socket = new Socket(ip, port);
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bo = new BufferedOutputStream(outputStream, 1024 * 100);
            bo.write(data);
            bo.flush();
            bo.close();
            socket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main2(String[] args) {
        byte[] data = new byte[]
                {142-256, 0, 23, 66, 74, 70, 76, 48, 48, 48, 48, 48, 48, 48, 48, 49, 1, 50, 0, 0, 1, 168-256, 237-256};
        Socket socket = null;
        try{
            socket = new Socket(ip, port);
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bo = new BufferedOutputStream(outputStream, 1024 * 100);
            bo.write(data);
            bo.flush();
            bo.close();
            socket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main1(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bo = new BufferedOutputStream(outputStream, 1024 * 100);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String s = null;
                if (scanner.hasNextLine()) {
                    s = scanner.nextLine();
                }else {
                    System.out.println("未发现");
                    continue;
                }

                if ("logout".equals(s)) {
                    break;
                }
                bo.write(s.getBytes());
    //            System.out.println(String.valueOf(0x41));   = 65 十进制
                bo.flush();
            }
            scanner.close();
            bo.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
