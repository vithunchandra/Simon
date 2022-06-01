/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Component;

import Util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class ClickedDataComponent <Data> extends MouseAdapter{
    private ArrayList<ComponentData<Data, ActionComponent>> component;
    private ComponentData<Data, ActionComponent> clickedComponent;

    public ClickedDataComponent() {
        component = new ArrayList<>();
        clickedComponent = null;
    }
    
    public void click(){
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        ActionComponent container = search(e);
        if(container != null){
            if(e.getX() >= 0 && e.getX() <= container.getWidth() && e.getY() > 0 && e.getY() <= container.getHeight()){
                clickedComponent = search(container);
                unclicked();
                click();
            }else{
                if(clickedComponent != null){
                    if(container != clickedComponent.getComponent()){
                        container.setBorder(null);
                    }
                }else{
                    container.setBorder(null);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        ActionComponent container = search(e);
        if(container != null){
            SetBorder border = new SetBorder(Color.BLUE);
            container.setBorder(null);
            container.setBorder(border.outlineBorder());
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        ActionComponent container = search(e);
        if(container != null){
            if(clickedComponent != null){
                if(container != clickedComponent.getComponent()){
                    container.setBorder(null);
                }
            }else{
                container.setBorder(null);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        ActionComponent container = search(e);
        boolean flag = false;
        if(container != null){
            if(clickedComponent != null){
                if(container != clickedComponent.getComponent()){
                    flag = true;
                }
            }else{
                flag = true;
            }
        }
        
        if(flag){
            SetBorder border = new SetBorder(Color.RED);
            container.setBorder(null);
            container.setBorder(border.niceFrame());
        }
    }
    
    public void unclicked(){
        for(int i=0; i<component.size(); i++){
            if(component.get(i) != clickedComponent){
                component.get(i).getComponent().setBorder(null);
            }
        }
    }
    
    public ActionComponent search(MouseEvent e){
        ActionComponent temp = null;
        for(int i = 0; i<component.size(); i++){
            if(component.get(i).getComponent() == e.getSource()){
                temp = component.get(i).getComponent();
                break;
            }
        }
        return temp;
    }
    
    public ComponentData search(ActionComponent container){
        ComponentData temp = null;
        for(int i = 0; i<component.size(); i++){
            if(component.get(i).getComponent() == container){
                temp = component.get(i);
                break;
            }
        }
        return temp;
    }
    
    public void addComponent(ComponentData<Data, ActionComponent> component){
        component.getComponent().addMouseListener(this);
        this.component.add(component);
    }

    public ArrayList<ComponentData<Data, ActionComponent>> getComponent() {
        return component;
    }

    public void setComponent(ArrayList<ComponentData<Data, ActionComponent>> component) {
        this.component = component;
    }

    public ComponentData<Data, ActionComponent> getClickedComponent() {
        return clickedComponent;
    }

    public void setClickedComponent(ComponentData clickedComponent) {
        this.clickedComponent = clickedComponent;
    }
}
