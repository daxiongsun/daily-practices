package com.ngsky.jvm.classloader;

import java.io.*;

/**
 * <dl>
 * <dt>SimpleClassloaderTest</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 12/7/2018 11:31 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class SimpleClassloaderTest {
    public static void main(String[] args){
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                System.out.println("name:" + name);
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
//                String fileName = name + ".class";
                System.out.println("fileName:" + fileName);
                InputStream is = null;
                try {
                    is = new FileInputStream(new File("D:\\life\\projects\\daily-practices\\mymind\\daily-practices\\mymind\\JVM\\jvm\\src\\main\\java\\com\\ngsky\\jvm\\classloader\\SimpleClassloaderTest.class"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                if(is == null){
                    System.out.println("++++++");
                    return super.loadClass(name);
                }
                try {
                    System.out.println("..." + name);
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return super.loadClass(name);
            }
        };
        try {
            Object obj = myLoader.loadClass("com.ngsky.jvm.classloader.SimpleClassloaderTest");
            System.out.println(obj.getClass());
            System.out.println(obj instanceof com.ngsky.jvm.classloader.SimpleClassloaderTest);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
