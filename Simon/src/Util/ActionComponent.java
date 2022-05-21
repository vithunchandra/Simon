/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ActionComponent extends JComponent{
    Image imageBackground;
    
    public ActionComponent(Dimension size, LayoutManager layout, Image imageBackground){
        this.setPreferredSize(size);
        this.setLayout(layout);
        this.setBackground(Color.LIGHT_GRAY);
        if(imageBackground != null){
            this.imageBackground = imageBackground.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        }
    }
    
    public ActionComponent(Dimension size, int x, int y, LayoutManager layout, Image imageBackground){
        this.setBounds(x, y, size.width, size.height);
        this.setLayout(layout);
        this.setBackground(Color.LIGHT_GRAY);
        if(imageBackground != null){
            this.imageBackground = imageBackground.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g = (Graphics2D) g;
        if(imageBackground != null){
            g.drawImage(imageBackground, 0, 0, this.getWidth(), this.getHeight(), this);
        }else{
            g.setColor(this.getBackground());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }

    public Image getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(Image imageBackground) {
        if(imageBackground != null){
            this.imageBackground = imageBackground.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        }
    }
}
