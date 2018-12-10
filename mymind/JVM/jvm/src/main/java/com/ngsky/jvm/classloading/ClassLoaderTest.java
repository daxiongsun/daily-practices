package com.ngsky.jvm.classloading;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                System.out.println("name:" + name);
                String fileName = "D:\\github\\mymind\\daily-practices\\mymind\\JVM\\jvm\\target\\classes\\" + name + ".class";
                try {
                    InputStream is = new FileInputStream(fileName);
                    if (is == null) {
                        super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    return defineClass(name, b, 0, b.length);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return super.loadClass(name);
            }
        };

        Object object = classLoader.loadClass("com.ngsky.jvm.classloading.ClassLoaderTest");
        System.out.println(object);
        System.out.println(object instanceof com.ngsky.jvm.classloading.ClassLoaderTest);
    }
}
