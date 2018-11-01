package com.ngsky.design.singleton;

/**
 * <dl>
 * <dt>Singleton4</dt>
 * <dd>Description:为了防止类被多次加载造成产生多个实例，提供静态内部类方式和懒汉式以解决该问题</dd>
 * <dd>CreateDate: 11/1/2018 10:32 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class Singleton4 {

    private Singleton4(){}

    private static final class Singleton4Holder{
        private static final Singleton4 instance = new Singleton4();
    }

    public static final Singleton4 getInstance(){
        return Singleton4Holder.instance;
    }
}

class Test4{
    public static void main(String[] args){
        Singleton4 instance1 = Singleton4.getInstance();
        System.out.println(instance1);
        Singleton4 instance2 = Singleton4.getInstance();
        System.out.println(instance2);

        Thread thread1 = new Thread(){
            public void run(){
                for(int i = 0;i < 20;i++){
                    Singleton4 instance = Singleton4.getInstance();
                    System.out.println("thread1:" + "i:" + i + "," + instance);
                }
            }
        };

        Thread thread2 = new Thread(){
            public void run(){
                for(int i = 0;i < 20;i++){
                    Singleton4 instance = Singleton4.getInstance();
                    System.out.println("thread2:" + "i:" + i + "," + instance);
                }
            }
        };

        Thread thread3 = new Thread(){
            public void run(){
                for(int i = 0;i < 20;i++){
                    Singleton4 instance = Singleton4.getInstance();
                    System.out.println("thread3:" + "i:" + i + "," + instance);
                }
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
