package com.zuql.base;

import com.zuql.util.FunctionUtil;
import com.zuql.vo.Dog;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * Created by 沧澜 on 2018/11/3.
 */
public class BaseDemo {
    public static void main(String[] args) {
        demo12();
    }

    /**
     * 电子宠物
     */
    public static void demo12(){
        //提示起个名字
        System.out.print("给宠物起个名字：");
        String n = new Scanner(System.in).nextLine();
        Dog dog = new Dog();
        dog.name = n;
        dog.happy = 50;
        dog.full = 50;
        System.out.println("已经领养了一只新的宠物狗");
        System.out.println("按回车");
        while(true) {
            //等待按回车
            new Scanner(System.in).nextLine();
            int r = new Random().nextInt(3);
            switch(r) {
                case 0: dog.feed(); break;
                case 1: dog.play(); break;

                case 2: dog.punish(); break;
            }
        }

    }
    /**
     * 汉诺塔
     */
    public static void demo11(){
        System.out.print("玩几层汉诺塔：");
        int n = new Scanner(System.in).nextInt();
        f(n, "A", "B", "C");

    }
    private static void f(
            int n,
            String z1,
            String z2,
            String z3) {

        //最简问题
        if(n == 1) {
            System.out.println(z1+" -> "+z3);
            return;
        }
        //简化成上面n-1层，从z1，经z3，到z2
        f(n-1, z1, z3, z2);
        //最底下一层，从z1到z3
        System.out.println(z1+" -> "+z3);
        //在z2上的n-1层，从z2，经z1，到z3
        f(n-1, z2, z1, z3);
    }

    /**
     * 斐波那契数
     */
     public static void demo10(){
         System.out.print("求第几个斐波那契数：");
         int n = new Scanner(System.in).nextInt();
         long r = f(n);
         System.out.println(r);

     }
    private static long g(int n) {
      /*
       * 1 1 2 3 5
       *       a b
       *
       * b=a+b
       * a=b-a
       */
        long a = 1;
        long b = 1;
        //从第3个，求到第n个
        for(int i=3; i<=n; i++) {
            b=a+b;
            a=b-a;
        }
        return b;
    }

    //反例
    private static long f(int n) {
        //最简问题
        if(n==1 || n==2) {
            return 1;
        }
        return f(n-1) + f(n-2);
    }


