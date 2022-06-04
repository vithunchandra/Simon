/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Item.Item;
import Util.*;
import Util.Button.*;
import Util.Component.*;
import Util.Container.*;
import Util.Text.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class ShopAction extends MouseAdapter{
    private ArrayList<ComponentData<Item, ActionComponent>> component;
    private ComponentData<Item, ActionComponent> clickedComponent;
    private ActionComponent detailPanel, buyContainer;
    private BuyAction buyAction;

    public ShopAction() {
        component = new ArrayList<>();
        clickedComponent = null;
        detailPanel = null; buyContainer = null;
        buyAction = null;
    }
    
    public void click(){
        detailPanel.removeAll();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        
        Item itemData = clickedComponent.getData();
        DrawText itemName = new DrawText(itemData.getName(), new Font(Font.SANS_SERIF, Font.BOLD, 32));
        int height = itemName.getHeight();
        DrawImage itemImage = new DrawImage(itemData.getItemImage(), new Dimension(height, height));
        ActionComponent itemNameContainer = new ActionComponent(new Dimension((itemName.getWidth() + height + 10), height), new FlowLayout(FlowLayout.CENTER, 10, 0), null);
        DynamicText description = new DynamicText(itemData.getDescription(), new Font(Font.SANS_SERIF, Font.PLAIN, 20), new Dimension(detailPanel.getWidth() - 30, detailPanel.getHeight() - itemNameContainer.getHeight() - 20));
        ActionComponent descriptionContainer = new ActionComponent(new Dimension(detailPanel.getWidth(), detailPanel.getHeight() - itemNameContainer.getHeight()), new FlowLayout(FlowLayout.CENTER, 10, 10), null);
        descriptionContainer.setBackground(new Color(0, 0, 0, 0));
        descriptionContainer.add(description);
        
        itemNameContainer.add(itemImage);
        itemNameContainer.add(itemName);
        itemNameContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        detailPanel.add(itemNameContainer);
        detailPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        detailPanel.add(descriptionContainer);
        
        SetBorder border = new SetBorder(new Color(30, 30, 30, 100));
        detailPanel.setBorder(border.niceFrame());
        
        detailPanel.setVisible(true);
        detailPanel.revalidate();
        detailPanel.repaint();
        
        buyContainer.setVisible(true);
        buyAction.setAmount(1);
        buyAction.setTotal(itemData.getPrice());
        buyAction.setItemData(itemData);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        ActionComponent comp = search(e);
        if(comp != null){
            if(e.getX() >= 0 && e.getX() <= comp.getWidth() && e.getY() > 0 && e.getY() <= comp.getHeight()){
                clickedComponent = search(comp);
                unclicked();
                click();
            }else{
                if(clickedComponent != null){
                    if(comp != clickedComponent.getComponent()){
                        comp.setBorder(null);
                    }
                }else{
                    comp.setBorder(null);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        ActionComponent comp = search(e);
        if(comp != null){
            SetBorder border = new SetBorder(Color.BLUE);
            comp.setBorder(null);
            comp.setBorder(border.outlineBorder());
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        ActionComponent comp = search(e);
        if(comp != null){
            if(clickedComponent != null){
                if(comp != clickedComponent.getComponent()){
                    comp.setBorder(null);
                }
            }else{
                comp.setBorder(null);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        ActionComponent comp = search(e);
        boolean flag = false;
        if(comp != null){
            if(clickedComponent != null){
                if(comp != clickedComponent.getComponent()){
                    flag = true;
                }
            }else{
                flag = true;
            }
        }
        
        if(flag){
            SetBorder border = new SetBorder(Color.RED);
            comp.setBorder(null);
            comp.setBorder(border.niceFrame());
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
    
    public ComponentData search(ActionComponent detailPanel){
        ComponentData temp = null;
        for(int i = 0; i<component.size(); i++){
            if(component.get(i).getComponent() == detailPanel){
                temp = component.get(i);
                break;
            }
        }
        return temp;
    }

    public void setBuyAction(BuyAction buyAction) {
        this.buyAction = buyAction;
    }

    public void addBuyContainer(ActionComponent buyContainer) {
        this.buyContainer = buyContainer;
    }
    
    public void addDetailPanel(ActionComponent detailPanel){
        this.detailPanel = detailPanel;
    }
    
    public void addComponent(ComponentData<Item, ActionComponent> component){
        component.getComponent().addMouseListener(this);
        this.component.add(component);
    }

    public ArrayList<ComponentData<Item, ActionComponent>> getComponent() {
        return component;
    }

    public void setComponent(ArrayList<ComponentData<Item, ActionComponent>> component) {
        this.component = component;
    }

    public ComponentData<Item, ActionComponent> getClickedComponent() {
        return clickedComponent;
    }

    public void setClickedComponent(ComponentData clickedComponent) {
        this.clickedComponent = clickedComponent;
    }
}
