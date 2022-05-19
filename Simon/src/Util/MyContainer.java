/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyContainer extends JPanel{
    
    public MyContainer(int width, int height, LayoutManager layout){
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(layout);
    }
}
