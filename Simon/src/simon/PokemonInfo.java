/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import DummyClass.PokemonDummy;
import Util.Component.*;
import Util.MyFrame;
import Util.Container.MyPanel;
import Util.Container.TransparantPanel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import Util.*;
import Util.Button.*;
import java.util.ArrayList;

public class PokemonInfo {
    PokemonDummy pokemon;
    MyPanel oldPanel;
    MyFrame frame;

    public PokemonInfo(PokemonDummy pokemon, MyFrame frame, MyPanel oldPanel) {
        this.pokemon = pokemon;
        this.oldPanel = oldPanel;
        this.frame = frame;
    }
    
    public MyPanel infoPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
                
        Image infoBackground = null;
        try {
            infoBackground = ImageLoader.loadImage("src\\Material\\Image\\infoBackground.png");
        } catch (IOException ex) {
            Logger.getLogger(SwitchPokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        MyPanel infoPanel = new MyPanel(infoBackground, new FlowLayout());
        
        String text = "Pokeom Name : " + pokemon.getName() +
                      "\nLevel : " + pokemon.getLevel() +
                      "\nMax Hp : " + pokemon.getMaxHp() +
                      "\nAttack : " + pokemon.getAttack() +
                      "\nDefense : " + pokemon.getDefense() +
                      "\nElement : " + pokemon.getElement();
        
        DrawImage pokemonImage = new DrawImage(pokemon.getDummyImage(), new Dimension(500, 500));
        DrawText pokemonStat = new DrawText(text, new Font(Font.MONOSPACED, Font.BOLD, 25));
        
        ActionButton backButton = new ActionButton(new Dimension(100, 30), new FlowLayout(), null);
        DrawText buttonName = new DrawText("Back", new Font(Font.SERIF, Font.BOLD, 30));
        backButton.add(buttonName);
        new ChangePanelButton(frame, oldPanel, backButton);
        
        SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.NORTHWEST);
        infoPanel.add(backButton);
        
        SetGBC.setGbc(gbc, 1, 0, 0, 0, GridBagConstraints.CENTER);
        infoPanel.add(pokemonImage);
        
        SetGBC.setGbc(gbc, 1, 1, 0, 0, GridBagConstraints.CENTER);
        infoPanel.add(pokemonStat);
        
        return infoPanel;
    }
}
