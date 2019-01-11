package com.zuql.collection;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 能被2,3,5 整除成1
 * 2,3,4,5,6,8,9,10,12,15,16,
 * 求第n个丑数
 */
public class demo03 {
    public static void main(String[] args) {
        System.out.print("求第几个丑数：");
        int n = new Scanner(System.in).nextInt();
        long r = f(n);
        System.out.println(r);
    }
    private static long f(int n) {
        /*
         * 10 12
         * -----------------
         * 9 12 15 18
         * -----------------
         * 10 15 20 25 30
         * -----------------
         *
         * 2 3 4 5 6 8
         *
         * 1.从头部移除最小值
         * 2.分别乘2,3,5，放入三个集合
         */
        LinkedList<Long> list2 = new LinkedList<>();
        LinkedList<Long> list3 = new LinkedList<>();
        LinkedList<Long> list5 = new LinkedList<>();
        list2.add(2L);
        list3.add(3L);
        list5.add(5L);
        long r = 0;//用来保存结果
        //从第一个到第n个
        for(int i=1;i<=n;i++) {
            long a = list2.getFirst();
            long b = list3.getFirst();
            long c = list5.getFirst();
            r = Math.min(a, Math.min(b, c));
            if(r == a) list2.removeFirst();
            if(r == b) list3.removeFirst();
            if(r == c) list5.removeFirst();
            //
            list2.add(r*2);
            list3.add(r*3);
            list5.add(r*5);
        }
        return r;
    }

}
