/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Button;

import Item.Item;
import Pokemon.*;
import Util.Component.*;
import Util.Text.*;
import javax.swing.*;
import simon.Player;
import java.util.ArrayList;

public class PokemonCenterAction extends ButtonAction{
    ArrayList<JProgressBar> hpBar;

    public PokemonCenterAction(ActionButton button) {
        super(button);
        hpBar = new ArrayList<>();
    }
    
    public PokemonCenterAction(){
        hpBar = new ArrayList<>();
    }
    
    @Override
    public void clicked() {
        ArrayList<Pokemon> party = Player.pokemonInParty;
        for(int i=0; i<party.size(); i++){
            Pokemon pokemonData = party.get(i);
            pokemonData.healed(pokemonData.getMaxHp());
            double floatValue = (pokemonData.getHp() / pokemonData.getMaxHp());
            int percent = (int) floatValue * 100;
            JProgressBar temp = hpBar.get(i);
            temp.setValue(party.get(i).getHp());
            temp.setString(percent + "%");
        }
    }

    public void addHpBar(JProgressBar hpBar){
        this.hpBar.add(hpBar);
    }
}
