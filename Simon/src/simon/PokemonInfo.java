/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import Util.Text.*;
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
    CardsPanel cardsPanel;

    public PokemonInfo(Pokemon pokemon, MyFrame frame, MyPanel oldPanel) {
        this.pokemon = pokemon;
        this.oldPanel = oldPanel;
        this.frame = frame;
    }
    
    public MyPanel infoPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
                
        Image infoBackground = null, containerBackground = null;
        try {
            infoBackground = ImageLoader.loadImage("src\\Material\\Image\\infoBackground.png");
            containerBackground = ImageLoader.loadImage("src\\Material\\Image\\dialogue.png");
        } catch (IOException ex) {
            Logger.getLogger(SwitchPokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MyPanel infoPanel = new MyPanel(infoBackground, new GridBagLayout());
        
        ActionComponent cards = new ActionComponent(new Dimension(400, 500), new CardLayout(), containerBackground);
        cards.add(getStat(), getStat().getName());
        cards.add(getSkill(), getSkill().getName());
        
        cardsPanel = new CardsPanel(cards);
        
        Font font = new Font(Font.SERIF, Font.BOLD, 30);
        
        DrawText buttonName = new DrawText("Back", font);
        ActionButton backButton = new ActionButton(new Dimension(buttonName.getWidth() + 30, buttonName.getHeight() + 15), new FlowLayout(), null);
        backButton.add(buttonName);
        new ChangePanelButton(frame, oldPanel, backButton);
        
        DrawImage pokemonImage = new DrawImage(pokemon.getDefaultFrontImage().getScaledInstance(300, 500, Image.SCALE_SMOOTH), new Dimension(300, 500));
        
        gbc = SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.NORTHWEST);
        infoPanel.add(backButton, gbc);
        
        gbc = SetGBC.setGbc(gbc, 0, 1, 0, 0, GridBagConstraints.NORTHWEST);
        infoPanel.add(infoList(font), gbc);
        
        gbc = SetGBC.setGbc(gbc, 1, 0, 0, 0, GridBagConstraints.WEST);
        gbc.gridheight = 2;
        infoPanel.add(pokemonImage, gbc);
        
        gbc = SetGBC.setGbc(gbc, 2, 0, 0, 0, GridBagConstraints.WEST);
        infoPanel.add(cards, gbc);
        
        return infoPanel;
    }
    
    public ActionComponent infoList(Font font){
        Font newFont = new Font(font.getFontName(), font.getStyle(), font.getSize() + 5);
        Canvas c = new Canvas();
        FontMetrics metrics = c.getFontMetrics(newFont);
        int height = metrics.getHeight(), width = 0;
        
        String[] name = {"Stat", "Skill", "Description"};
        
        ArrayList<ActionText> text = new ArrayList<>();
        
        for(int i=0; i<name.length; i++){
            text.add(new ActionText(name[i], font, newFont, Color.GRAY, Color.WHITE));
            if(metrics.stringWidth(name[i]) > width){
                width = metrics.stringWidth(name[i]);
            }
            text.get(i).useDefaultColor();
            text.get(i).useDefaultFont();
        }
        
        ActionComponent infoContainer = new ActionComponent(new Dimension(width + 10, height * name.length), null, null);
        infoContainer.setLayout(new BoxLayout(infoContainer, BoxLayout.Y_AXIS));
        
        ArrayList<ActionComponent> textContainer = new ArrayList<>();
        
        for(int i=0; i<text.size(); i++){
            textContainer.add(new ActionComponent(new Dimension(width, height), new FlowLayout(FlowLayout.LEFT), null));
            textContainer.get(i).add(text.get(i));
            textContainer.get(i).setBackground(Color.GRAY);
            cardsPanel.addActionText(new ComponentData<String, ActionComponent>(name[i], textContainer.get(i)));
            infoContainer.add(textContainer.get(i));
        }
        
        return infoContainer;
    }
    
    public ActionComponent getStat(){
        ArrayList<String> text = new ArrayList<>();
        text.add("Pokemon Name : " + pokemon.getNama());
        text.add("Level : " + pokemon.getLvl());
        text.add("Max Hp : " + pokemon.getMaxHp());
        text.add("Attack : " + pokemon.getDamage());
        text.add("Total Skill : " + pokemon.getNumberOfSkill());
        text.add("Element : Grass");
        
        DrawTextArray pokemonStat = new DrawTextArray(text, new Font(Font.MONOSPACED, Font.BOLD, 25), new Dimension(400, 500));
        ActionComponent statContainer = new ActionComponent(new Dimension(pokemonStat.getWidth() + 20, pokemonStat.getHeight() + 20), new FlowLayout(), null);
        statContainer.add(pokemonStat);
        statContainer.setName("Stat");
        
        return statContainer;
    }
    
    public ActionComponent getSkill(){
        DynamicText pokemonSkill = new DynamicText(pokemon.getSkill(0).getDescription(), new Font(Font.MONOSPACED, Font.BOLD, 25), new Dimension(400, 500));
        ActionComponent skillContainer = new ActionComponent(new Dimension(400 + 20, 500 + 20), new FlowLayout(), null);
        skillContainer.add(pokemonSkill);
        skillContainer.setName("Skill");
        
        return skillContainer;
    }
}
