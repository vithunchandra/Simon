/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class DrawImage extends JComponent{
    private Image image;
    private String fileName;
    public DrawImage(String fileName, Dimension size){
        this.setSize(size);
        this.setPreferredSize(size);
        this.fileName = fileName;
        takeImage(fileName);
    }
    
    public DrawImage(String fileName, Dimension size, int x, int y){
        this.setBounds(x, y, size.width, size.height);
        this.fileName = fileName;
        takeImage(fileName);
    }
    
    public DrawImage(Image image, Dimension size){
        this.setSize(size);
        this.setPreferredSize(size);
        this.image = image;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = (Graphics2D) g;
        g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), null);
    }

    public void takeImage(String fileName){
        try {
            image = ImageLoader.loadImage(fileName).getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(DrawImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
