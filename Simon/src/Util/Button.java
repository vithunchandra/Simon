/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public abstract class Button implements MouseListener{
    private ArrayList<ActionButton> button;
    

    public Button(ArrayList<ActionButton> button) {
        this.button = button;
    }
    
    public Button(){
        button = new ArrayList<>();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(int i=0; i<button.size(); i++){
            button.get(i).setPressed(true);
            if(!button.get(i).isClicked()){
                button.get(i).setPressed(true);
                button.get(i).setImageBackground(button.get(i).getPressedState());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(int i=0; i<button.size(); i++){
            button.get(i).checkClicked(e.getX(), e.getY());
            if(button.get(i).isClicked()){
                mouseClicked(e);
            }else{
                button.get(i).setImageBackground(button.get(i).getDefaultState());
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    public ArrayList<ActionButton> getButton() {
        return button;
    }

    public void setButton(ArrayList<ActionButton> button) {
        this.button = button;
    }
}