    /**
     * 冒泡排序
     */
    public static void demo09(){
        //产生一个随机整数n，作为数组长度
        //范围 5 + [0, 6)
        int n = 5 + new Random().nextInt(6);
        //新建n个长度的int[]数组，存到变量a
        int[] a = new int[n];
        //遍历数组a，填入100内随机整数
        for(int i=0;i<a.length;i++) {
            a[i] = new Random().nextInt(100);
        }
        System.out.println("原始数组："+Arrays.toString(a));
        for(int i=0;i<a.length;i++) {
            boolean flag = false;//没有交换
         /*
          * j循环，把较小值向前交换
          * 最终，把最小值推到i位置
          */
            for(int j=a.length-1; j>i; j--) {
                if(a[j] < a[j-1]) {
                    int t = a[j];
                    a[j] = a[j-1];
                    a[j-1] = t;
                    flag = true;//有交换，数据还是混乱的
                }
            }
            //j从后往前把数据捋一遍，
            //位置都正确，没有交换数据
            if(!flag) {
                break; //排序结束
            }
            System.out.println(Arrays.toString(a));
        }

    }
    /**
     * 99乘法表
     */
    public static void demo08(){
        for(int i=1; i<=9; i++) {
            for(int j=1; j<=i; j++) {
               if(j==3 && (i==3 || i==4)) {
                    System.out.print(" ");
                }
                System.out.print(
                        j+"*"+i+"="+(j*i)+" ");
            }
            System.out.println();
        }
    }
    /**
     * 猜数字
     */
    public static void demo07(){
        // 1 + [0, 1000) --> [1, 1001)
        int r = 1 + new Random().nextInt(1000);
        System.out.println(r);
        System.out.println("已经产生了一个[1, 1001)的随机整数");
        System.out.println("请猜这个数是几");
        while(true) {
            System.out.print("猜：");
            int c = new Scanner(System.in).nextInt();
            if(c>r) {
                System.out.println("大");
            } else if(c<r) {
                System.out.println("小");
            } else {
                System.out.println("对");
                break;
            }
        }
    }
    /**
     * 阶乘
     */
    public static void demo06(){
        System.out.print("输入整数求阶乘：");
        int n = new Scanner(System.in).nextInt();
        /*
       * 假设参数n的值是5
       * r = 5
       * i循环
       * 4  r *= i
       * 3  r *= i
       * 2  r *= i
       * 1  r *= i
       */

        long r = n;

        for(int i=n-1; i>=1; i--) {
            r *= i;
        }
        System.out.println(n+"的阶乘："+r);
    }
    /**
     * 鸡兔共35只
     * 有94只脚
     * 鸡和兔各几只
     *
     * 穷举法
     *
     * 鸡j   兔t
     * 0     35
     * 1     34
     * 2     33
     * ...
     * 35    0
     *
     * 鸡兔同笼
     */
    public static void demo05(){
        //鸡0到35，兔35到0
        for(int j=0,t=35; j<=35; j++,t--) {
            //j只鸡和t只兔，脚的数量是否是94只
            if(j*2+t*4 == 94) {
                System.out.println(j+"只鸡， "+t+"只兔");

            }

        }

    }
    /**
     * 输入一个整数，判断是否是质数
     * 2,3,5,7,11,13,17,19,23...
     *
     * 判断质数
     */
    public static void demo04(){
        System.out.print("输入整数：");
        int n = new Scanner(System.in).nextInt();
        //判断n的值是否是质数
        //在2到  1+根号n 范围内找能把n整除的数
        //求1+根号n的值，存到变量max
        double max = 1 + Math.sqrt(n);
        //循环2到<max
        for(int i=2; i<max; i++) {
            //如果n被i整除
            if(n % i == 0) {
                //n不是质数
                System.out.println(n+"不是质数");
                //方法到此结束
                //返回到调用位置继续执行
                return;
            }
        }
        System.out.println(n+"是质数");

    }
    /**
     * 输入一个年号，判断是否是闰年
     *能被4整除，并且不能被100整除
     *能被400整除
     *
     */
    public static void demo03(){
        System.out.print("输入年号：");

        //先获得输入的值
        //再保存到变量y
        int y = new Scanner(System.in).nextInt();
        boolean r=FunctionUtil.isRunYear(y);
        String s=r?"闰年":"平年";
        System.out.println(y+"是："+s);
    }
    /**
     * 牛郎织女星相距 16.4 光年
     * 光速 299792458 米/秒
     * 喜鹊身长 0.46 米
     * 牛郎织女相会，需要多少只喜鹊
     * 项目：牛郎织女
     */
    public static void demo02(){
        //1光年是多少米
        long ly = 299792458L*60*60*24*365;
        //16.4光年是多少米
        double d = 16.4 * ly;
        //喜鹊的数量
        double r = d/0.46;
        //Math.ceil(r)
        //天花板，向上取整
        //取整结果，是double类型 74546.0
        //转型成 long 类型
        long x = (long) Math.ceil(r);
        System.out.println("需要："+x+"只喜鹊");
    }

    /**
     * 1/2*g*t*t
     * 项目：day0202_自由落体距离
     */
    public static void demo01(){
        System.out.println("请输入降落时间（秒）：");
        //获得输入值
        String s=new Scanner(System.in).next();
        Double t=Double.valueOf(s);
        //套用公式求自由落体
        double d=0.5*9.8*t*t;
        System.out.println(d);
    }
}
