/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawVerticalText extends JComponent{
    private String text;
    private Font font;
    private Dimension size, maxSize;
    
    public DrawVerticalText(String text, Font font){
        this.text = text;
        this.font = font;
        Canvas c = new Canvas();
        FontMetrics metrics = c.getFontMetrics(font);
        int height, width;
        
        width = metrics.getHeight();
        height = metrics.stringWidth(text) / text.length();
        
        size = new Dimension(width, height);
    
        this.setPreferredSize(size);
        this.setSize(size);
        this.setBounds(0, 0, width, height);
        this.setOpaque(false);
        this.maxSize = null;
    }
    
    public DrawVerticalText(String text, Font font, Dimension maxSize){
        this.text = text;
        this.font = font;
        Canvas c = new Canvas();
        FontMetrics metrics = c.getFontMetrics(font);
        int height, width;
        
        width = metrics.getHeight();
        height = metrics.stringWidth(text) / text.length();
        
        this.maxSize = maxSize;
        if(width > maxSize.width){
            width = maxSize.width;
        }
        if(height > maxSize.height){
            height = maxSize.height;
        }
        
        size = new Dimension(width, height);
        
        this.setPreferredSize(size);
        this.setSize(size);
        this.setBounds(0, 0, width, height);
        this.setOpaque(false);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        g = (Graphics2D) g;
        g.setFont(font);
        g.drawString(text, size.height - 10, 0);
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        Canvas c = new Canvas();
        FontMetrics metrics = c.getFontMetrics(font);
        int width = metrics.getHeight();
        int height = metrics.stringWidth(text);
        
        if(width > maxSize.width){
            width = maxSize.width;
        }
        if(height > maxSize.height){
            height = maxSize.height;
        }
        
        size = new Dimension(width, height);
    
        this.setPreferredSize(size);
        this.setSize(size);
        this.setBounds(0, 0, width, height);
    }
    
    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Dimension getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Dimension maxSize) {
        this.maxSize = maxSize;
    }
}
