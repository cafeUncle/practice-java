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
