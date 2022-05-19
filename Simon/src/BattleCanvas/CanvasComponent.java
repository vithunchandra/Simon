/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BattleCanvas;

import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author LVOILA
 */
public abstract class CanvasComponent {
    protected CanvasMouseListener mouse;
    protected int x,y,width,height;
    protected final Font DEFAULT_FONT;
    protected final int default_font_size;
    
    public CanvasComponent(int x,int y,int width,int height,CanvasMouseListener mouse) {
        default_font_size = 20;
        this.DEFAULT_FONT = new Font("Courier New",Font.PLAIN,default_font_size) ;
        this.mouse = mouse;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }
    
    public abstract void draw(Graphics g);
    
    public Boolean clicked() {
        if(mouse.getX() >= x && mouse.getY() >= y && mouse.getX() <= (x + width) && mouse.getY() <= (y + height)) {
            return mouse.isRelease();
            
        }
        else {
            return false;
        }
    }
}
