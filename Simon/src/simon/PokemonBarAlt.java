/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author LVOILA
 */
public class PokemonBarAlt extends CanvasComponent{
    
    private int fontSize;
    
    private String pokemonName;
    private int lvl,health,maxHealth;
    public PokemonBarAlt(int x, int y, int width, int height, CanvasMouseListener mouse) {
        super(x, y, width, height, mouse);
        fontSize = 20;
        pokemonName = "<POKENAME>";
        lvl = -1;
        health = 5;
        maxHealth = 10;
        
    }
    
    
    
    @Override
    public void draw(Graphics g) {
        g.setFont(new Font(DEFAULT_FONT, Font.PLAIN, this.fontSize));
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
         
        g.setColor(Color.black);
        g.drawString(pokemonName, x + width/15, y + this.fontSize + height/10);
        g.drawString("Lv:" + lvl, x + width*11/15, y + this.fontSize + height/10);
        
        
        g.fillRect(x + width/8 - 10, y + height/2 -10 , width*4/5 + 20,height/6 + 20 );
        
        
        double percentage = ((double)width*2/3)*((double)health/(double)maxHealth);
        

        g.setColor(Color.gray);
        g.fillRect(x + width/4, y + height/2 ,width*2/3 ,height/6 );
        
        g.setColor(Color.green);
        g.fillRect(x + width/4, y + height/2 ,(int) percentage ,height/6 );
        
        g.setColor(Color.orange);
        g.drawString("HP", x + width/7 ,y + height/2 - 5 + this.fontSize);
    }
    
}
