/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BattleCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author LVOILA
 */
public class CanvasMouseListener implements MouseListener{
    
    
    private Boolean pressed, release, entered, exited;
    private Integer x,y;

    public CanvasMouseListener() {
        pressed = false;
        release = false;
        entered = false;
        exited = true;
        x = -1;
        y = -1;
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) { 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.pressed = true;
        getPoint(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.pressed = false;
        this.release = true;
        getPoint(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        entered = true;
        exited = false;
        getPoint(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        entered = false;
        exited = true;
        getPoint(e);
    }

    public void disableRelease() {
        this.release = false;
        this.x = -1;
        this.y = -1;
    }

    public Boolean isPressed() {
        return pressed;
    }

    public Boolean isRelease() {
        return release;
    }
    
    public Boolean isHovered() {
        return entered;
    }
    
    public void getPoint(MouseEvent event){
        x = event.getX();
        y = event.getY();
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
    
    
}
