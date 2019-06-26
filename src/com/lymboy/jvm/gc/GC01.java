package com.lymboy.jvm.gc;

/**
 * @author sairo
 * @version 1.0
 * @date 2019-06-23
 */
public class GC01 {

    public static void main(String[] args) {
        while (true) {
            int size = 1024 * 1024;
            int[] arr = new int[size];
//            System.gc();
        }
    }
}
