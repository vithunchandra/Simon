/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Text;

import java.awt.*;
import java.awt.*;

/**
 *
 * @author asus
 */
public class ActionText extends DrawText{
    private Font defaultFont, clickedFont;
    private Color defaultColor, clickedColor;

    public ActionText(String text, Font font, Font clickedFont, Color defaultColor, Color clickedColor) {
        super(text, clickedFont);
        this.defaultFont = font;
        this.clickedFont = clickedFont;
        this.setForeground(defaultColor);
        this.defaultColor = defaultColor;
        this.clickedColor = clickedColor;
    }

    public Font getDefaultFont() {
        return defaultFont;
    }

    public void setDefaultFont(Font defaultFont) {
        this.defaultFont = defaultFont;
    }

    public Font getClickedFont() {
        return clickedFont;
    }

    public void setClickedFont(Font clickedFont) {
        this.clickedFont = clickedFont;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }

    public Color getClickedColor() {
        return clickedColor;
    }

    public void setClickedColor(Color clickedColor) {
        this.clickedColor = clickedColor;
    }
    
    public void useDefaultFont(){
        this.setFont(defaultFont);
    }
    
    public void useClickedFont(){
        this.setFont(clickedFont);
    }
    
    public void useDefaultColor(){
        this.setForeground(defaultColor);
    }
    
    public void useClickedColor(){
        this.setForeground(clickedColor);
    }
}
