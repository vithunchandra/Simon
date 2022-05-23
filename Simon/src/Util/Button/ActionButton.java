/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Button;

import Util.Component.ActionComponent;
import Util.ImageLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActionButton extends ActionComponent{
    private boolean clicked, pressed, release, entered, exited;
    private Image defaultState, pressedState;

    public ActionButton(Dimension size, LayoutManager layout, Image imageBackground) {
        super(size, layout, imageBackground);
        clicked = false; pressed = false; release = false; entered = false; exited = false;
        try {
            defaultState = ImageLoader.loadImage("src\\Material\\Image\\btn1.png");
            pressedState = ImageLoader.loadImage("src\\Material\\Image\\btn1_press.png");
        } catch (IOException ex) {
            Logger.getLogger(ActionButton.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.setImageBackground(defaultState);
    }

    public ActionButton(Dimension size, int x, int y, LayoutManager layout, Image imageBackground) {
        super(size, x, y, layout, imageBackground);
        clicked = false; pressed = false; release = false; entered = false; exited = false;
        try {
            defaultState = ImageLoader.loadImage("src\\Material\\Image\\btn1.png");
            pressedState = ImageLoader.loadImage("src\\Material\\Image\\btn1_press.png");
        } catch (IOException ex) {
            Logger.getLogger(ActionButton.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.setImageBackground(defaultState);
    }
    
    public void checkClicked(int x, int y){
        if(x >= 0 && x < this.getWidth() && y > 0 && y < this.getHeight() && pressed){
            clicked = true;
        }
        pressed = false;
    }

    public Image getDefaultState() {
        return defaultState;
    }

    public void setDefaultState(Image defaultState) {
        this.defaultState = defaultState;
    }

    public Image getPressedState() {
        return pressedState;
    }

    public void setPressedState(Image pressedState) {
        this.pressedState = pressedState;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean entered) {
        this.entered = entered;
    }

    public boolean isExited() {
        return exited;
    }

    public void setExited(boolean exited) {
        this.exited = exited;
    }
}
