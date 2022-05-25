/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawText extends JComponent{
    private String text;
    private Font font;
    private Dimension size;
    
    public DrawText(String text, Font font){
        this.text = text;
        this.font = font;
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
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = (Graphics2D) g;
        g.setFont(font);
        g.drawString(text, 0, size.height - 10);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        Canvas c = new Canvas();
        FontMetrics metrics = c.getFontMetrics(font);
        int height = metrics.getHeight();
        int width = metrics.stringWidth(text);
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
}
