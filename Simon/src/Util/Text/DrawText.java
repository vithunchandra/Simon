/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawText extends JComponent{
    private String text;
    private Dimension size, maxSize;
    
    public DrawText(String text, Font font){
        this.text = text;
        this.setFont(font);
        
        Canvas c = new Canvas();
        FontMetrics metrics = c.getFontMetrics(font);
        int height, width;
        
        height = metrics.getHeight();
        width = metrics.stringWidth(text);
        
        size = new Dimension(width, height);
    
        this.setPreferredSize(size);
        this.setSize(size);
        this.setBounds(0, 0, width, height);
        this.setOpaque(false);
        this.maxSize = null;
    }
    
    public DrawText(String text, Font font, Dimension maxSize){
        this.text = text;
        this.setFont(font);
        
        Canvas c = new Canvas();
        FontMetrics metrics = c.getFontMetrics(font);
        int height, width;
        
        height = metrics.getHeight();
        width = metrics.stringWidth(text);
        
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = (Graphics2D) g;
        if(this.getForeground() != null){
            g.setColor(this.getForeground());
        }else{
            g.setColor(Color.BLACK);
        }
        g.setFont(this.getFont());
        g.drawString(text, 0, size.height - 10);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        Canvas c = new Canvas();
        FontMetrics metrics = c.getFontMetrics(this.getFont());
        int height = metrics.getHeight();
        int width = metrics.stringWidth(text);
        
        if(maxSize != null){
            if(width > maxSize.width){
                width = maxSize.width;
            }
            if(height > maxSize.height){
                height = maxSize.height;
            }
        }
        
        size = new Dimension(width, height);
    
        this.setPreferredSize(size);
        this.setSize(size);
        this.setBounds(0, 0, width, height);
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
