/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BattleCanvas;

import BattleCanvas.CanvasMouseListener;
import BattleCanvas.CanvasComponent;
import Pokemon.ImagePath;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Pokemon.Pokemon;
import Util.ImageLoader;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LVOILA
 */
public class PokemonBarAlt extends CanvasComponent{
    
    private int fontSize;
    
    private Pokemon pokemon;
    private Image boxImg;
    public PokemonBarAlt(Pokemon pokemon,int x, int y, int width, int height, CanvasMouseListener mouse) {
        super(x, y, width, height, mouse);
        fontSize = 20;
        this.pokemon = pokemon;
        
        try {
            boxImg = ImageLoader.loadImage(ImagePath.INFO_BOX);
        } catch (IOException ex) {
            Logger.getLogger(PokemonBarAlt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setFont(DEFAULT_FONT);
        g.setColor(Color.white);
        g.drawImage(boxImg,x, y, width, height,null);
         
        g.setColor(Color.black);
        g.drawString(pokemon.getNama(), x + width/15, y + this.fontSize + height/10);
        g.drawString("Lv:" + pokemon.getLvl(), x + width*11/15, y + this.fontSize + height/10);
        
        
        g.fillRect(x + width/8 - 10, y + height/2 -10 , width*4/5 + 20,height/6 + 20 );
        
        
        double percentage = ((double)width*2/3)*((double)pokemon.getHp()/(double)pokemon.getMaxHp());
        

        g.setColor(Color.gray);
        g.fillRect(x + width/4, y + height/2 ,width*2/3 ,height/6 );
        
        g.setColor(Color.green);
        g.fillRect(x + width/4, y + height/2 ,(int) percentage ,height/6 );
        
        g.setColor(Color.orange);
        g.drawString("HP", x + width/7 ,y + height/2 - 5 + this.fontSize);
    }
    
}
