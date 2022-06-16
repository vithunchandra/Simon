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
                
        Image infoBackground = null, containerBackground = null;
        try {
            infoBackground = ImageLoader.loadImage("src\\Material\\Image\\infoBackground.png");
            containerBackground = ImageLoader.loadImage("src\\Material\\Image\\dialogue.png");
        } catch (IOException ex) {
            Logger.getLogger(SwitchPokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MyPanel infoPanel = new MyPanel(infoBackground, new GridBagLayout());
        
        ActionComponent cards = new ActionComponent(new Dimension(620, 500), new CardLayout(), containerBackground);
        cards.add(getStat(), getStat().getName());
        cards.add(getSkill(), getSkill().getName());
        
        cardsPanel = new CardsPanel(cards);
        
        Font font = new Font(Font.SERIF, Font.BOLD, 30);
        
        DrawText buttonName = new DrawText("Back", font);
        ActionButton backButton = new ActionButton(new Dimension(buttonName.getWidth() + 30, buttonName.getHeight() + 15), new FlowLayout(), null);
        backButton.add(buttonName);
        new ChangePanelButton(frame, backButton);
        
        gbc.weightx = 0.1;
        gbc = SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.NORTHWEST);
        infoPanel.add(backButton, gbc);
        
        gbc.weighty = 0.1;
        gbc = SetGBC.setGbc(gbc, 0, 1, 0, 0, GridBagConstraints.CENTER);
        infoPanel.add(pokemonProfile(), gbc);
        
        gbc = SetGBC.setGbc(gbc, 1, 1, 0, 0, GridBagConstraints.WEST);
        infoPanel.add(cards, gbc);
        
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 22, 60, 0);
        gbc = SetGBC.setGbc(gbc, 0, 2, 0, 0, GridBagConstraints.NORTHWEST);
        infoPanel.add(infoList(font), gbc);
        
        return infoPanel;
    }
    
    public ActionComponent pokemonProfile(){
        DrawImage pokemonImage = new DrawImage(pokemon.getDefaultFrontImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH), new Dimension(300, 300));
        DrawText pokemonName = new DrawText(pokemon.getNama(), new Font(Font.SANS_SERIF, Font.BOLD, 30));
        pokemonName.setForeground(Color.WHITE);
        DrawText pokemonLvl = new DrawText(("Lvl : " + pokemon.getLvl()), new Font(Font.SANS_SERIF, Font.BOLD, 30));
        JProgressBar bar = new JProgressBar();
        
        Image nameBackground = null, barBackground = null;
        try {
            nameBackground = ImageLoader.loadImage("src\\Material\\Image\\NameBorder.png");
            barBackground = ImageLoader.loadImage("src\\Material\\Image\\BarBackground2.png");
        } catch (IOException ex) {
            Logger.getLogger(PokemonInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ActionComponent nameContainer = new ActionComponent(new Dimension(300, pokemonName.getHeight()), new FlowLayout(FlowLayout.CENTER), nameBackground);
        nameContainer.add(pokemonName);
        nameContainer.setBackground(new Color(0, 0, 0, 0));
        
        ActionComponent lvlContainer = new ActionComponent(new Dimension(300, pokemonLvl.getHeight()), new FlowLayout(FlowLayout.CENTER), null);
        lvlContainer.setBackground(new Color(255, 255 ,255, 0));
        lvlContainer.add(pokemonLvl);
        
        DrawText expText = new DrawText("Exp : ", new Font(Font.SANS_SERIF, Font.BOLD, 30));
        ActionComponent barExp = new ActionComponent(new Dimension(300, expText.getHeight()), new FlowLayout(FlowLayout.LEFT), barBackground);
        bar.setMinimum(0);
        bar.setMaximum(pokemon.getExpNeededToLevelUp());
        bar.setValue(pokemon.getExp());
        bar.setString(pokemon.getExp() + "/" + pokemon.getExpNeededToLevelUp());
        bar.setStringPainted(true);
        bar.setPreferredSize(new Dimension(200, expText.getHeight()));
        bar.setBackground(new Color(0, 0, 0, 150));
        bar.setForeground(Color.GREEN);
        
        barExp.add(expText);
        barExp.add(bar);
        
        ActionComponent imageContainer = new ActionComponent(new Dimension(300, 500), null, null);
        imageContainer.setBackground(new Color(216,191,216, 150));
        imageContainer.setLayout(new BoxLayout(imageContainer, BoxLayout.Y_AXIS));
        imageContainer.add(nameContainer);
        imageContainer.add(Box.createVerticalGlue());
        imageContainer.add(pokemonImage);
        imageContainer.add(Box.createVerticalGlue());
        imageContainer.add(lvlContainer, BorderLayout.EAST);
        imageContainer.add(barExp);
        
        return imageContainer;
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
            width += metrics.stringWidth(name[i]) + 10;
            text.get(i).useDefaultColor();
            text.get(i).useDefaultFont();
        }
        
        ActionComponent infoContainer = new ActionComponent(new Dimension(width + 10, height), null, null);
        infoContainer.setLayout(new BoxLayout(infoContainer, BoxLayout.X_AXIS));
        
        ArrayList<ActionComponent> textContainer = new ArrayList<>();
        
        for(int i=0; i<text.size(); i++){
            textContainer.add(new ActionComponent(new Dimension(metrics.stringWidth(name[i]) + 10, height), new FlowLayout(FlowLayout.LEFT), null));
            textContainer.get(i).add(text.get(i));
            textContainer.get(i).setBackground(Color.DARK_GRAY);
            cardsPanel.addActionText(new ComponentData<String, ActionComponent>(name[i], textContainer.get(i)));
            infoContainer.add(textContainer.get(i));
        }

        return infoContainer;
    }
    
    public ActionComponent getStat(){
        ArrayList<String> text = new ArrayList<>();
        text.add("Simon Name : " + pokemon.getNama());
        text.add("Level : " + pokemon.getLvl());
        text.add("Max Hp : " + pokemon.getMaxHp());
        text.add("Attack : " + pokemon.getDamage());
        text.add("Total Skill : " + pokemon.getNumberOfSkill());
        text.add("Element : " + pokemon.getType());
        
        DrawTextArray pokemonStat = new DrawTextArray(text, new Font(Font.MONOSPACED, Font.BOLD, 25), new Dimension(600, 500));
        ActionComponent statContainer = new ActionComponent(new Dimension(pokemonStat.getWidth() + 20, pokemonStat.getHeight() + 20), new FlowLayout(FlowLayout.LEFT), null);
        statContainer.add(pokemonStat);
        statContainer.setName("Stat");
        
        return statContainer;
    }
    
    public ActionComponent getSkill(){
        ActionComponent skillContainer = new ActionComponent(new Dimension(600 + 20, 500 + 20), null, null);
        skillContainer.setLayout(new BoxLayout(skillContainer, BoxLayout.Y_AXIS));
        
        for(int i=0; i<pokemon.getNumberOfSkill(); i++){
            SetBorder border = new SetBorder(Color.RED);
            
            DynamicText skillDesc = new DynamicText(pokemon.getSkill(i).getDescription(), new Font(Font.SANS_SERIF, Font.PLAIN, 20), new Dimension(510, 90));
            DrawText skillName = new DrawText(pokemon.getSkill(i).getSkillName(), new Font(Font.SANS_SERIF, Font.BOLD, 20));
            DrawImage skillIcon = new DrawImage("src\\Material\\Image\\fireball.png", new Dimension(80, 80));
            
            skillDesc.setForeground(Color.WHITE);
            skillName.setForeground(Color.WHITE);
            
            ActionComponent skillInfo = new ActionComponent(new Dimension(600, 125), new GridBagLayout(), null);
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 0.1;
            gbc.weighty = 0.1;
            
            SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.CENTER);
            gbc.gridheight = 2;
            skillInfo.add(skillIcon, gbc);
            
            SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.CENTER);
            gbc.gridheight = 1;
            gbc.gridwidth = 2;
            skillInfo.add(skillName, gbc);
            
            gbc.gridwidth = 1;
            SetGBC.setGbc(gbc, 1, 1, 0, 0, GridBagConstraints.CENTER);
            skillInfo.add(skillDesc, gbc);
            
            border.setColor(Color.yellow);
            skillInfo.setBorder(border.niceFrame());
            
            skillInfo.setBackground(new Color(0, 0, 0, 150));
            
            skillContainer.add(skillInfo);
        }
        
        for(int i=0; i<(4-pokemon.getNumberOfSkill()); i++){
            ActionComponent fillerBox = new ActionComponent(new Dimension(600, 125), null, null);
            fillerBox.setOpaque(false);
            
            skillContainer.add(fillerBox);
        }
        skillContainer.setOpaque(false);
        skillContainer.setName("Skill");
        
        return skillContainer;
    }
}
