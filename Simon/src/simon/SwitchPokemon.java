/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author asus
 */
public class SwitchPokemon {
    String imageSource = "src\\Material\\Image\\Switch-Pokemon.jpg";
    Image background;
    MyPanel switchPanel;
    GridBagConstraints gbc;
    
    SwitchPokemon(MyFrame frame){
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
        
        TransparantPanel skillIcon = new TransparantPanel(0, 140);
        skillIcon.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        skillIcon.setPreferredSize(new Dimension(120, 260));
        
        for(int i=0; i<3; i++){
            skillIcon.add(new CanvasImage("src\\Material\\Image\\fireball.png", 0, i * 50, 80, 80));
        }
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 200;
        switchPanel.add(skillIcon, gbc);
        
        frame.changePanel(switchPanel);
    }
}
