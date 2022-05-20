/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import java.awt.event.*;
import java.util.ArrayList;
import Util.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class TradeData implements MouseListener{
    ArrayList<ComponentData<Image, MyContainer>> party;
    ArrayList<ComponentData<Image, TransparantPanel>> pokemonList;
    ComponentData<Image, MyContainer> clickedParty = null;
    
    public TradeData(ArrayList<ComponentData<Image, MyContainer>> comp1, ArrayList<ComponentData<Image, TransparantPanel>> comp2){
        this.party = comp1;
        this.pokemonList = comp2;
        
        for(int i=0; i<party.size(); i++){
            party.get(i).getComponent().addMouseListener(this);
        }
        
        for(int i=0; i<pokemonList.size(); i++){
            pokemonList.get(i).getComponent().addMouseListener(this);
        }
    }
    
    public void unclicked(MyContainer object){
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
                party.get(i).getComponent().setBorder(border.outlineBorder());
                unclicked(party.get(i).getComponent());
            }
        }
        
        for(int i=0; i<pokemonList.size(); i++){
            if(e.getSource() == pokemonList.get(i).getComponent()){
                if(clickedParty != null){
                    clickedParty.setData(pokemonList.get(i).getData());
                    for(int j=0; j<clickedParty.getComponent().getComponentCount(); j++){
                        if(clickedParty.getComponent().getComponent(j) instanceof CanvasImage){
                            System.out.println("Test");
                            CanvasImage image = (CanvasImage) clickedParty.getComponent().getComponent(j);
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
                party.get(i).getComponent().setBackground(Color.GREEN);
            }
        }
        
        for(int i=0; i<pokemonList.size(); i++){
            if(e.getSource() == pokemonList.get(i).getComponent()){
                pokemonList.get(i).getComponent().setBackground(new Color(0, 255, 0, 90));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for(int i=0; i<party.size(); i++){
            if(e.getSource() == party.get(i).getComponent()){
                party.get(i).getComponent().setBackground(Color.LIGHT_GRAY);
            }
        }
        
        for(int i=0; i<pokemonList.size(); i++){
            if(e.getSource() == pokemonList.get(i).getComponent()){
                pokemonList.get(i).getComponent().setBackground(new Color(255, 255, 255, 90));
            }
        }
    }
    
}
