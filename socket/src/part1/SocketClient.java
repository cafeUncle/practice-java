package part1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 9090);
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bo = new BufferedOutputStream(outputStream, 1024 * 100);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("创建scanner");
                String s = null;
                if (scanner.hasNextLine()) {
                    s = scanner.nextLine();
                }else {
                    continue;
                }
                System.out.println("键入：" + s);

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
