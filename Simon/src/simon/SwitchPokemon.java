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
import Pokemon.*;

/**
 *
 * @author asus
 */
public class SwitchPokemon {
    String imageSource = "src\\Material\\Image\\messageImage_1652930975720.jpeg";
    Image background;
    MyPanel switchPanel;
    GridBagConstraints gbc;
    MyFrame frame;
    MyPanel oldPanel;
    ActionButton confirmButton, infoButton;
    ClickedDataComponent<Pokemon> party;
    ClickedDataComponent<Pokemon> pokeList;
    TradeDataButton trade;
    ChangeInfoPokemonPanel infoPanel; 
    
    SwitchPokemon(MyFrame frame, MyPanel oldPanel){
        this.frame = frame;
        this.oldPanel = oldPanel;
        try {
            background = ImageIO.read(new File(imageSource));
        } catch (IOException ex) {
            Logger.getLogger(SwitchPokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        gbc = new GridBagConstraints();
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        
        switchPanel = new MyPanel(background, new GridBagLayout());
        switchPanel.setPreferredSize(new Dimension(MyFrame.DEFAULT_WIDTH, MyFrame.DEFAULT_HEIGHT));
        
        party = new ClickedDataComponent();
        pokeList = new ClickedDataComponent();
        
        gbc = SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.NORTHWEST);
        switchPanel.add(setBackButton(), gbc);
        
        switchPanel.add(setPartyContainer(), gbc);
        
        switchPanel.add(showPokemonList(), gbc);
        
        trade = new TradeDataButton(party, pokeList, confirmButton);
        
        infoPanel = new ChangeInfoPokemonPanel(pokeList, frame, infoButton);
        
        frame.changePanel(switchPanel);
    }
    
    public Container showPokemonList(){
        Image containerBackground = null;
        try {
            containerBackground = ImageLoader.loadImage("src\\Material\\Image\\dialogue.png");
        } catch (IOException ex) {
            Logger.getLogger(SwitchPokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ActionComponent container = new ActionComponent(new Dimension(500, 540), new FlowLayout(FlowLayout.CENTER, 10, 10), null);
        ActionComponent header = new ActionComponent(new Dimension(300, 50), new FlowLayout(FlowLayout.CENTER, 50, 10), null);
        ActionComponent pokemonList = new ActionComponent(new Dimension(500, 400), new FlowLayout(FlowLayout.CENTER, 10, 10), containerBackground);
        
        container.setBackground(Color.LIGHT_GRAY);
        pokemonList.setBackground(Color.BLUE);
        
        DrawImage backIcon = new DrawImage("src\\Material\\Image\\Back_Black.png", new Dimension(30, 30));
        DrawImage forwardIcon = new DrawImage("src\\Material\\Image\\Forward_Black.png", new Dimension(30, 30));
        DrawText headerText = new DrawText("Box 1", new Font(Font.SANS_SERIF, Font.BOLD, 25));
       
        header.add(backIcon);
        header.add(headerText);
        header.add(forwardIcon);
        
        container.add(header);
        
        SetBorder border = new SetBorder(Color.GREEN);
        
        for(int i = 0; i<18; i++){
            ActionComponent imageContainer = new ActionComponent(new Dimension(80, 80), null, null);
            imageContainer.setBackground(new Color(255, 255, 255, 90));
            
            Pokemon pokemonData = Player.pokemonInParty.get((i % Player.pokemonInParty.size()));
            DrawImage pokemonImage = new DrawImage(pokemonData.getDefaultFrontImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH), new Dimension(80, 80), 0, 0);

            imageContainer.add(pokemonImage);
            
            pokemonList.add(imageContainer);
            
            pokeList.addComponent(new ComponentData<Pokemon, ActionComponent>(pokemonData, imageContainer));
            
            imageContainer.setBorder(border.niceFrame());
        }
        
        container.add(pokemonList);
        
        DrawText confirmText, infoText;
        DrawImage confirmImage, infoImage;
        ActionButton confirm, info;
        
        confirmText = new DrawText("Change", new Font(Font.MONOSPACED, Font.BOLD, 30));
        infoText = new DrawText("Info", new Font(Font.MONOSPACED, Font.BOLD, 30));
        
        confirmImage = new DrawImage("src\\Material\\Image\\Change.png", new Dimension(confirmText.getHeight(), confirmText.getHeight()));
        infoImage = new DrawImage("src\\Material\\Image\\Info.png", new Dimension(infoText.getHeight(), infoText.getHeight()));
        
        confirm = new ActionButton(new Dimension(confirmText.getWidth() + confirmImage.getWidth() + 30, confirmText.getHeight() + 15), new FlowLayout(), null);
        info = new ActionButton(new Dimension(infoText.getWidth() + infoImage.getWidth() + 30, infoText.getHeight() + 15), new FlowLayout(), null);
        
        confirm.add(confirmImage);
        confirm.add(confirmText);
        
        info.add(infoImage);
        info.add(infoText);
        
        container.add(confirm);
        container.add(info);
        
        confirmButton = confirm;
        infoButton = info;
        
        gbc = SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.CENTER);
        gbc.gridheight = 2;
        gbc.gridwidth = 2;
        return container;
    }
    
    public Container setPartyContainer(){
        Image containerBackground = null;
        try {
            containerBackground = ImageLoader.loadImage("src\\Material\\Image\\pokemonBorder.png");
        } catch (IOException ex) {
            Logger.getLogger(SwitchPokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ActionComponent container = new ActionComponent(new Dimension(210, 350), new FlowLayout(FlowLayout.CENTER, 10, 10), null);
        container.setBackground(Color.red);
        
        DrawText partyText = new DrawText("Party", new Font(Font.SANS_SERIF, Font.BOLD, 20));
        container.add(partyText);
        
        for(int i=0; i<3; i++){
            Pokemon pokemonData = Player.pokemonInParty.get(i);
            
            ActionComponent pokemon = new ActionComponent(new Dimension(210, 90), new GridBagLayout(), null);
            pokemon.setBackground(Color.LIGHT_GRAY);
            
            DrawImage pokemonImage = new DrawImage(
                    pokemonData.getDefaultFrontImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH), new Dimension(80, 80)
            );
            DrawText pokemonName = new DrawText(pokemonData.getNama(), new Font(Font.SANS_SERIF, Font.BOLD, 20));
            pokemonName.setName("pokemonName");
            
            DrawText pokemonLevel = new DrawText("Lvl : " + pokemonData.getLvl(), new Font(Font.SANS_SERIF, Font.BOLD, 25));
            pokemonLevel.setName("pokemonLevel");
            
            gbc = SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.WEST);
            gbc.gridheight = 2;
            pokemon.add(pokemonImage, gbc);
            
            gbc = SetGBC.setGbc(gbc, 1, 0, 0, 0, GridBagConstraints.SOUTHEAST);
            gbc.gridheight = 1;
            pokemon.add(pokemonName, gbc);
            
            gbc = SetGBC.setGbc(gbc, 1, 1, 0, 0, GridBagConstraints.EAST);
            pokemon.add(pokemonLevel, gbc);
            
            container.add(pokemon);
            
            party.addComponent(new ComponentData<Pokemon, ActionComponent>(pokemonData, pokemon));
        }
        
        gbc = SetGBC.setGbc(gbc, 0, 1, 0, 0, GridBagConstraints.NORTHWEST);
        return container;
    }
    
    public TransparantPanel setBackButton(){
        TransparantPanel buttonContainer = new TransparantPanel(0, 0);
        buttonContainer.setPreferredSize(new Dimension(100, 50));
        
        MyButton backButton = new MyButton("Back", new Dimension(100, 50));
        backButton.setBounds(0, 0, 100, 50);
        backButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == backButton){
                        frame.changePanel(oldPanel);
                    }
                }
            }
        );
        buttonContainer.add(backButton);
        
        return buttonContainer;
    }
}
