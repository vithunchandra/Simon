/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Button;

import Util.MyFrame;
import java.awt.*;
import Util.Component.*;
//import DummyClass.*;
import simon.*;
import Pokemon.*;

public class ChangeInfoPokemonPanel extends ChangePanelButton{
    ClickedDataComponent<Pokemon> clickedData;
    public ChangeInfoPokemonPanel(ClickedDataComponent<Pokemon> clickedData, MyFrame frame, ActionButton button) {
        super(frame, button);
        this.clickedData = clickedData;
    }

    @Override
    public void clicked() {
        if(clickedData.getClickedComponent() != null){
            PokemonInfo pokemonInfo = new PokemonInfo(clickedData.getClickedComponent().getData(), frame, this.oldPanel);
            this.newPanel = pokemonInfo.infoPanel();
            frame.changePanel(newPanel);
        }
    }
    
}
