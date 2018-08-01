package com.ngsky.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        // testCommon();
        ServerSocket socket = new ServerSocket(1344);
        System.out.println("server: waiting for client...");
        while (true) {
            Socket client = socket.accept();
            System.out.println("server: client connected!");
//            BufferedInputStream in = new BufferedInputStream(client.getInputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());

            System.out.println("server: receive message....");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("E:\\test\\hello.pdf")));

            String line = "  ";
            while ((line = in.readLine()) != null && line.length() != 0) {
                bos.write(line.getBytes());
                bos.write("\n".getBytes());
            }
            System.out.println("server: receive message done!");
            bos.close();
            System.out.println("server: send message...");
            out.write("X-Virus-Name:".getBytes());
            out.write("和咯哦\n".getBytes());
            out.write("djsakfjkdasjfklsdjfkldsjfkljsdkldfjksaljfdkdsja\n".getBytes());
            out.write("\n".getBytes());
            out.write("\r\n".getBytes());
            out.flush();
//            in.close();
            out.close();
//            client.close();
            System.out.println("\nDone!");
        }
    }
}
