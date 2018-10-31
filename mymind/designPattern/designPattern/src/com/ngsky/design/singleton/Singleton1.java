package com.ngsky.design.singleton;

/**
 * 饿汉式
 * 1.java.lang.Runtime
 */
public class Singleton1 {

    private Singleton1() {
    }
    private static final Singleton1 instance = new Singleton1();
    public static Singleton1 getInstance(){
        return instance;
    }

    private int i = 0;
    public void run(){
        System.out.println("run:" + (i++));
    }
}

class Test1{
    public static void main(String[] args){
        Singleton1 instance = Singleton1.getInstance();
        instance.run();
    }
}