/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.*;


public class Menu {
    GridBagConstraints gbc;
    JLabel title;
    ImageIcon imageTitle;
    private MyFrame frame;
    Menu(){
        frame = new MyFrame();
        gbc = new GridBagConstraints();
        title = new JLabel();
        imageTitle = new ImageIcon(
                new ImageIcon("src\\Material\\Image\\logo-simon-1.png")
                .getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH)
        );
        title.setIcon((Icon) imageTitle);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        gbc.weighty = 0.1;
        gbc.weightx = 0.1;
        frame.changePanel(openMenu());
        
        frame.setVisible(true);
    }
    
    public MyPanel openMenu(){
        ArrayList<MyButton> button = new ArrayList<>();
        Image image = null;
        try {
            image = ImageIO.read(new File("src\\Material\\Image\\Pokemon-Unite-PC-Wallpaper.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        MyPanel newPanel = new MyPanel(image, new GridBagLayout());
        TransparantPanel buttonContainer = new TransparantPanel(0, 310, new Color(0, 0, 0, 90));
        buttonContainer.setPreferredSize(new Dimension(400, 120));
        String[] buttonName = {"Play", "Exit and save"};
        String[] message = {"Playing", "Saving and exit"};
        for(int i=0; i<buttonName.length; i++){
            button.add(new MyButton(buttonName[i]));
            button.get(i).setBounds(0, (i * 60), 400, 50);
            JButton tempButton = button.get(i);
            String tempMessage = message[i];
            button.get(i).addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        if(event.getSource() == tempButton && tempButton.getText().equals("Play")){
                            frame.changePanel(playGame());
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Exit and save")){
                            JOptionPane.showMessageDialog(null, tempMessage);
                            System.exit(0);
                        }
                    }
                }
            );
            
            button.get(i).addMouseListener(button.get(i));
            
            buttonContainer.add(button.get(i), gbc);
        }
        
        setGbc(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        newPanel.add(title, gbc);
        
        setGbc(0, 1, 0, 300);
        gbc.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonContainer, gbc);
        return newPanel;
    }
    
    public MyPanel playGame(){
        ImageIcon icon = new ImageIcon(
                new ImageIcon("src\\Material\\Image\\1652700300094.png")
                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );
        Image image = null;
        try {
            image = ImageIO.read(new File("src\\Material\\Image\\Pokemon-Unite-PC-Wallpaper.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        MyPanel newPanel = new MyPanel(image, new GridBagLayout());
        TransparantPanel buttonContainer = new TransparantPanel(0, 40, new Color(0, 0, 0, 90));
        TransparantPanel description = new TransparantPanel(0, 0, new Color(0, 0, 0, 90));
        
        buttonContainer.setPreferredSize(new Dimension(400, 450));
        description.setPreferredSize(new Dimension(400, 400));
        ArrayList<MyButton> button = new ArrayList<>();
        String[] buttonName = {"Dungeon", "Switch Pokemon", "Pokemon Center", "Shop", "Gym", "Top-up poke-coin", "Back"};
        String[] message = {"Entering dungeon...", "Switching...", "Entering pokemon center...", "Entering shop...", "Entering gym...", "Top-uping", "Back..."};
        for(int i=0; i<buttonName.length; i++){
            button.add(new MyButton(buttonName[i]));
            button.get(i).setBounds(0, (i * 60), 400, 50);
            button.get(i).setIcon(icon);
            JButton tempButton = button.get(i);
            String tempMessage = message[i];
            button.get(i).addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        if(event.getSource() == tempButton && tempButton.getText().equals("Dungeon")){
                            try {
                                new BattleMenuAlt(frame,playGame());
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Switch Pokemon")){
                            new SwitchPokemon(frame);
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Pokemon Center")){
                            JOptionPane.showMessageDialog(null, tempMessage);
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Shop")){
                            JOptionPane.showMessageDialog(null, tempMessage);
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Gym")){
                            JOptionPane.showMessageDialog(null, tempMessage);
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Top-up poke-coin")){
                            JOptionPane.showMessageDialog(null, tempMessage);
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Back")){
                            JOptionPane.showMessageDialog(null, tempMessage);
                            frame.changePanel(openMenu());
                        }
                    }
                }
            );
            
            button.get(i).addMouseListener(button.get(i));
           
            buttonContainer.add(button.get(i));
        }
        setGbc(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        newPanel.add(title, gbc);
        
        setGbc(0, 1, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonContainer, gbc);
        
//        setGbc(1, 0);
//        gbc.ipadx = 200;
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.gridheight = 2;
//        gbc.anchor = GridBagConstraints.EAST;
//        newPanel.add(description, gbc);
        resetGbc();
        return newPanel;
    }
    
    public void resetGbc(){
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.NONE;
    }
    
    public void setGbc(int gridX, int gridY, int xpad, int ypad){
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.ipadx = xpad;
        gbc.ipady = ypad;
    }
    
    public void setGbc(int gridX, int gridY){
        gbc.gridx = gridX;
        gbc.gridy = gridY;
    }
}
