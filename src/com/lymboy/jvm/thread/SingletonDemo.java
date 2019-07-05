package com.lymboy.jvm.thread;

/**
 * @author sairo
 * @version 1.0
 * @date 2019-07-04
 */
public class SingletonDemo {

    // 禁止指令重排，保证线程安全
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t SingletonDemo创建...");
    }

    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        /**
         * 多线程环境下不能满足单例模式
         * 解决办法：
         * 1. getInstance方法用 synchronized 修饰
         * 2. DCL (Double Check Lock)双端检锁机制
         */
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }).start();
        }
    }
}


