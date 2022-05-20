/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BattleCanvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author LVOILA
 */
public class CanvasButton extends CanvasComponent {
    
    private String text;
    private int fontSize;
    
    public CanvasButton(String text,int x,int y,int width,int height,CanvasMouseListener mouse) {
        super(x, y, width, height, mouse);
        this.text = text;
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
    
    @Override
    public void draw(Graphics g) {
        if(mouse.getX() >= x && mouse.getY() >= y && mouse.getX() <= (x + width) && mouse.getY() <= (y + height)) {
            if(mouse.isPressed()) {
                g.setColor(Color.red);
            }
            else if(this.hovered()) {
                g.setColor(Color.green);
            }
            else {
                g.setColor(Color.yellow);
            }   
        }
        else {
            g.setColor(Color.yellow);
        }
        g.fillRect(x, y, width, height);
        
        
        g.setFont(super.DEFAULT_FONT);
        g.setColor(Color.BLACK);
        int midHeight = (this.height - this.getPixelSize())/2;
        int midWidth = (int)(this.width - this.text.length()*this.fontSize/1.5 )/2;
        //System.out.println(midWidth);
        g.drawString(text, x + midWidth, y + this.fontSize + midHeight);
    }
   
    
}
