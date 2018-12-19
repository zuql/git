package com.zuql.thread.waitingandnotification;

public class Consumer extends Thread {
    private Stack stack;
    public Consumer(Stack stack) {
        this.stack = stack;
    }
    @Override
    public void run() {
        while(true) {
            synchronized (stack) {
                //如果没有数据
                while(stack.index==0) {
                    //当前线程，在栈对象上等待
                    try {
                        stack.wait();
                    } catch (InterruptedException e) {
                    }
                }
                char c = stack.pop();
                System.out.println("弹出 -->"+c);
                //通知在栈对象上等待的线程
                stack.notifyAll();
            }
        }
    }

}

