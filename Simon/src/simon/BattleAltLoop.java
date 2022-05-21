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
import Pokemon.ImagePath;
import Pokemon.PlantSimon;
import Util.ImageLoader;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Pokemon.Pokemon;
import Util.DoubleLinkList;
import javax.swing.JPanel;

/**
 *
 * @author LVOILA
 */
public class BattleAltLoop implements Drawable {
    
    private CanvasButton useSkillBtn,changePokemonBtn,useItemBtn,runBtn;
    private PokemonBarAlt pokeBar,pokeBar2;
    private CanvasTextArea canvasTextArea;
    private CanvasMouseListener mouse;
    private Pokemon playerPokemon,enemyPokemon;
    
    private DoubleLinkList<String> dialogueNow;
    private MyFrame frame;
    private JPanel panel;
    
    private boolean battling;
    
    public BattleAltLoop(CanvasMouseListener mouse) throws IOException {
        this.mouse = mouse;
        this.frame = frame;
        this.panel = panel;
        //x,y,width,height
        this.useItemBtn = new CanvasButton("Use Item", MyFrame.DEFAULT_WIDTH - 310, MyFrame.DEFAULT_HEIGHT - 180, 140, 50, mouse);
        this.useSkillBtn = new CanvasButton("Use Skill", MyFrame.DEFAULT_WIDTH - 160, MyFrame.DEFAULT_HEIGHT - 180, 140, 50, mouse);
        this.changePokemonBtn = new CanvasButton("Switch", MyFrame.DEFAULT_WIDTH - 310, MyFrame.DEFAULT_HEIGHT - 120, 140, 50, mouse);
        this.runBtn = new CanvasButton("RUN", MyFrame.DEFAULT_WIDTH - 160, MyFrame.DEFAULT_HEIGHT - 120, 140, 50, mouse);
        
        this.enemyPokemon = new PlantSimon("Simon Enemy",100, 10, MyFrame.DEFAULT_WIDTH/2 + 120, 0,150,300);
        this.playerPokemon = new PlantSimon("Simon player",100, 10, MyFrame.DEFAULT_WIDTH/2 + 120, 0,150,300);
        playerPokemon.levelUp();
        
        this.pokeBar = new PokemonBarAlt(this.enemyPokemon,0, 100, 350, 120, mouse);
        this.pokeBar2 = new PokemonBarAlt(this.playerPokemon,580, 330, 400, 150, mouse);
        
        this.dialogueNow = new DoubleLinkList<>();
        this.dialogueNow.add("You encounter " + this.enemyPokemon.getNama() + "!");
        this.dialogueNow.add("What you want to do next?");
        this.canvasTextArea = new CanvasTextArea(200, mouse,dialogueNow);
        
        this.battling = true;
        
        
    }

    @Override
    public void draw(Graphics g) {
        this.useItemBtn.setRenderedToFalse();
        this.useSkillBtn.setRenderedToFalse();
        this.changePokemonBtn.setRenderedToFalse();
        this.runBtn.setRenderedToFalse();


        this.canvasTextArea.draw(g);
        this.pokeBar.draw(g);
        this.pokeBar2.draw(g);
        
        this.enemyPokemon.draw(g);
        
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
        
        if(this.runBtn.clicked() && this.runBtn.isRendered()) {
            this.battling = false;
        }
        
        this.enemyPokemon.logicLoop(diff);
    }

    public boolean isBattling() {
        return battling;
    }
    
    
}
