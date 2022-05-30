/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Text;

import Util.Component.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import Util.*;

public abstract class ClickedText extends MouseAdapter{
    private ArrayList<ComponentData<String, ActionComponent>> actionText;
    private ComponentData<String, ActionComponent> clickedText;

    public ClickedText(ArrayList<ComponentData<String, ActionComponent>> actionText) {
        this.actionText = actionText;
        for(int i=0; i<actionText.size(); i++){
            actionText.get(i).getComponent().addMouseListener(this);
        }
        this.clickedText = null;
    }
    
    public ClickedText(){
        this.actionText = new ArrayList<>();
    }
    
    public abstract void clicked();

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        ActionComponent temp = search(e).getComponent();
        if(temp != null){
            if(clickedText != null){
                if(temp != clickedText.getComponent()){
                    temp.setBackground(Color.DARK_GRAY);
                }
            }else{
                temp.setBackground(Color.DARK_GRAY);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        ActionComponent temp = search(e).getComponent();
        if(temp != null){
            if(clickedText != null){
                if(temp != clickedText.getComponent()){
                    temp.setBackground(new Color(0, 0, 0, 150));
                }
            }else{
                temp.setBackground(new Color(0, 0, 0, 150));
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        ActionComponent temp = search(e).getComponent();
        if(temp != null){
            if(e.getX() >= 0 && e.getY() >= 0 && e.getX() <= temp.getWidth() && e.getY() <= temp.getHeight()){
                temp.setBorder(null);
                temp.setBackground(null);
                ActionText text = searchText(temp);
                if(text != null){
                    text.useClickedColor();
                    text.useClickedFont();
                }
                clickedText = search(e);
                clicked();
                unclicked();
            }else{
                if(clickedText != null){
                    if(temp != clickedText.getComponent()){
                        temp.setBorder(null);
                        ActionText text = searchText(temp);
                        if(text != null){
                            text.useDefaultColor();
                            text.useDefaultFont();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        boolean status = false;
        ActionComponent temp = search(e).getComponent();
        if(temp != null){
            if(clickedText != null){
                if(temp != clickedText.getComponent()){
                    status = true;
                }
            }else{
                status = true;
            }
        }
        if(status){
            SetBorder border = new SetBorder(Color.WHITE);
            temp.setBorder(border.getNormal());
            ActionText text = searchText(temp);
            if(text != null){
                text.useClickedColor();
            }
        }
    }
    
    public void unclicked(){
        for(int i=0; i<actionText.size(); i++){
            if(actionText.get(i) != clickedText){
                ActionComponent comp = actionText.get(i).getComponent();
                comp.setBorder(null);
                comp.setBackground(Color.DARK_GRAY);
                ActionText tempText = searchText(comp);
                tempText.useDefaultColor();
                tempText.useDefaultFont();
            }
        }
    }
    
    public ActionText searchText(ActionComponent comp){
        for(int i=0; i<comp.getComponentCount(); i++){
            if(comp.getComponent(i) instanceof ActionText){
                return (ActionText) comp.getComponent(i);
            }
        }
        return null;
    }
    
    public ComponentData<String, ActionComponent> search(MouseEvent e){
        for(int i=0; i<actionText.size(); i++){
            if(e.getSource() == actionText.get(i).getComponent()){
                return actionText.get(i);
            }
        }
        return null;
    }
    
    public void addActionText(ComponentData<String, ActionComponent> actionText){
        actionText.getComponent().addMouseListener(this);
        this.actionText.add(actionText);
    }

    public ArrayList<ComponentData<String, ActionComponent>> getActionText() {
        return actionText;
    }

    public void setActionText(ArrayList<ComponentData<String, ActionComponent>> actionText) {
        this.actionText = actionText;
    }

    public ComponentData<String, ActionComponent> getClickedText() {
        return clickedText;
    }

    public void setClickedText(ComponentData<String, ActionComponent> clickedText) {
        this.clickedText = clickedText;
    }
}
