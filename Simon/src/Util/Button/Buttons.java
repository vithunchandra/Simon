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
    
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        for(int i=0; i<buttons.size(); i++){
            buttons.get(i).setPressed(true);
            if(!buttons.get(i).isClicked()){
                buttons.get(i).setPressed(true);
                buttons.get(i).setImageBackground(buttons.get(i).getPressedState());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        for(int i=0; i<buttons.size(); i++){
            buttons.get(i).checkClicked(e.getX(), e.getY());
            if(buttons.get(i).isClicked()){
                mouseClicked(e);
            }else{
                buttons.get(i).setImageBackground(buttons.get(i).getDefaultState());
            }
        }
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
