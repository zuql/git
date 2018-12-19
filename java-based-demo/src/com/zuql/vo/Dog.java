package com.zuql.vo;

/**
 * Created by 沧澜 on 2018/11/3.
 */
public class Dog {
    public String name;
    public int full;
    public int happy;

    public void feed() {
        if(full == 100) {
            System.out.println(name+"吃不下了");
            return;
        }
        System.out.println("给"+name+"喂食");
        full += 10;
        System.out.println("饱食度："+full);
    }
    public void play() {
        if(full==0) {
            System.out.println(name+"饿得玩不动了");
            return;
        }
        System.out.println("陪"+name+"玩耍");
        full -= 10;
        happy += 10;
        System.out.println("快乐度："+happy+"， 饱食度："+full);
    }
    public void punish() {
        System.out.println(
                "打"+name+"的pp，"+name+"哭叫：旺~");

        happy -= 10;
        System.out.println("快乐度："+happy);

    }

}
