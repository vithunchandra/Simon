/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

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
import Util.*;
import Util.Container.*;
import Pokemon.*;

public class PokemonInfo {
    Pokemon pokemon;
    MyPanel oldPanel;
    MyFrame frame;

    public PokemonInfo(Pokemon pokemon, MyFrame frame, MyPanel oldPanel) {
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
        MyPanel infoPanel = new MyPanel(infoBackground, new GridBagLayout());
        
        ArrayList<String> text = new ArrayList<>();
        text.add("Pokemon Name : " + pokemon.getNama());
        text.add("Level : " + pokemon.getLvl());
        text.add("Max Hp : " + pokemon.getMaxHp());
        text.add("Attack : " + pokemon.getDamage());
        text.add("Total Skill : " + pokemon.getNumberOfSkill());
        text.add("Element : Grass");
        
        DrawImage pokemonImage = new DrawImage(pokemon.getDefaultFrontImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH), new Dimension(500, 500));
        DrawTextArray pokemonStat = new DrawTextArray(text, new Font(Font.MONOSPACED, Font.BOLD, 25));
        
        Image containerBackground = null;
        try {
            containerBackground = ImageLoader.loadImage("src\\Material\\Image\\dialogue.png");
        } catch (IOException ex) {
            Logger.getLogger(SwitchPokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ActionComponent textContainer = new ActionComponent(new Dimension(pokemonStat.getWidth() + 20, pokemonStat.getHeight() + 20), new FlowLayout(), containerBackground);
        textContainer.add(pokemonStat);
        
        DrawText buttonName = new DrawText("Back", new Font(Font.SERIF, Font.BOLD, 30));
        ActionButton backButton = new ActionButton(new Dimension(buttonName.getWidth() + 30, buttonName.getHeight() + 15), new FlowLayout(), null);
        backButton.add(buttonName);
        new ChangePanelButton(frame, oldPanel, backButton);
        
        gbc = SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.NORTHWEST);
        infoPanel.add(backButton, gbc);
        
        gbc = SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.CENTER);
        gbc.gridwidth = 2;
        infoPanel.add(pokemonImage, gbc);
        
        gbc = SetGBC.setGbc(gbc, 2, 0, 0, 0, GridBagConstraints.CENTER);
        gbc.gridwidth = 1;
        infoPanel.add(textContainer, gbc);
        
        return infoPanel;
    }
}
