package com.zuql.thread.waitingandnotification;

public class Stack {
    private char[] a = new char[5];
    int index;

    public void push(char c) {
        if(index==5) {
            return;
        }
        a[index] = c;
        index++;
    }
    public char pop() {
        if(index==0) {
            return ' ';
        }
        index--;
        char c = a[index];
        return c;
    }

}
