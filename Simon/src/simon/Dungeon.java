/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import Util.Container.MyPanel;
import Util.Container.TransparantPanel;
import Util.ImageLoader;
import Util.MyFrame;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author LVOILA
 */
public class Dungeon {
    private String imageSource = "src\\Material\\Image\\1652700300094.png";
    private Image background;
    private MyPanel switchPanel;
    private MyFrame frame;
    private MyPanel oldPanel;
    private JLabel label;
    private int floor;
    
    public Dungeon(MyFrame frame,MyPanel oldPanel) {
        this.frame = frame;
        this.oldPanel = oldPanel;
        try {
            background = ImageLoader.loadImage(imageSource);
        } catch (IOException ex) {
            Logger.getLogger(SwitchPokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        switchPanel = new MyPanel(background,null);
        switchPanel.setPreferredSize(new Dimension(MyFrame.DEFAULT_WIDTH, MyFrame.DEFAULT_HEIGHT));
        
        floor = 1;
        label = new JLabel("FLOOR : " + floor);
        label.setBounds(MyFrame.DEFAULT_WIDTH/2 - 100, 0, 200, 50);
        label.setFont(new Font("Courier New", 0,30));
        switchPanel.add(label);
        
        //switchPanel.add(setNextFloorButton());
        //switchPanel.add();
        switchPanel.add(setBackButton());
        switchPanel.add(setNextFloorButton());
        switchPanel.add(setWalkButton());
        
        frame.changePanel(switchPanel);
    }
    
    public void nextFloor() {
        switchPanel.remove(label);
        floor = floor + 1;
        label = new JLabel("FLOOR : " + floor);
        label.setBounds(MyFrame.DEFAULT_WIDTH/2 - 100, 0, 200, 50);
        label.setFont(new Font("Courier New", 0,30));
        switchPanel.add(label);
        
        switchPanel.revalidate();
        switchPanel.repaint();
    }
    
    
    
    public TransparantPanel setNextFloorButton(){
        int widthNow = 225;
        int heightNow = 50;
        int xNow = 300;
        int yNow = 300;
                
        TransparantPanel buttonContainer = new TransparantPanel(0, 0);
        buttonContainer.setPreferredSize(new Dimension(widthNow, heightNow));
        buttonContainer.setBounds(xNow, yNow, widthNow, heightNow);
        
        MyButton backButton = new MyButton("NEXT FLOOR", new Dimension(widthNow, heightNow));
        backButton.setBounds(0, 0, widthNow, heightNow);
        backButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == backButton){
                        nextFloor();
                    }
                }
            }
        );
        backButton.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonContainer.add(backButton);
        
        return buttonContainer;
    }
    
    public TransparantPanel setWalkButton(){
        int widthNow = 225;
        int heightNow = 50;
        int xNow = 530;
        int yNow = 300;
                
        TransparantPanel buttonContainer = new TransparantPanel(0, 0);
        buttonContainer.setPreferredSize(new Dimension(widthNow, heightNow));
        buttonContainer.setBounds(xNow, yNow, widthNow, heightNow);
        
        MyButton backButton = new MyButton("WALK", new Dimension(widthNow, heightNow));
        backButton.setBounds(0, 0, widthNow, heightNow);
        backButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == backButton){
                        try {
                            new BattleMenuAlt(frame,switchPanel,floor,false);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Dungeon.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                    }
                }
            }
        );
        backButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        buttonContainer.add(backButton);
        
        return buttonContainer;
    }
    
    public TransparantPanel setBackButton(){
        TransparantPanel buttonContainer = new TransparantPanel(0, 0);
        buttonContainer.setPreferredSize(new Dimension(100, 50));
        buttonContainer.setBounds(0, 0, 100, 50);
        
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
