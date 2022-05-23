/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import Util.Component.ComponentData;
import Util.Component.ActionComponent;
import Util.Container.TransparantPanel;
import java.awt.event.*;
import java.util.ArrayList;
import Util.*;
import java.awt.*;
import javax.swing.JOptionPane;
import javax.swing.border.*;

public class TradeData implements MouseListener{
    ArrayList<ComponentData<Image, ActionComponent>> party;
    ArrayList<ComponentData<Image, TransparantPanel>> pokemonList;
    ComponentData<Image, ActionComponent> clickedParty = null;
    
    public TradeData(ArrayList<ComponentData<Image, ActionComponent>> comp1, ArrayList<ComponentData<Image, TransparantPanel>> comp2){
        this.party = comp1;
        this.pokemonList = comp2;
        
        for(int i=0; i<party.size(); i++){
            party.get(i).getComponent().addMouseListener(this);
        }
        
        for(int i=0; i<pokemonList.size(); i++){
            pokemonList.get(i).getComponent().addMouseListener(this);
        }
    }
    
    public void unclicked(ActionComponent object){
        for(int i=0; i<party.size(); i++){
            if(party.get(i).getComponent() != object){
                party.get(i).getComponent().setBorder(null);
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i=0; i<party.size(); i++){
            if(e.getSource() == party.get(i).getComponent()){
                clickedParty = party.get(i);
                SetBorder border = new SetBorder(Color.GREEN);
                Border temp = border.outlineBorder();
                party.get(i).getComponent().setBorder(border.outlineBorder());
                unclicked(party.get(i).getComponent());
            }
        }
        
        for(int i=0; i<pokemonList.size(); i++){
            if(e.getSource() == pokemonList.get(i).getComponent()){
                if(clickedParty != null){
                    clickedParty.setData(pokemonList.get(i).getData());
                    for(int j=0; j<clickedParty.getComponent().getComponentCount(); j++){
                        if(clickedParty.getComponent().getComponent(j) instanceof DrawImage){
                            System.out.println("Test");
                            DrawImage image = (DrawImage) clickedParty.getComponent().getComponent(j);
                            image.setImage(pokemonList.get(i).getData());
                            image.repaint();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Clicked the pokemon in party first then click pokemon in pokemonList");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for(int i=0; i<party.size(); i++){
            if(e.getSource() == party.get(i).getComponent()){
                SetBorder border = new SetBorder(Color.GREEN);
                party.get(i).getComponent().setBorder(border.niceFrame());
                party.get(i).getComponent().setBackground(Color.GREEN);
            }
        }
        
        for(int i=0; i<pokemonList.size(); i++){
            if(e.getSource() == pokemonList.get(i).getComponent()){
                SetBorder border = new SetBorder(Color.GREEN);
                pokemonList.get(i).getComponent().setBorder(border.niceFrame());
                pokemonList.get(i).getComponent().setBackground(new Color(0, 255, 0, 90));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for(int i=0; i<party.size(); i++){
            if(e.getSource() == party.get(i).getComponent()){
                if(clickedParty != null){
                    if(clickedParty.getComponent() != party.get(i).getComponent()){
                        party.get(i).getComponent().setBorder(null);
                    }
                }else{
                    party.get(i).getComponent().setBorder(null);
                }
            }
        }
        
        for(int i=0; i<pokemonList.size(); i++){
            if(e.getSource() == pokemonList.get(i).getComponent()){
                pokemonList.get(i).getComponent().setBackground(new Color(255, 255, 255, 90));
            }
        }
    }
    
}
