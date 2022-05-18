/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author LVOILA
 */
public class CanvasButton {
    
    private CanvasMouseListener mouse;
    private int x,y,width,height;
    private String text;
    private int fontSize;
    
    public CanvasButton(int x,int y,int width,int height,CanvasMouseListener mouse) {
        this.mouse = mouse;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.fontSize = 20;
    }
    
    //nanti hapus
    public void setText(String text) {
        this.text = text;
    }
    
    private int fontToPixel(int point) {
        return (int)(point*1.33333333 + 0.5);
    }
    private int getPixelSize() {
        return fontToPixel(this.fontSize);
    }
    
    public void draw(Graphics g) {
        if(mouse.getX() >= x && mouse.getY() >= y && mouse.getX() <= (x + width) && mouse.getY() <= (y + height)) {
            if(mouse.isPressed()) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.yellow);
            }   
        }
        else {
            g.setColor(Color.yellow);
        }
        g.fillRect(x, y, width, height);
        
        
        g.setFont(new Font("Courier New", Font.PLAIN, this.fontSize));
        g.setColor(Color.BLACK);
        int midHeight = (this.height - this.getPixelSize())/2;
        int midWidth = (int)(this.width - this.text.length()*this.fontSize/1.5 )/2;
        //System.out.println(midWidth);
        g.drawString("BACK", x + midWidth, y + this.fontSize + midHeight);
    }
    
    public Boolean released() {
        if(mouse.getX() >= x && mouse.getY() >= y && mouse.getX() <= (x + width) && mouse.getY() <= (y + height)) {
            return mouse.isRelease();
        }
        else {
            return false;
        }
    }
}
