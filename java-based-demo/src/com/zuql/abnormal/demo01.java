package com.zuql.abnormal;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class demo01 {
    public static void main(String[] args) {
        while(true) {
            try {
                f();
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("输入两个！");
            } catch (ArithmeticException e) {
                System.out.println("不能除0");
            } catch (Exception e) {
                System.out.println("出错，请重试");
            } finally {
                System.out.println("----------------\n\n");
            }
        }

    }


    private static void f() {
        System.out.println("输入逗号隔开的两个整数：");
        String s = new Scanner(System.in).nextLine();
        // "34463463,4"
        String[] a = s.split(",");
        int n1 = Integer.parseInt(a[0]);
        int n2 = Integer.parseInt(a[1]);
        int r = n1/n2;
        System.out.println(r);
        /* 3562334,6534
         * 4353
         * abc,def
         * 5634,0
         */
    }

}
class Test2 {
    public static void main(String[] args) {
        try {
            f();
        } catch (ParseException e) {
            System.out.println("日期格式错误");
        } catch (IOException e) {
            System.out.println("无法创建文件");
        }
    }

    private static void f() throws ParseException,IOException{
        System.out.println("生日(yyyy-MM-dd)：");
        String s = new Scanner(System.in).nextLine();
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(s);
        // "d:\567546734223.txt"
        String path = "e:\\abc\\"+d.getTime()+".txt";

        File f = new File(path);
        f.createNewFile();
    }
}
class Test3 {
    public static void main(String[] args) {
        f();
    }

    private static void f() {
        System.out.println("输入两个浮点数：");
        double a = new Scanner(System.in).nextDouble();
        double b = new Scanner(System.in).nextDouble();
        try {
            double r = divide(a, b);
            System.out.println(r);
        } catch (ArithmeticException e) {
            System.out.println(
                    "不能除0，是我们错，轻鞭笞我们吧！");
            //打印完整异常信息
            e.printStackTrace();
        }
    }

    private static double divide(double a, double b) {
        if(b == 0) {
            ArithmeticException e =
                    new ArithmeticException("/ by zero");
            throw e;//把异常对象抛到上面的调用位置
        }
        return a/b;
    }
}
