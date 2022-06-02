/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Button;

import java.awt.event.*;

public abstract class ButtonAction extends MouseAdapter{
    public ActionButton button;

    public ButtonAction(ActionButton button) {
        this.button = button;
        this.button.addMouseListener(this);
    }
    
    public ButtonAction(){
        
    }
    
    public abstract void clicked();
    
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(button == e.getSource()){
            button.setPressed(true);
            button.setImageBackground(button.getPressedState());
            button.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if(button == e.getSource()){
            button.checkClicked(e.getX(), e.getY());
            if(button.isClicked()){
                clicked();
                button.setImageBackground(button.getDefaultState());
                button.repaint();
            }else{
                button.setImageBackground(button.getDefaultState());
                button.repaint();
            }
        }
    }

    public ActionButton getButton() {
        return button;
    }

    public void setButton(ActionButton button) {
        button.addMouseListener(this);
        this.button = button;
    }
}
