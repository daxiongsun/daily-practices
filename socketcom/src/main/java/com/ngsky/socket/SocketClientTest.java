package com.ngsky.socket;

import java.io.*;
import java.net.Socket;

/***
 * @desc 本机网络传输 1.78G 大文件：25s 可以接受
 * 需要注意的是，从Socket的输入流中读取数据并不能读取文件那样，一直调用read()方法直到返回-1为止，因为对Socket而言，只有当服务端关闭连接时，Socket的输入流才会返回-1，
 * 而是事实上服务器并不会不停地关闭连接。假设我们想要通过一个连接发送多个请求，那么在这种情况下关闭连接就显得非常愚蠢。
 *
 * 因此，从Socket的输入流中读取数据时我们必须要知道需要读取的字节数，这可以通过让服务器在数据中告知发送了多少字节来实现，也可以采用在数据末尾设置特殊字符标记的方式连实现。
 * @author sunyx
 */
public class SocketClientTest {
    public static void main(String[] args) throws IOException {

        System.out.println("client: request connect with server ...");
        long startTime = System.currentTimeMillis();
        Socket socket = new Socket("172.16.40.240", 1344);
//        socket.setSoTimeout(3000);
        BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());

        System.out.println("client: send message...");
        BufferedInputStream fileBis = new BufferedInputStream(new FileInputStream(
                new File("C:\\Users\\sunyx\\Desktop\\2018年书单\\Understanding the JVM Advanced Features and Best Practices_2_220_.pdf")));

        int fileLen = 0;
        byte[] fileBuffer = new byte[8192];
        while ((fileLen = fileBis.read(fileBuffer)) > 0) {
            output.write(fileBuffer, 0, fileLen);
            output.write("\r\n".getBytes());
            output.write("0\r\n\r\n".getBytes());
            output.flush();
        }
        System.out.println("client: send message done!");
        fileBis.close();
        output.write("\n".getBytes());
        output.write("0\n\n".getBytes());
        output.flush();

        // receive message
        System.out.println("client: receive message...");
        int len = 0;
        byte[] buffer = new byte[2];
        while ((len = input.read(buffer)) > 0) {
            // doWork
            System.out.println(String.valueOf(buffer));
        }
        System.out.println("client: receive message done!");

        fileBis.close();
//        input.close();
        output.close();
        socket.close();
        socket = null;
        System.out.println("\nDone!");
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }
}
