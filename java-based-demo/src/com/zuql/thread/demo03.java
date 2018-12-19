package com.zuql.thread;

public class demo03 {
    public static void main(String[] args) {
        R1 r1 = new R1();
        Thread t = new Thread(r1);
        // r1.run(), r1.add()
        t.start();

        //main线程
        while(true) {
            int i = r1.get();
            if(i%2 == 1) {
                System.out.println(i);
                System.exit(0);//关闭虚拟机
            }
        }
    }

    static class R1 implements Runnable {
        static int i;
        public synchronized void add() {
            i++;
            i++;
        }
        public synchronized int get() {
            return i;
        }
        @Override
        public void run() {
            while(true) {
                add();
            }
        }
    }

}
