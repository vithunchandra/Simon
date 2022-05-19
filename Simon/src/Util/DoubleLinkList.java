/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.util.Iterator;

/**
 *
 * @author Hp-PC
 */
public class DoubleLinkList<E extends Comparable<E>> {
    private Node<E> head;
    private Node<E> tail;

    public DoubleLinkList() {
        this.head = null;
        this.tail = null;
    }
    
    
    public void addFirst(E newVal) {
        Node<E> temp = new Node<>(newVal);
        if(head == null && tail == null) {
            head = temp;
            tail = temp;
        }
        else {
            head.left = temp;
            temp.right = head;
            head = temp;
        }
    }
    
    public void addLast(E newVal) {
        Node<E> temp = new Node<>(newVal);
        if(head == null && tail == null) {
            head = temp;
            tail = temp;
        }
        else {
            tail.right = temp;
            temp.left = tail;
            tail = temp;
        }
    }
    
    public void add(E newVal) {
        addLast(newVal);
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public boolean hasNext() {
        return head.right != null;
    }
    
    public boolean hasBefore() {
        return head.left != null;
    }
    
    public E pop() {
        E temp = head.value;
        this.removeLeft();  
        return temp;
    }
    
    public void sort() {
        Node<E> temp1 = this.head;
        while(temp1 != null) {
            Node<E> temp2 = temp1.right;
            while(temp2 != null) {
                System.out.println(temp1.value + " " + temp2.value);
                if(temp1.value.compareTo(temp2.value) > 0) {
                    E tempVar = temp1.value;
                    temp1.value = temp2.value;
                    temp2.value = tempVar;
                } 
                temp2 = temp2.right;
            }
            temp1 = temp1.right;
        }
    }
    
    public int size() {
        Node<E> temp = head;
        int ct = 0;
        while(temp != null) {
            ct = ct + 1;
            temp = temp.right;
        }
        return ct;
    }
    
    public void insertAt(int idx,E newVal) {
        Node<E> newNode = new Node<>(newVal);
        if(idx == 0) {
            this.addFirst(newVal);
        }
        else {
            Node<E> temp = head;
            int ct = 0;
            while(temp != null) {
                if(ct == idx) {
                    Node toReplace = temp;
                    Node simpanKiri= temp.left;

                    newNode.left = simpanKiri;
                    newNode.right = toReplace;
                    simpanKiri.right = newNode;
                    toReplace.left = newNode;

                }
                ct = ct + 1;
                temp = temp.right;
            }
        }
    }
    
    public void removeLeft() {
        if(head.right == null) {
            head = null;
            tail = null;
        }
        else {    
            head = head.right;
            head.left = null;
        }
    }
    
    public void removeRight() {
        if(tail.left == null) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.left;
            tail.right = null;
        }
    }
    
    public void removeAt(int idx) {
        if(idx == 0) {
            this.removeLeft();
        }
        else {
            Node<E> temp = head;
            int ct = 0;
            while(temp != null) {
                if(ct == idx) {
                    try {
                        temp.left.right = temp.right;
                        temp.right.left = temp.left;
                    } catch (Exception e) {
                        this.removeRight();
                    }
                }
                ct = ct + 1;
                temp = temp.right;
            }
        }
    }
    
    public E get(int idx) {
        Node<E> temp = head;
        int ct = 0;
        while(temp != null) {
            if(ct == idx) {
                return temp.value;
            }
            temp = temp.right;
            ct = ct + 1;
        }
        return null;
    }
    
    public void set(int idx,E newVal) {
        Node<E> temp = this.head;
        for(int i = 0;i < idx;i++) {
            temp = temp.right;
        }
        temp.value = newVal;
    }
    
    public void showFromLeft() {
        Node<E> temp = head;
        while(temp != null) {
            System.out.println(temp.value.toString());
            temp = temp.right;
        }
        
    }
    
    public void showFromRight() {
        Node<E> temp = tail;
        while(temp != null) {
            System.out.println(temp.value.toString());
            temp = temp.left;
        }
        
    }


}
