package com.ngsky.design.singleton;

/**
 * 懒汉式
 */
public class Singleton2 {
    private static Singleton2 instance;
    private Singleton2(){}
    public static Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }

    public void run(){
        System.out.println("run:");
    }
}

class Test2{/**/
    public static void main(String[] args) {
        Singleton2 singleton2 = Singleton2.getInstance();
        singleton2.run();
    }
}