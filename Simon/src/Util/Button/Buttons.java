/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public abstract class Buttons extends MouseAdapter{
    private ArrayList<ActionButton> buttons;
    

    public Buttons(ArrayList<ActionButton> buttons) {
        this.buttons = buttons;
        for(int i=0; i<buttons.size(); i++){
            buttons.get(i).addMouseListener(this);
        }
    }
    
    public Buttons(){
        buttons = new ArrayList<>();
    }
    
    public abstract void clicked(ActionButton button);
    
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        ActionButton button = search(e);
        if(button != null){
            button.setPressed(true);
            if(!button.isClicked()){
                button.setPressed(true);
                button.setImageBackground(button.getPressedState());
            }
            button.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        ActionButton button = search(e);
        if(button != null){
            button.checkClicked(e.getX(), e.getY());
            if(button.isClicked()){
                clicked(button);
            }
            button.setImageBackground(button.getDefaultState());
            button.repaint();
        }
    }
    
    public ActionButton search(MouseEvent e){
        ActionButton temp = null;
        for(int i=0; i<buttons.size(); i++){
            if(buttons.get(i) == e.getSource()){
                temp = buttons.get(i);
                break;
            }
        }
        return temp;
    }
    
    public void AddButton(ActionButton button){
        this.buttons.add(button);
        button.addMouseListener(this);
    }

    public ArrayList<ActionButton> getButton() {
        return buttons;
    }

    public void setButton(ArrayList<ActionButton> buttons) {
        this.buttons = buttons;
    }
}
