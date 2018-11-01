package com.ngsky.design.singleton;

import java.io.Serializable;

/**
 * <dl>
 * <dt>Singleton11</dt>
 * <dd>Description:单例与系列化</dd>
 * <dd>CreateDate: 11/2/2018 12:12 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class Singleton11 implements Serializable {
    private static volatile Singleton11 instance;
    private Singleton11(){}
    public static Singleton11 getInstance(){
        if(instance == null){
            synchronized(Singleton11.class){
                if(instance == null){
                    instance = new Singleton11();
                }
            }
        }
        return instance;
    }
    private Object readResolve(){  // 重写 Serializable readResolve 方法
        return instance;
    }
}
