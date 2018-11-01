/*
package com.ngsky.design.singleton;

*/
/**
 * <dl>
 * <dt>Singleton8</dt>
 * <dd>Description:解决系列化问题的单例模式</dd>
 * <dd>CreateDate: 11/1/2018 11:51 PM</dd>
 * </dl>
 * 存在问题
 * @author daxiong
 *//*

public class Singleton8 {
    private FinalWrapper<Singleton8> helperWrapper = null;
    private Singleton8(){}
    public Singleton8 helper(){
        FinalWrapper<Singleton8> wrapper = helperWrapper;
        if(wrapper == null){
            synchronized(this){
                if(helperWrapper == null){
                    helperWrapper = new FinalWrapper<>(new Singleton8());
                }
                wrapper = helperWrapper;
            }
        }
        return wrapper.value;
    }
    public static Singleton8 getInstance(){
        return helper();
    }
}

class FinalWrapper<T>{
    public final T value;
    public FinalWrapper(T value){
        this.value = value;
    }
}

class Test8{
    public static void main(String[] args){
        Thread thread1 = new Thread(){
            public void run(){
                for(int i = 0;i < 20;i++){
                    Singleton8 instance = new Singleton8();
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
                    Singleton8 instance = new Singleton8();
                    System.out.println("thread2:" + "i:" + i + "," + instance);
                }
            }
        };

        Thread thread3 = new Thread(){
            public void run(){
                for(int i = 0;i < 20;i++){
                    Singleton8 instance = new Singleton8();
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
}*/
