/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;
/**
 *
 * @author Hp-PC
 */
public class Node<E extends Comparable<E>> {
    public Node right;
    public Node left;
    public E value;

    public Node(E value) {
        this.right = null;
        this.left = null;
        this.value = value;
       
    }
    
}
