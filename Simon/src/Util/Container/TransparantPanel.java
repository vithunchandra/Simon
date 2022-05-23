/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Container;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.*;

public class TransparantPanel extends JPanel{
    int x, y;
    
    public TransparantPanel(int substractX, int substractY){
        this.setLayout(null);
        this.setOpaque(false);
        this.setBackground(new Color(0, 0, 0, 90));
        x = substractX; y = substractY;
    }
    
    public TransparantPanel(int substractX, int substractY, Color color){
        this.setLayout(null);
        this.setOpaque(false);
        this.setBackground(color);
        x = substractX; y = substractY;
    }

    public void setSubstract(int x, int y){
        this.x = x; this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g = (Graphics2D) g;
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth() - x, this.getHeight() - y);
    }
}
