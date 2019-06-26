package com.lymboy.jvm.gc;

import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * @author sairo
 * @version 1.0
 * @date 2019-06-23
 */
public class GC02 {

    @Test
    public void testMaxMemory() throws IOException {
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024 + "MB");
        System.out.println("处理器数量： " + Runtime.getRuntime().availableProcessors());
        System.out.println("空闲内存： " + Runtime.getRuntime().freeMemory()/1024/1024 + "MB");
    }
}
