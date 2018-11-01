package com.ngsky.design.singleton;

/**
 * <dl>
 * <dt>Singleton5</dt>
 * <dd>Description:简单非线程安全的懒汉式单例模式</dd>
 * <dd>CreateDate: 11/1/2018 11:14 PM</dd>
 * </dl>
 *
 * 出现多个实例，多个线程同时进入 判空逻辑。instance 被多次实例化
 *
 * @author daxiong
 */
public class Singleton5 {

    private static Singleton5 instance;

    private Singleton5(){}

    public static Singleton5 getInstance(){
        if(instance == null){
            instance = new Singleton5();
        }
        return instance;
    }
}

class Test5{
    public static void main(String[] args){
        Thread thread1 = new Thread(){
            public void run(){
                for(int i = 0;i < 20;i++){
                    Singleton5 instance = Singleton5.getInstance();
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
                    Singleton5 instance = Singleton5.getInstance();
                    System.out.println("thread2:" + "i:" + i + "," + instance);
                }
            }
        };

        Thread thread3 = new Thread(){
            public void run(){
                for(int i = 0;i < 20;i++){
                    Singleton5 instance = Singleton5.getInstance();
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
