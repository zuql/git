package com.zuql.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class demo04 {


}
class Resource {
    private String name;
    private int count = 1; // 记录烤鸭的编号
    private boolean flag = false;

    // 创建一个锁对象
    Lock lock = new ReentrantLock();

    // 通过已有的锁获取两组监视器, 一组监视生产者, 一组监视消费者
    Condition producer_con = lock.newCondition();
    Condition consumer_con = lock.newCondition();

    public void set(String name) {
        lock.lock(); //获取锁
        try
        {
            while(flag)
                try{
                    producer_con.await();
                }catch(InterruptedException e){}
            this.name = name + count;
            count++;
            System.out.println(Thread.currentThread().getName()+"..生产者.."+this.name);
            flag = true;
            consumer_con.signal();
        }
        finally {
            lock.unlock(); //释放锁
        }
    }

    public void out() {
        lock.lock(); //获取锁
        try{
            while(!flag){
                try{consumer_con.await();}catch(InterruptedException e){}
            }
            System.out.println(Thread.currentThread().getName()+ "...消费者.."+ this.name);
            flag = false;
            producer_con.signal();
        }finally{
            lock.unlock(); //释放锁
        }
    }
}

class Producer implements Runnable {
    Resource r;
    Producer(Resource r){
        this.r = r;
    }

    public void run(){
        while(true){
            r.set("烤鸭");
        }
    }
}

class Consumer implements Runnable {
    Resource r;
    Consumer(Resource r){
        this.r = r;
    }
    public void run(){
        while(true){
            r.out();
        }
    }
}

class ProducerConsumerDemo {
    public static void main(String[] args){
        // 创建资源
        Resource r = new Resource();

        // 创建任务
        Producer pro = new Producer(r);
        Consumer con = new Consumer(r);

        // 多生产者
        Thread t0 = new Thread(pro);
        Thread t1 = new Thread(pro);

        // 多消费者
        Thread t2 = new Thread(con);
        Thread t3 = new Thread(con);
        Thread t4 = new Thread(con);
        Thread t5 = new Thread(con);

        // 开启线程
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}