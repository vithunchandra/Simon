/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import java.awt.*;
import javax.swing.*;
import java.awt.Image.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MyPanel extends JPanel{
    Image background;
    int x = 1000, y = 700;
    MyPanel(){
        try{
            background = ImageIO.read(new File(
                    "src\\Material\\Image\\Pokemon-Unite-PC-Wallpaper.jpg"
            ));
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Cannot find image!");
        }
        this.setPreferredSize(new Dimension(x, y));
        this.setLayout(new GridBagLayout());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g = (Graphics2D) g;
        g.drawImage(background, 0, 0, x, y, this);
    }
}
