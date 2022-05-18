/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import java.awt.Graphics;

/**
 *
 * @author LVOILA
 */
public abstract class CanvasComponent {
    protected CanvasMouseListener mouse;
    protected int x,y,width,height;
    protected final String DEFAULT_FONT;
    
    public CanvasComponent(int x,int y,int width,int height,CanvasMouseListener mouse) {
        this.DEFAULT_FONT = "Courier New";
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
