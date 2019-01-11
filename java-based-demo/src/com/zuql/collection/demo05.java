package com.zuql.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class demo05 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(
                list,
                "3","11","2","21","10","32",
                "1","31","20","30","12","22");

        //排序
        Collections.sort(list);
        System.out.println(list);

        Comparator<String> c =
                new Comparator<String>() {
                    /*
                     * o1和o2比较大小
                     * o1大，正数
                     * o1小，负数
                     *  相同，0
                     */
                    @Override
                    public int compare(String o1, String o2) {
                        //o1,o2解析成int
                        int a = Integer.parseInt(o1);
                        int b = Integer.parseInt(o2);
                        return a-b;
                    }
                };

        //比较器对象，交给sort()方法使用
        //sort()方法内部会调用比较器的compare()方法
        Collections.sort(list, c);
        System.out.println( Collections.binarySearch(list,"21"));
        System.out.println(list);

    }

}
