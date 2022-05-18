/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author asus
 */
public class CanvasImage extends Canvas implements MouseListener{
    private Image image;
    private String fileName;
    CanvasImage(String fileName, int x, int y, int width, int height){
        this.setBounds(x, y, width, height);
        this.setPreferredSize(new Dimension(width, height));
        takeImage(fileName);
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = (Graphics2D) g;
        g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), null);
    }

    public void takeImage(String fileName){
        this.fileName = fileName;
        try {
            image = ImageIO.read(new File(this.fileName));
            image = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CanvasImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    //Mouse Listener Method
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setSize(new Dimension(this.getWidth() + 10, this.getHeight() + 10));
        image = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setSize(new Dimension(this.getWidth() - 10, this.getHeight() - 10));
        image = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
    }

}
