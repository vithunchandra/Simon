/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Button;

import java.awt.event.*;

public abstract class ButtonAction extends MouseAdapter{
    ActionButton button;

    public ButtonAction(ActionButton button) {
        this.button = button;
        this.button.addMouseListener(this);
    }
    
    public abstract void clicked();
    
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(button == e.getSource()){
            button.setPressed(true);
            button.setImageBackground(button.getPressedState());
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
            }else{
                button.setImageBackground(button.getDefaultState());
            }
        }
    }

    public ActionButton getButton() {
        return button;
    }

    public void setButton(ActionButton button) {
        this.button = button;
    }
}
