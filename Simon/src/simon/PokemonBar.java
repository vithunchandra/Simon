/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;

public class PokemonBar extends JPanel{
    JLabel name, level, pokeBall;
    HpBar hpBar;
    PokemonBar(String nama, int lvl, int hp){
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        name = new JLabel(nama);
        name.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        level = new JLabel("Level : " + lvl);
        level.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        ImageIcon icon = new ImageIcon(
            new ImageIcon("src\\Material\\Image\\pokeball.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)
        );
        pokeBall = new JLabel();
        pokeBall.setIcon(icon);
        hpBar = new HpBar(0, 100);
        name.setBounds(20, 0, 200, 50);
        level.setBounds(200, 0, 200, 50);
        pokeBall.setBounds(30, 50, 30, 30);
        hpBar.setBounds(100, 50, 250, 30);
        this.add(name);
        this.add(level);
        this.add(pokeBall);
        this.add(hpBar);
    }
}
