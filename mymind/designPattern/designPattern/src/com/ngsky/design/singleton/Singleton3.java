package com.ngsky.design.singleton;

/**
 * 线程安全 单例模式
 */
public class Singleton3 {
    private static Singleton3 instance;
    private Singleton3(){}
    
    public static synchronized Singleton3 getInstance(){
        if(instance == null){
            instance = new Singleton3();
        }
        return instance;
    }

    public int i = 0;
}

class Test3{
    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread1：****");
                Singleton3 instance = Singleton3.getInstance();
                for(int i = 0;i < 50;i++){
                    instance.i = instance.i + 1;
                    System.out.println("Thread1:" + instance.i);
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread2:****");
                Singleton3 instance = Singleton3.getInstance();
                for(int i = 0;i < 50;i++){
                    instance.i = instance.i + 1;
                    System.out.println("Thread2:" + instance.i);
                }
            }
        };

        thread1.start();
        thread2.start();
    }
}
//////OUTPUT://///////////
//Thread1：****
//Thread2:****
//Thread1:2
//Thread2:2
//Thread1:3
//Thread1:5
//Thread2:4
//////OUTPUT://///////////