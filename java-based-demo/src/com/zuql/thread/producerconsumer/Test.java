package com.zuql.thread.producerconsumer;

public class Test {
    public static void main(String[] args) {
        Stack stack = new Stack();
        Producer p = new Producer(stack);
        Consumer c = new Consumer(stack);
        p.start();
        c.start();
    }

}
