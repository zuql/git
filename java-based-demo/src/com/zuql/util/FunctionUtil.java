package com.zuql.util;

/**
 * Created by 沧澜 on 2018/11/3.
 */
public class FunctionUtil {

    /**
     * 判断是否是润年 是 true  否 false
     * @return
     */
    public static boolean isRunYear(int year){
        boolean f=false;
        //如果能被4整除，并且不能被100整除
        //或者，能被400整除 是闰年
        if((year%4==0 && year%100!=0)||year%400==0) {
            f=true;
        }
        return f;
    }
}
