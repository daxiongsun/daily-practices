package com.ngsky.design.singleton;

/**
 * <dl>
 * <dt>Singleton7</dt>
 * <dd>Description:线程安全 懒汉式单例模式</dd>
 * <dd>CreateDate: 11/1/2018 11:31 PM</dd>
 * </dl>
 * 双重校验 线程安全 效率高
 * @author daxiong
 */
public class Singleton7 {
    private volatile static Singleton7 instance;
    private Singleton7(){}
    public static Singleton7 getInstance(){
        if(instance == null){  // 进入时首先判空，验证是否有存在的实例
            synchronized(Singleton7.class){  // 在jvm中通过对字节码加锁，字节码只有一份，如果当前字节码被load 到内存中实例，会被加锁，其它线程将不再有机会进行实例化操作
                if(instance == null){  // jvm 层面考虑
                    instance = new Singleton7();  // 1.分配内存空间 2.初始化对象 3.将实例指向内存空间
                }
            }
        }
        return instance;
    }
}
