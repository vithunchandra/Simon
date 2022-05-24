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
import BattleCanvas.ItemCanvas;
import BattleCanvas.PokemonBarAlt;
import BattleCanvas.SwitchCanvas;
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
    private SwitchCanvas switchCanvas;
    
    //Item component
    private ItemCanvas itemCanvas;
    
    //pokeball anim
    private int pokeAnimX,pokeAnimY;
    private Double pokeAnimT;
    private boolean drawEnemy;
    
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
        this.switchCanvas = new SwitchCanvas(mouse, this);
        //item component
        this.itemCanvas = new ItemCanvas(mouse, this);
        //pokeball anim
        this.pokeAnimX = 50;
        this.pokeAnimY = 500;
        this.pokeAnimT = -15.;
        this.drawEnemy = true;
    }

    @Override
    public void draw(Graphics g) {
        if(this.nowState.equals("battle")) {
            g.drawImage(this.bgImg, 0, 0,MyFrame.DEFAULT_WIDTH,MyFrame.DEFAULT_HEIGHT, null);
            this.useItemBtn.setRenderedToFalse();
            this.useSkillBtn.setRenderedToFalse();
            this.changePokemonBtn.setRenderedToFalse();
            this.runBtn.setRenderedToFalse();
            
            if(itemCanvas.isUsingPokeBall()) {
                
                g.fillRect(pokeAnimX, pokeAnimY, 50, 50);
                pokeAnimY = pokeAnimY - 15 + (int)((pokeAnimT*pokeAnimT)/6);
                pokeAnimX = pokeAnimX + 5;
                pokeAnimT = pokeAnimT + 0.25;
                
                if(pokeAnimT >= 15.5) {
                    itemCanvas.setUsingPokeBall(false);
                    
                    
                    this.pokeAnimX = 50;
                    this.pokeAnimY = 500;
                    this.pokeAnimT = -15.;
                    
                    boolean catched = true;
                    
                    if(catched) {
                        this.dialogueNow.add("Congrats!\nYou catch the pokemon!");
                        this.canvasTextArea.nextText();
                        Player.pokemonInBox.add(this.enemyPokemon.get(enemyPokemonIdx));
                        this.drawEnemy = false;
                    }
                    else {
                        this.dialogueNow.add("You failed to catch the pokemon!");
                        this.canvasTextArea.nextText();
                    }
                }
                
                
            }
            
            if(this.drawEnemy) {
                this.enemyPokemon.get(enemyPokemonIdx).draw(g);
            }
            else {
                g.fillRect(650, 200, 50, 50);
            }
            this.playerPokemon.get(playerPokemonIdx).draw(g);

            this.canvasTextArea.draw(g);
            this.pokeBar.draw(g);
            this.pokeBar2.draw(g);
            
            
            if(!canvasTextArea.haveNextDialogue() && !itemCanvas.isUsingPokeBall() && !this.canvasTextArea.getTextNow().equals("Congrats!\nYou catch the pokemon!")) {
                
                this.useItemBtn.draw(g);
                this.useSkillBtn.draw(g);
                this.changePokemonBtn.draw(g);
                this.runBtn.draw(g);
            }
        }
        else if(this.nowState.equals("switch")) {
            this.switchCanvas.draw(g);
        }
        else if(this.nowState.equals("item")) {
            this.itemCanvas.draw(g);
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
            if(this.canvasTextArea.getTextNow().equals("Congrats!\nYou catch the pokemon!")) {
                if(canvasTextArea.clicked()) {
                    this.battling = false;
                }
            }

            if(this.runBtn.clicked() && this.runBtn.isRendered()) {
                this.battling = false;
            }

            if(this.changePokemonBtn.clicked() && this.changePokemonBtn.isRendered()) {
                this.switchCanvas.reInit();
                this.nowState = "switch";
            }
            
            if(this.useItemBtn.clicked() && this.useSkillBtn.isRendered()) {
                this.nowState = "item";
            }

            this.enemyPokemon.get(enemyPokemonIdx).logicLoop(diff);

            this.playerPokemon.get(playerPokemonIdx).setBound(100, 250, 225, 300);
            this.playerPokemon.get(playerPokemonIdx).setRenderFront(false);
            this.playerPokemon.get(playerPokemonIdx).logicLoop(diff);
        }
        else if(this.nowState.equals("switch")) {
            this.switchCanvas.logicLoop(diff);
        }
        else if(this.nowState.equals("item")) {
            this.itemCanvas.logicLoop(diff);
            
        }
    }

    public boolean isBattling() {
        return battling;
    }

    public void setNowState(String nowState) {
        this.nowState = nowState;
    }

    public PokemonBarAlt getPokeBar2() {
        return pokeBar2;
    }

    public int getPlayerPokemonIdx() {
        return playerPokemonIdx;
    }


    public void setPlayerPokemonIdx(int playerPokemonIdx) {
        this.playerPokemonIdx = playerPokemonIdx;
    }

    public DoubleLinkList<String> getDialogueNow() {
        return dialogueNow;
    }

    public CanvasTextArea getCanvasTextArea() {
        return canvasTextArea;
    }
    
    
}
