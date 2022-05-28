/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BattleCanvas;

import Pokemon.ImagePath;
import Util.ImageLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LVOILA
 */
public class CanvasButton extends CanvasComponent {
    
    private String text;
    private int fontSize;
    private boolean rendered;
    private Image btn,btnPress;
    
    public CanvasButton(String text,int x,int y,int width,int height,CanvasMouseListener mouse) {
        super(x, y, width, height, mouse);
        try {
            this.text = text;
            this.fontSize = 20;
            
            btn = ImageLoader.loadImage(ImagePath.BTN1);
            btnPress = ImageLoader.loadImage(ImagePath.BTN1_PRESS);
        } catch (IOException ex) {
            Logger.getLogger(CanvasButton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        rendered = true;
        if(mouse.getX() >= x && mouse.getY() >= y && mouse.getX() <= (x + width) && mouse.getY() <= (y + height)) {
            if(mouse.isPressed()) {
                g.drawImage(this.btnPress,x, y, width, height,null);
            }
//            else if(this.hovered()) {
//                g.setColor(Color.green);
//            }
            else {
                g.drawImage(this.btn,x, y, width, height,null);
            }   
        }
        else {
            g.drawImage(this.btn,x, y, width, height,null);
        }
        
        
        g.setFont(super.DEFAULT_FONT);
        g.setColor(Color.WHITE);
        int midHeight = (this.height - this.getPixelSize())/2;
        int midWidth = (int)(this.width - this.text.length()*this.fontSize/1.5 )/2;
        //System.out.println(midWidth);
        g.drawString(text, x + midWidth, y + this.fontSize + midHeight);
    }

    public boolean isRendered() {
        return rendered;
    }
   
    public void setRenderedToFalse() {
        this.rendered = false;
    }

    public String getText() {
        return text;
    }
    
    public String getTextNoNum() {
        String[] temp = this.text.split("\\(");
        return temp[0];
    }
    
    
}
