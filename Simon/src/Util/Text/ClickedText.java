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

    public ClickedText(ArrayList<ComponentData<String, ActionComponent>> actionText, ComponentData<String, ActionComponent> firstHighlight) {
        this.actionText = actionText;
        for(int i=0; i<actionText.size(); i++){
            actionText.get(i).getComponent().addMouseListener(this);
        }
        clickedText = firstHighlight;
        ActionComponent comp = firstHighlight.getComponent();
        comp.setBorder(null);
        comp.setBackground(null);
        ActionText text = searchText(comp);
        if(text != null){
            text.useClickedColor();
            text.useClickedFont();
        }
    }
    
    public ClickedText(){
        
    }
    
    public abstract void clicked();

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        ActionComponent temp = search(e).getComponent();
        temp.setBackground(null);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        ActionComponent temp = search(e).getComponent();
        temp.setBackground(new Color(192, 192, 192, 90));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        ActionComponent temp = search(e).getComponent();
        if(e.getX() >= 0 && e.getY() >= 0 && e.getX() <= temp.getWidth() && e.getY() <= temp.getHeight()){
            temp.setBorder(null);
            temp.setBackground(null);
            ActionText text = searchText(temp);
            if(text != null){
                text.useClickedColor();
                text.useClickedFont();
            }
            clickedText = search(e);
            unclicked();
            
        }else{
            temp.setBorder(null);
            ActionText text = searchText(temp);
            if(text != null){
                text.useDefaultColor();
                text.useDefaultFont();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        ActionComponent temp = search(e).getComponent();
        SetBorder border = new SetBorder(Color.WHITE);
        temp.setBorder(border.getNormal());
        ActionText text = searchText(temp);
        if(text != null){
            text.useClickedColor();
        }
    }
    
    public void unclicked(){
        for(int i=0; i<actionText.size(); i++){
            if(actionText.get(i) != clickedText){
                ActionComponent comp = actionText.get(i).getComponent();
                comp.setBorder(null);
                comp.setBackground(null);
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
            if(e.getSource() == actionText.get(i)){
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
