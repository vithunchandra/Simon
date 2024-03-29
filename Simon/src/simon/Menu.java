/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import Pokemon.Pokemon;
import Save.Memo;
import Util.MyFrame;
import Util.Container.MyPanel;
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
import Util.Container.TransparantPanel;
import Util.BackgroundSong;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Menu {
    GridBagConstraints gbc;
    JLabel title;
    ImageIcon imageTitle;
    private MyFrame frame;
    
    //add pokemon party
    
    Menu() {
        
        frame = new MyFrame();
        gbc = new GridBagConstraints();
        title = new JLabel();
        imageTitle = new ImageIcon(
                new ImageIcon("src\\Material\\Image\\logo-simon-1.png")
                .getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH)
        );
        title.setIcon((Icon) imageTitle);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        title.setPreferredSize(new Dimension(320, 150));
        gbc.weighty = 0.1;
        gbc.weightx = 0.1;
        
        try {
            //new BackgroundSong("src\\Material\\Sound\\XL-TT.wav");
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
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
            button.add(new MyButton(buttonName[i], new Dimension(400, 50)));
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
                            //Save Player
                            Memo memo = new Memo();
                            
                            try {
                                FileOutputStream fileOut = new FileOutputStream("src\\Save\\memo.ser");
                                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                                out.writeObject(memo);
                                out.close();
                                fileOut.close();
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
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
        String[] buttonName = {"Dungeon", "Switch Simon", "Simon Center", "Shop", "Gym", "Top-up Mon-E", "Back"};
        String[] message = {"Entering dungeon...", "Switching...", "Entering pokemon center,All Your Pokemon health has been healed", "Entering shop...", "Entering gym...", "Top-uping", "Back..."};
        for(int i=0; i<buttonName.length; i++){
            button.add(new MyButton(buttonName[i], new Dimension(400, 50)));
            button.get(i).setBounds(0, (i * 60), 400, 50);
            button.get(i).setIcon(icon);
            JButton tempButton = button.get(i);
            String tempMessage = message[i];
            button.get(i).addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        if(event.getSource() == tempButton && tempButton.getText().equals("Dungeon")){
                            new Dungeon(frame, playGame());
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Switch Simon")){
                            new SwitchPokemon(frame, playGame());
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Simon Center")){
//                            for(Pokemon playerPokemon : Player.pokemonInParty) {
//                                playerPokemon.healed(playerPokemon.getMaxHp());
//                            }
                            new PokemonCenter(frame, playGame());
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Shop")){
                            new Shop(frame, playGame());
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Gym")){
                            new Gym(frame, playGame());
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Top-up Mon-E")){
                            new TopUp(frame, playGame());
                        }else if(event.getSource() == tempButton && tempButton.getText().equals("Back")){
                            JOptionPane.showMessageDialog(null, "there's no turning back");
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
