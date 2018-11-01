package com.ngsky.design.singleton;

/**
 * <dl>
 * <dt>Singleton6</dt>
 * <dd>Description:线程安全式懒汉式</dd>
 * <dd>CreateDate: 11/1/2018 11:20 PM</dd>
 * </dl>
 * 该种方法线程安全，但是效率低下,很多场景下可能不需要同步方法
 * @author daxiong
 */
public class Singleton6 {
    private static Singleton6 instance;

    private Singleton6() {

    }

    public synchronized static Singleton6 getInstance(){
        if(instance == null){
            instance = new Singleton6();
        }
        return instance;
    }
}

class Test6{
    public static void main(String[] args){
        Thread thread1 = new Thread(){
            public void run(){
                for(int i = 0;i < 20;i++){
                    Singleton6 instance = Singleton6.getInstance();
                    System.out.println("thread1:" + "i:" + i + "," + instance);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Thread thread2 = new Thread(){
            public void run(){
                for(int i = 0;i < 20;i++){
                    Singleton6 instance = Singleton6.getInstance();
                    System.out.println("thread2:" + "i:" + i + "," + instance);
                }
            }
        };

        Thread thread3 = new Thread(){
            public void run(){
                for(int i = 0;i < 20;i++){
                    Singleton6 instance = Singleton6.getInstance();
                    System.out.println("thread3:" + "i:" + i + "," + instance);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
