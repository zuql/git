package com.zuql.collection;

import java.util.LinkedList;

/**
 * 手写双向链表
 */
public class demo04 {
    public static void main(String[] args) {
        MyList m=new MyList();
        m.add(1);
        m.add(2);
        m.add(3);
        m.add(4);
        System.out.println(m.get(0));
        System.out.println(m.get(1));
        System.out.println(m.get(2));
        System.out.println(m.get(3));

    }
    static class MyList {
        Node head;
        int size;
        Node tail;
        public void add(Object obj) {
            Node node=new Node(obj);
            if (head==null){
                head=node;
                node.setPrev(head);
                node.setNext(head);
            }else {
                Node prev=  head.getPrev();
                node.setPrev(prev);
                node.setNext(head);
                head.setPrev(node);
            }
            tail=node;
            ++size;
        }

        public Object get(int index) {
            if (index < (size >> 1)) {
                Node x = head;
                for (int i = 0; i < index; i++)
                    x = x.next;
                return x.value;
            } else {
                Node x = tail;
                for (int i = size - 1; i > index; i--)
                    x = x.prev;
                return x.value;
            }
        }

        public int size() {
            return size;
        }

        class Node {
            Object value;//封装的数据
            Node prev;//引用前一个节点
            Node next;//引用下一个节点
            public Node(Object obj){
                value=obj;
            }
            public Object getValue() {
                return value;
            }

            public void setValue(Object value) {
                this.value = value;
            }

            public Node getPrev() {
                return prev;
            }

            public void setPrev(Node prev) {
                this.prev = prev;
            }

            public Node getNext() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }
        }

    }
}