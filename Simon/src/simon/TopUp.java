/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import Util.ImageLoader;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author LVOILA
 */

// Token : O23HH
// Token : BG52Z
public class TopUp {
    private String imageSource = "src\\Material\\Image\\1652700300094.png";
    private Image background;
    private MyPanel switchPanel;
    private MyFrame frame;
    private MyPanel oldPanel;
    private JTextField textField;

    public TopUp(MyFrame frame,MyPanel oldPanel) {
        this.frame = frame;
        this.oldPanel = oldPanel;
        try {
            background = ImageLoader.loadImage(imageSource);
        } catch (IOException ex) {
            Logger.getLogger(SwitchPokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        switchPanel = new MyPanel(background,null);
        switchPanel.setPreferredSize(new Dimension(MyFrame.DEFAULT_WIDTH, MyFrame.DEFAULT_HEIGHT));
        
        textField = new JTextField();
        textField.setBounds(MyFrame.DEFAULT_WIDTH/2 - 100, MyFrame.DEFAULT_HEIGHT/2 - 100, 200, 30);
        
        
        
        switchPanel.add(setBackButton());
        switchPanel.add(topUpButton());
        switchPanel.add(textField);
        
        frame.changePanel(switchPanel);
    }
    
    public TransparantPanel topUpButton(){
        TransparantPanel buttonContainer = new TransparantPanel(0, 0);
        buttonContainer.setPreferredSize(new Dimension(200, 100));
        buttonContainer.setBounds(MyFrame.DEFAULT_WIDTH/2 - 100, MyFrame.DEFAULT_HEIGHT/2 - 50, 200, 100);
        
        MyButton topUp = new MyButton("Top Up", new Dimension(200, 100));
        topUp.setBounds(0, 0, 200, 100);
        topUp.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == topUp){
                        String kode = textField.getText();
                        URL url;
                        try {
                            url = new URL("https://fallibilist-automat.000webhostapp.com/coba.php?filename=" + kode );

                            BufferedReader in = new BufferedReader(
                            new InputStreamReader(url.openStream()));

                            String inputLine = in.readLine();
                            
                            
                            if(inputLine.equals("0")) {
                                JOptionPane.showMessageDialog(null, "The Token Is Wrong");
                            }
                            else {
                                try {
                                    int topUpAmmount = Integer.parseInt(inputLine);
                                    JOptionPane.showMessageDialog(null, "You Get " + topUpAmmount);
                                } catch(Exception exce) {
                                    JOptionPane.showMessageDialog(null, "The Token Is Wrong");
                                }
                            }
                            in.close();
                        } catch (Exception ex) {
                            Logger.getLogger(TopUp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        );
        buttonContainer.add(topUp);
        
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
