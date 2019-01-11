package com.zuql.collection;

import java.util.Iterator;
import java.util.LinkedList;

public class demo01 {
    public static void main(String[] args) {
        /*
         * <> 泛型
         *    用来限制集合中存放的数据类型
         *    泛型语法，不支持基本类型
         *    集合不允许存放基本类型
         */
        LinkedList<String> list = new LinkedList<>();
        list.add("aaa");
        list.add("ccc");
        list.add("uuu");
        list.add("ppp");
        list.add("qqq");
        list.add("ccc");
        list.add("aaa");
        list.add("aaa");
        System.out.println(list.size());
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.get(list.size()-1));
        System.out.println(list.remove(2));
        System.out.println(list);
        System.out.println(list.remove("aaa"));
        System.out.println(list);
        System.out.println("----------------");
        //双向链表，下标遍历，效率低
        for(int i=0;i<list.size();i++) {
            System.out.println(list.get(i));
        }
        System.out.println("---------------");
        //双向链表，迭代遍历，效率高
        //新建迭代器对象
        Iterator<String> it = list.iterator();
        //当有数据
        while(it.hasNext()) {
            //取下一项数据
            String s = it.next();
            System.out.println(s);
        }
    }


}
