package com.cherry.cherry_anddemo.ui.interview.designpattern;

public class InstanceDesignPattern {

    //第一种 懒汉式，线程不安全（多线程下不能正常工作）
    private static InstanceDesignPattern instance;
    private InstanceDesignPattern() { }
    public static InstanceDesignPattern getInstance(){
        if (instance == null){
            instance = new InstanceDesignPattern();
        }
        return instance;
    }

    //第二种 懒汉式，线程安全
    public static synchronized InstanceDesignPattern getInstance2(){
        if (instance == null){
            instance = new InstanceDesignPattern();
        }
        return instance;
    }

    //第三种 双重检验锁
    public volatile static InstanceDesignPattern instanceDesignPattern;
    public static InstanceDesignPattern getInstance3(){
        if (instance == null){
            synchronized (InstanceDesignPattern.class){
                if (instance == null){
                    instance = new InstanceDesignPattern();
                }
            }
        }
        return instance;
    }

    //第四中 饿汉式  单例会在加载类后一开始就被初始化
    private static final InstanceDesignPattern instance4 = new InstanceDesignPattern();
    public static InstanceDesignPattern getInstance4(){
        return  instance4;
    }

    //第五中 静态内部类
    private static class InstanceHolder{
        private static final InstanceDesignPattern INSTANCE = new InstanceDesignPattern();
    }
    private static InstanceDesignPattern getInstance5(){
        return InstanceHolder.INSTANCE;
    }

    //第六种  枚举
    public enum EasySingleTon{
        INSTANCE;
    }
}
