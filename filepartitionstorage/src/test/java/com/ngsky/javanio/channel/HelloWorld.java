package com.ngsky.javanio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class HelloWorld {
    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("E:\\workspace\\github\\filepartitionstorage\\src\\main\\java\\com\\ngsky\\filepartition\\SeparateFile.java", "rw");
            // FileChannel
            FileChannel fileChannel = raf.getChannel();
            // ByteBuffer
            ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
            int readLen = 0;
            while((readLen = fileChannel.read(buffer)) != -1){
                System.out.println("readLen:" + readLen);
                buffer.flip();

                System.out.println("buffer:" + buffer.getChar());
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
