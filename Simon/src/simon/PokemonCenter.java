/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import Util.*;
import Util.Button.*;
import Util.Component.*;
import Util.Container.*;
import Util.Text.*;
import Item.*;
import Pokemon.*;
import java.awt.*;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.*;
import javax.swing.*;

public class PokemonCenter {
    MyFrame frame;
    MyPanel oldPanel, pokemonCenterPanel;

    public PokemonCenter(MyFrame frame, MyPanel oldPanel) {
        this.frame = frame;
        this.oldPanel = oldPanel;
        
        Image panelBackground = null;
        try {
            panelBackground = ImageLoader.loadImage("src\\Material\\Image\\pokemonCenter.png");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PokemonCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.1; gbc.weighty = 0.1;
        
        MyPanel pokemonCenterPanel = new MyPanel(panelBackground, new GridBagLayout());
        
        DrawText buttonName = new DrawText("Back", new Font(Font.SERIF, Font.BOLD, 30));
        ActionButton backButton = new ActionButton(new Dimension(buttonName.getWidth() + 30, buttonName.getHeight() + 15), new FlowLayout(), null);
        backButton.add(buttonName);
        
        new ChangePanelButton(frame, oldPanel, backButton);
        
        SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.NORTHWEST);
        pokemonCenterPanel.add(backButton, gbc);
        
        gbc.weighty = 0.2;
        SetGBC.setGbc(gbc, 0, 1, 0, 0, GridBagConstraints.NORTH);
        pokemonCenterPanel.add(pokemonParty(), gbc);
        
        frame.changePanel(pokemonCenterPanel);
    }
    
    public ActionComponent pokemonParty(){
        ActionComponent partyContainer = new ActionComponent(new Dimension(1000, 430), new FlowLayout(FlowLayout.CENTER, 10, 0), null);
        partyContainer.setBackground(new Color(0, 0, 0, 0));
        
        for(int i=0; i<Player.pokemonInParty.size(); i++){
            Pokemon pokemonData = Player.pokemonInParty.get(i);
            
            ActionComponent pokemonContainer = new ActionComponent(new Dimension(300, 430), null, null);
            pokemonContainer.setLayout(new BoxLayout(pokemonContainer, BoxLayout.Y_AXIS));
            pokemonContainer.setBackground(new Color(0, 0, 0, 90));
            
            DrawImage pokemonImage = new DrawImage(pokemonData.getDefaultFrontImage(), new Dimension(300, 300));
            DrawText pokemonName = new DrawText(pokemonData.getNama(), GetSizedFont.getSizedFont(pokemonData.getNama(), Font.SANS_SERIF, Font.BOLD, new Dimension(270, 40)));
            
            ActionComponent barHpContainer, nameContainer;
            nameContainer = new ActionComponent(new Dimension(300, 40), new FlowLayout(FlowLayout.CENTER, 10, 0), null);
            nameContainer.add(pokemonName);
            
            barHpContainer = new ActionComponent(new Dimension(300, 40), new FlowLayout(FlowLayout.LEFT), null);
            DrawText hpText = new DrawText("HP ", GetSizedFont.getSizedFont("HP ", Font.SANS_SERIF, Font.BOLD, new Dimension(40, 40)));
            JProgressBar hpBar = new JProgressBar();
            hpBar.setMinimum(0);
            hpBar.setMaximum(pokemonData.getMaxHp());
            hpBar.setValue(pokemonData.getHp());
            double temp = (pokemonData.getHp() / pokemonData.getMaxHp());
            int percent = (int) temp * 100;
            hpBar.setString(percent + "%");
            hpBar.setStringPainted(true);
            hpBar.setForeground(Color.GREEN);
            hpBar.setPreferredSize(new Dimension(230, 40));
            hpBar.setMaximumSize(new Dimension(230, 40));
            
            barHpContainer.add(hpText);
            barHpContainer.add(hpBar);
            
            pokemonContainer.add(nameContainer);
            pokemonContainer.add(Box.createVerticalGlue());
            pokemonContainer.add(pokemonImage);
//            pokemonContainer.add(Box.createVerticalGlue());
            pokemonContainer.add(barHpContainer);
            
            partyContainer.add(pokemonContainer);
        }
        
        return partyContainer;
    }
}
