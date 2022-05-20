/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import BattleCanvas.CanvasButton;
import BattleCanvas.CanvasMouseListener;
import BattleCanvas.CanvasTextArea;
import BattleCanvas.Drawable;
import BattleCanvas.PokemonBarAlt;
import Util.ImageLoader;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LVOILA
 */
public class BattleAltLoop implements Drawable {
    
    private CanvasButton useSkillBtn,changePokemonBtn,useItemBtn,runBtn;
    private PokemonBarAlt pokeBar,pokeBar2;
    private CanvasTextArea canvasTextArea;
    private CanvasMouseListener mouse;
    private ArrayList<Image> standPokemon1,standPokemon2;
    int standCt = 0;
    long timeAcc1 = 0;
    long changeEveryMilis = 750;
    
    public BattleAltLoop(CanvasMouseListener mouse) {
        this.mouse = mouse;
        //x,y,width,height
        this.useItemBtn = new CanvasButton("Use Item", MyFrame.DEFAULT_WIDTH - 310, MyFrame.DEFAULT_HEIGHT - 180, 125, 40, mouse);
        this.useSkillBtn = new CanvasButton("Use Skill", MyFrame.DEFAULT_WIDTH - 160, MyFrame.DEFAULT_HEIGHT - 180, 125, 40, mouse);
        this.changePokemonBtn = new CanvasButton("Switch", MyFrame.DEFAULT_WIDTH - 310, MyFrame.DEFAULT_HEIGHT - 120, 125, 40, mouse);
        this.runBtn = new CanvasButton("RUN", MyFrame.DEFAULT_WIDTH - 160, MyFrame.DEFAULT_HEIGHT - 120, 125, 40, mouse);
        
        this.pokeBar = new PokemonBarAlt(0, 100, 350, 120, mouse);
        this.pokeBar2 = new PokemonBarAlt(580, 330, 400, 150, mouse);
        this.canvasTextArea = new CanvasTextArea(200, mouse);
        
        try {
            standPokemon1 = ImageLoader.loadImageArrayCrop(512/8, 158, 8,"src\\Material\\Image\\simon1.png");
            standPokemon2 = ImageLoader.loadImageArrayCrop(512/8, 158, 8,"src\\Material\\Image\\simon1.png");
        } catch (IOException ex) {
            Logger.getLogger(BattleAltLoop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void draw(Graphics g) {
        this.canvasTextArea.draw(g);
        this.pokeBar.draw(g);
        this.pokeBar2.draw(g);
        
        g.drawImage(standPokemon1.get(standCt), MyFrame.DEFAULT_WIDTH/2 + 120, 0,150,300, null);
        
        if(!canvasTextArea.haveNextDialogue()) {
            this.useItemBtn.draw(g);
            this.useSkillBtn.draw(g);
            this.changePokemonBtn.draw(g);
            this.runBtn.draw(g);
        }
        
    }
    
    
    public void logicLoop(long diff) {
        graphicLogic(diff);
    }
    
    private void graphicLogic(long diff) {
        if(canvasTextArea.clicked()) {
            canvasTextArea.nextText();
        }
        
        this.timeAcc1 += diff;
        if(timeAcc1 >= changeEveryMilis) {
            timeAcc1 = 0;
            standCt = standCt + 1;
            if(standCt == standPokemon1.size()) {
                standCt = 0;
            }
        }
    }
}
