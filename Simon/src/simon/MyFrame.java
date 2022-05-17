/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyFrame extends JFrame{
    
    MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(1000, 700));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Simon");
    }
    
    public void changePanel(MyPanel newPanel){
        this.getContentPane().removeAll();
        this.add(newPanel);
        this.revalidate();
        this.repaint();
    }
}
