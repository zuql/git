package com.zuql.thread;

import java.util.Arrays;

public class demo01 {
    static char[] a = {'*','*','*','*','*'};
    static char c = '-';

    public static void main(String[] args) {
        //启动两个线程
        startT1();
        startT2();
    }

    private static void startT1() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while(true) {
                    for(int i=0;i<a.length;i++) {
                        a[i] = c;
                    }
                    c = c=='*'?'-':'*';
                }
            }
        };

        t1.start();
    }

    private static void startT2() {
        new Thread() {
            public void run() {
                while(true) {
                    System.out.println(Arrays.toString(a));
                }
            }
        }.start();
    }

}
