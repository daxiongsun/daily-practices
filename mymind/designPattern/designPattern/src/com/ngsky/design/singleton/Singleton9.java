package com.ngsky.design.singleton;

/**
 * <dl>
 * <dt>Singleton9</dt>
 * <dd>Description:枚举单例模式</dd>
 * <dd>CreateDate: 11/2/2018 12:06 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
public enum Singleton9 {
    INSTANCE;
    public void Singleton9(){}
}

class Test9{
    public static void main(String[] args){
        Singleton9 instance = Singleton9.INSTANCE;
        System.out.println(instance);
    }
}
