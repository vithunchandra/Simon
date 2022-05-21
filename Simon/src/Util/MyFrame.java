/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyFrame extends JFrame{
    
    public static int DEFAULT_HEIGHT = 700;
    public static int DEFAULT_WIDTH = 1000;
    public MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Simon");
    }
    
    public void changePanel(JPanel newPanel){
        this.getContentPane().removeAll();
        this.add(newPanel);
        this.revalidate();
        this.repaint();
    }
    
    public void removePanel(){
        this.getContentPane().removeAll();
        this.revalidate();
        this.repaint();
    }
}
