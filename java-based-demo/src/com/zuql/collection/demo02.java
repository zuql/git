package com.zuql.collection;

import java.util.Iterator;
import java.util.LinkedList;

public class demo02 {
    public static void main(String[] args) {
        System.out.println("正在添加数据...");
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<1000000;i++) {
            list.add(1);
        }
        System.out.println("数据准备完毕！");
        System.out.println("--下标遍历------------");
        f1(list);
        System.out.println("--迭代遍历------------");
        f2(list);
    }
    private static void f1(LinkedList<Integer> list) {
        long t = System.currentTimeMillis();
        for(int i=0;i<list.size();i++) {
            list.get(i);
        }
        t = System.currentTimeMillis()-t;
        System.out.println(t);
    }
    private static void f2(LinkedList<Integer> list) {
        long t = System.currentTimeMillis();
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()) {
            it.next();
        }
        t = System.currentTimeMillis()-t;
        System.out.println(t);
    }

}
