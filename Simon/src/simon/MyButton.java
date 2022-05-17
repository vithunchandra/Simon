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

public class MyButton extends JButton implements MouseListener{
    
    MyButton(String name){
        this.setText(name);
        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        this.setBackground(Color.LIGHT_GRAY);
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setForeground(Color.RED);
        this.setOpaque(false);
        this.setFocusable(false);
        this.setBorder(new EmptyBorder(0, 20, 0, 0));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setOpaque(true);
        this.setBackground(Color.GREEN);
        this.setForeground(Color.DARK_GRAY);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setOpaque(false);
        this.setForeground(Color.RED);
        this.setBackground(Color.LIGHT_GRAY);
    }
}
