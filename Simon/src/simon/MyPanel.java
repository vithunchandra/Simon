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
    MyPanel(Image background, LayoutManager setLayout){
        this.background = background;
        this.setPreferredSize(new Dimension(x, y));
        this.setLayout(setLayout);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g = (Graphics2D) g;
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
