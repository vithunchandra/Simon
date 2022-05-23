/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simon;

import Util.MyFrame;
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
import java.awt.Color;
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
    private ArrayList<Pokemon> playerPokemon,enemyPokemon;
    private int playerPokemonIdx,enemyPokemonIdx;
    
    private Image bgImg;
    private DoubleLinkList<String> dialogueNow;
    private MyFrame frame;
    private JPanel panel;
    
    private boolean battling;
    private String nowState;
    
    //switch component
    int idxChange1,idxChange2;
    private CanvasButton switchBtn1,switchBtn2;
    private PokemonBarAlt switchBar1,switchBar2;
    private CanvasButton backButton;
    
    public BattleAltLoop(CanvasMouseListener mouse) throws IOException {
        this.mouse = mouse;
        this.frame = frame;
        this.panel = panel;
        //x,y,width,height
        this.useItemBtn = new CanvasButton("Use Item", MyFrame.DEFAULT_WIDTH - 310, MyFrame.DEFAULT_HEIGHT - 180, 140, 50, mouse);
        this.useSkillBtn = new CanvasButton("Use Skill", MyFrame.DEFAULT_WIDTH - 160, MyFrame.DEFAULT_HEIGHT - 180, 140, 50, mouse);
        this.changePokemonBtn = new CanvasButton("Switch", MyFrame.DEFAULT_WIDTH - 310, MyFrame.DEFAULT_HEIGHT - 120, 140, 50, mouse);
        this.runBtn = new CanvasButton("RUN", MyFrame.DEFAULT_WIDTH - 160, MyFrame.DEFAULT_HEIGHT - 120, 140, 50, mouse);
        
        this.playerPokemonIdx = 0;
        this.enemyPokemonIdx = 0;
        
        this.enemyPokemon = new ArrayList<>();
        this.enemyPokemon.add(new PlantSimon(100, 10, MyFrame.DEFAULT_WIDTH/2 + 120, 0,150,300));
        this.playerPokemon = Player.pokemonInParty;
 
        
        this.pokeBar = new PokemonBarAlt(this.enemyPokemon.get(enemyPokemonIdx),0, 100, 350, 120, mouse);
        this.pokeBar2 = new PokemonBarAlt(this.playerPokemon.get(playerPokemonIdx),580, 330, 400, 150, mouse);
        
        this.dialogueNow = new DoubleLinkList<>();
        this.dialogueNow.add("You encounter " + this.enemyPokemon.get(enemyPokemonIdx).getNama() + "!");
        this.dialogueNow.add("What you want to do next?");
        this.canvasTextArea = new CanvasTextArea(200, mouse,dialogueNow);
        
        this.bgImg= ImageLoader.loadImage(ImagePath.BATTLE_BG1);
        this.battling = true;
        this.nowState = "battle";
        
        //switch component
        this.idxChange1 = -1;
        this.idxChange2 = -1;
        
        
        //125, 400, 100, 100
        backButton = new CanvasButton("Back", 0, 0, 100, 50, mouse);
        switchBtn1 = new CanvasButton("Switch!", 175, 475, 150, 50, mouse);
        switchBtn2 = new CanvasButton("Switch!", 625, 475, 150, 50, mouse);
        
        
    }

    @Override
    public void draw(Graphics g) {
        if(this.nowState.equals("battle")) {
            g.drawImage(this.bgImg, 0, 0,MyFrame.DEFAULT_WIDTH,MyFrame.DEFAULT_HEIGHT, null);
            this.useItemBtn.setRenderedToFalse();
            this.useSkillBtn.setRenderedToFalse();
            this.changePokemonBtn.setRenderedToFalse();
            this.runBtn.setRenderedToFalse();

            this.enemyPokemon.get(enemyPokemonIdx).draw(g);
            this.playerPokemon.get(playerPokemonIdx).draw(g);

            this.canvasTextArea.draw(g);
            this.pokeBar.draw(g);
            this.pokeBar2.draw(g);



            if(!canvasTextArea.haveNextDialogue()) {
                this.useItemBtn.draw(g);
                this.useSkillBtn.draw(g);
                this.changePokemonBtn.draw(g);
                this.runBtn.draw(g);
            }
        }
        else if(this.nowState.equals("switch")) {
            this.switchBtn1.setRenderedToFalse();
            this.switchBtn2.setRenderedToFalse();
            this.backButton.setRenderedToFalse();
            
            g.drawImage(this.bgImg, 0, 0,MyFrame.DEFAULT_WIDTH,MyFrame.DEFAULT_HEIGHT, null);
            
            g.fillRect(50, 75, 400, 500);
            g.fillRect(500, 75, 400, 500);
            
            this.playerPokemon.get(this.idxChange1).draw(g);
            this.playerPokemon.get(this.idxChange2).draw(g);
            
            this.backButton.draw(g);
            
            this.switchBtn1.draw(g);
            this.switchBtn2.draw(g);
            
            this.switchBar1.draw(g);
            this.switchBar2.draw(g);
        }
    }
    
    
    public void logicLoop(long diff) {
        graphicLogic(diff);
    }
    
    private void graphicLogic(long diff) {
        if(this.nowState.equals("battle")) {
            if(canvasTextArea.clicked()) {
                canvasTextArea.nextText();
            }

            if(this.runBtn.clicked() && this.runBtn.isRendered()) {
                this.battling = false;
            }

            if(this.changePokemonBtn.clicked() && this.changePokemonBtn.isRendered()) {
                int whichBox = 0;
                for(int i = 0;i < playerPokemon.size();i++) {
                    if(i != this.playerPokemonIdx) {
                        if(whichBox == 0) {
                            this.idxChange1 = i;
                        }
                        else if(whichBox == 1) {
                            this.idxChange2 = i;
                        }
                        
                        whichBox++;
                    }
                }
                
                this.playerPokemon.get(this.idxChange1).setRenderFront(true);
                this.playerPokemon.get(this.idxChange2).setRenderFront(true);
                switchBar1 = new PokemonBarAlt(this.playerPokemon.get(this.idxChange1), 115, 370, 260, 100, mouse);
                switchBar2 = new PokemonBarAlt(this.playerPokemon.get(this.idxChange2), 570, 370, 260, 100, mouse);
                this.nowState = "switch";
            }

            this.enemyPokemon.get(enemyPokemonIdx).logicLoop(diff);

            this.playerPokemon.get(playerPokemonIdx).setBound(100, 250, 225, 300);
            this.playerPokemon.get(playerPokemonIdx).setRenderFront(false);
            this.playerPokemon.get(playerPokemonIdx).logicLoop(diff);
        }
        else if(this.nowState.equals("switch")) {
            this.playerPokemon.get(this.idxChange1).setBound(125, 60, 225, 300);
            this.playerPokemon.get(this.idxChange2).setBound(600, 60, 225, 300);
            
            this.playerPokemon.get(this.idxChange1).logicLoop(diff);
            this.playerPokemon.get(this.idxChange2).logicLoop(diff);
            
            if(this.switchBtn1.clicked() && this.switchBtn1.isRendered()) {
                this.playerPokemonIdx = this.idxChange1;
                this.pokeBar2.setPokemon(this.playerPokemon.get(this.playerPokemonIdx));
                this.nowState = "battle";
            }
            if(this.switchBtn2.clicked() && this.switchBtn2.isRendered()) {
                this.playerPokemonIdx = this.idxChange2;
                this.pokeBar2.setPokemon(this.playerPokemon.get(this.playerPokemonIdx));
                this.nowState = "battle";
            }
            if(this.backButton.clicked() && this.backButton.isRendered()) {
                this.nowState = "battle";
            }
        }
    }

    public boolean isBattling() {
        return battling;
    }
    
    
}
