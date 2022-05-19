/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.*;

/**
 *
 * @author asus
 */
public class CanvasText extends Canvas{
    private String text;
    private Font font;
    private Dimension size;
    
    public CanvasText(String text, Font font){
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
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = (Graphics2D) g;
        g.setFont(font);
        g.drawString(text, 0, size.height - 8);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
