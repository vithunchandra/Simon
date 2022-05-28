/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Util.Text.DrawText;
import Util.Button.*;
import java.awt.*;
import Util.Component.*;
import Pokemon.*;
import simon.*;

public class TradeDataButton extends ButtonAction{
    ClickedDataComponent<Pokemon> firstData;
    ClickedDataComponent<Pokemon> secondData;

    public TradeDataButton(ClickedDataComponent<Pokemon> firstData, ClickedDataComponent<Pokemon> secondData, ActionButton button) {
        super(button);
        this.firstData = firstData;
        this.secondData = secondData;
    }

    @Override
    public void clicked() {
        if(firstData.getClickedComponent() != null && secondData.getClickedComponent() != null){
            Pokemon temp = firstData.getClickedComponent().getData();
            firstData.getClickedComponent().setData(secondData.getClickedComponent().getData());
            secondData.getClickedComponent().setData(temp);
            
//            Pokemon temp;
//            
//            int firstIndex = -1;
//            for(int i=0; i<Player.pokemonInParty.size(); i++){
//                if(Player.pokemonInParty.get(i) == firstData.getClickedComponent().getData()){
//                    firstIndex = i;
//                }
//            }
//            
//            temp = firstData.getClickedComponent().getData();
//            Player.pokemonInParty.set(firstIndex, secondData.getClickedComponent().getData());
//            secondData.getClickedComponent().setData(temp);
//            
//            firstData.getClickedComponent().setData(secondData.getClickedComponent().getData());
            
            reasset(firstData);
            reasset(secondData);
            
            firstData.setClickedComponent(null);
            secondData.setClickedComponent(null);
        }
        button.setClicked(false);
    }
    
    public void reasset(ClickedDataComponent<Pokemon> data){
        ActionComponent comp = data.getClickedComponent().getComponent();
        Pokemon pokemon = data.getClickedComponent().getData();
        for(int i=0; i<comp.getComponentCount(); i++){
            if(comp.getComponent(i) instanceof DrawImage){
                DrawImage image = (DrawImage) comp.getComponent(i);
                image.setImage(pokemon.getDefaultFrontImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
            }else if(comp.getComponent(i) instanceof DrawText){
                DrawText text = (DrawText) comp.getComponent(i);
                if(text.getName().equals("pokemonName")){
                    text.setText(pokemon.getNama());
                }else if(text.getName().equals("pokemonLevel")){
                    text.setText("Lvl : " + pokemon.getLvl());
                }
            }
        }
        comp.setBorder(null);
    }
    
}
