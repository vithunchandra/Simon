/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Text;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class DrawTextArray extends JComponent{
    private ArrayList<String> text;
    private int maxWidth, maxHeight, height;
    private Dimension maxSize;
    private Font font;

    public DrawTextArray(ArrayList<String> text, Font font) {
        this.text = text;
        this.font = font;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        
        Canvas c = new Canvas();
        FontMetrics metrics = c.getFontMetrics(this.font);
        for(int i=0; i<text.size(); i++){
            maxHeight += metrics.getHeight();
            if(maxWidth < metrics.stringWidth(text.get(i))){
                maxWidth = metrics.stringWidth(text.get(i));
            }
        }
        height = metrics.getHeight();
        
        Dimension size = new Dimension(maxWidth, maxHeight);
        this.setPreferredSize(size);
        this.setSize(size);
    }
    
    public DrawTextArray(ArrayList<String> text, Font font, Dimension maxSize) {
        this.text = text;
        this.font = font;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        
        Canvas c = new Canvas();
        FontMetrics metrics = c.getFontMetrics(this.font);
        for(int i=0; i<text.size(); i++){
            maxHeight += metrics.getHeight();
            if(maxWidth < metrics.stringWidth(text.get(i))){
                maxWidth = metrics.stringWidth(text.get(i));
            }
        }
        height = metrics.getHeight();
        
        if(maxWidth > maxSize.width){
            maxWidth = maxSize.width;
        }
        if(maxHeight > maxSize.height){
            maxHeight = maxSize.height;
        }
        
        Dimension size = new Dimension(maxWidth, maxHeight);
        this.setPreferredSize(size);
        this.setSize(size);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = (Graphics2D) g;
        g.setFont(this.font);
        g.setColor(Color.BLACK);
        for(int i=0; i<text.size(); i++){
            g.drawString(text.get(i), 0, (height*(i+1) - 10));
        }
    }

    public ArrayList<String> gettext() {
        return text;
    }

    public void settext(ArrayList<String> text) {
        this.text = text;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getTextHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Dimension getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Dimension maxSize) {
        this.maxSize = maxSize;
    }
}
