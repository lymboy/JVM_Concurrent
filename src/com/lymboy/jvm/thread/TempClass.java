package com.lymboy.jvm.thread;

/**
 * @author sairo
 * @version 1.0
 * @date 2019-06-27
 */
public class TempClass {
    volatile int age = 0;

    void changeAge() {
        this.age = 30;
    }

    void addOne() {
        this.age++;
    }
}
