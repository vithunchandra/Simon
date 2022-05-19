/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import Util.*;

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
        
        gbc = SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.NORTHWEST);
        switchPanel.add(setBackButton(), gbc);
        
        switchPanel.add(setPartyContainer(), gbc);
        
        frame.changePanel(switchPanel);
    }
    
    public Container setPartyContainer(){
        MyContainer container = new MyContainer(300, 320, new FlowLayout(FlowLayout.CENTER, 10, 10));
        container.setBackground(Color.red);
        
        CanvasText partyText = new CanvasText("Party", new Font(Font.SANS_SERIF, Font.BOLD, 20));
        container.add(partyText);
        
        for(int i=0; i<3; i++){
            MyContainer pokemon = new MyContainer(300, 80, new GridBagLayout());
            
            CanvasImage pokemonImage = new CanvasImage(
                    "src\\Material\\Image\\cookie0041_run04.png", 0, 0, 80, 80
            );
            CanvasText pokemonName = new CanvasText("Chandelure", new Font(Font.SANS_SERIF, Font.BOLD, 20));
            CanvasText pokemonLevel = new CanvasText("Lvl : 10", new Font(Font.SANS_SERIF, Font.BOLD, 25));
            
            gbc = SetGBC.setGbc(gbc, 0, 0, 0, 0, GridBagConstraints.WEST);
            gbc.gridheight = 2;
            pokemon.add(pokemonImage, gbc);
            
            gbc = SetGBC.setGbc(gbc, 1, 0, 0, 0, GridBagConstraints.EAST);
            gbc.gridheight = 1;
            pokemon.add(pokemonName, gbc);
            
            gbc = SetGBC.setGbc(gbc, 1, 1, 0, 0, GridBagConstraints.EAST);
            pokemon.add(pokemonLevel, gbc);
            
            container.add(pokemon);
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
