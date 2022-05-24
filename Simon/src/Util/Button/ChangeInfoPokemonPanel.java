/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.Button;

import Util.MyFrame;
import java.awt.*;
import Util.Component.*;
import DummyClass.*;
import simon.*;

public class ChangeInfoPokemonPanel extends ChangePanelButton{
    ClickedDataComponent<Image> clickedData;
    public ChangeInfoPokemonPanel(ClickedDataComponent<Image> clickedData, MyFrame frame, ActionButton button) {
        super(frame, button);
        this.clickedData = clickedData;
    }

    @Override
    public void clicked() {
        if(clickedData.getClickedComponent() != null){
            PokemonInfo pokemonInfo = new PokemonInfo(new PokemonDummy(clickedData.getClickedComponent().getData(), 100, 8, 20, 14, "Test", "Fire"), frame, this.oldPanel);
            this.newPanel = pokemonInfo.infoPanel();
            frame.changePanel(newPanel);
        }
    }
    
}
