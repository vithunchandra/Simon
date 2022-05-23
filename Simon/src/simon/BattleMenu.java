/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import Util.MyFrame;
import Util.Container.MyPanel;
import java.awt.*;
import javax.swing.*;
import Util.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class BattleMenu{
    Image background;
    BattleMenu(MyFrame frame){
        try {
            background = ImageIO.read(new File("src\\Material\\Image\\battlemenu.png"));
        } catch (IOException ex) {
            Logger.getLogger(BattleMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        MyPanel panel = new MyPanel(background, null);
        PokemonBar player, enemy;
        player = new PokemonBar("Vithun", 3, 100);
        enemy = new PokemonBar("Vincent", 2, 100);
        player.setBounds(25, 100, 400, 100);
        enemy.setBounds(575, 500, 400, 100);
        panel.add(player);
        panel.add(enemy);
        
        frame.changePanel(panel);
    }
}
