package com.lymboy.jvm.thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author sairo
 * @version 1.0
 * @date 2019-06-26
 */
public class VolatileDemo {

    /**
     * 测试volatile的可见性
     *  当对象的age字段没有添加volatile修饰时
     *      程序会一直循环，其原因是：
     *      各个线程对主内存中共享变量的操作都是各个线程各自拷贝到自己的工作内存操作后再写回到主内存的。
     *      这就可能存在一个线程AAA拷贝主内存中的值到自己的工作内存后修改了共享变量X的值但还未写回到主内存时，
     *      另外一个线程BBB又也拷贝了主内存中同一个共享变量X的值到自己的工作内存并且进行了一些操作，但此时
     *      AAA线程工作内存中已经修改了的值还未更新到主内存中，线程BBB此时并不知道X的最新值，
     *      这种工作内存与主内存同步延迟现象就造成了可见性问题
     *  当age字段添加volatile修饰时：
     *      程序不会一直悬停，正常结束。原因：
     *      当添加volatile修饰后，任何一个线程对主内存中共享变量修改后都会立即将结果刷新到主内存中，同时通知其他线程原来的
     *      已经读取到工作内存中的共享变量的值作废，不能用了，重新去主内存中读取最新值
     */
    @Test
    public void testVisibility() {
        Person person = new Person();
        new Thread(
                () -> {
                    System.out.println("当前线程： " + Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    person.changeAge();
                    System.out.println("线程：" + Thread.currentThread().getName() + "的新值为：" + person.age);
                },
                "AAA"
        ).start();

        System.out.println("Main: " + person.age);
        while (person.age == 0) {

        }

        System.out.println("Main线程: " + Thread.currentThread().getName() + "感知到了age的变化");
    }

    /**
     * volatile原子性测试
     *
     */
    @Test
    public void testAtomic() {

        Person person = new Person();

        for (int i=0; i<20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    person.addOne();
                }
            }, ""+i).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("最终值：" + person.age);
    }
}

class  Person {
    volatile int age = 0;

    void changeAge() {
        this.age = 30;
    }

    void addOne() {
        this.age++;
    }
}
